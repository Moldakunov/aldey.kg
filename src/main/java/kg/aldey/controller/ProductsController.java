package kg.aldey.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.Review;
import kg.aldey.model.product.Product;
import kg.aldey.model.product.SizeColor;
import kg.aldey.model.products.Categories;
import kg.aldey.model.products.SearchText;
import kg.aldey.model.products.SubCategories;
import kg.aldey.repository.CookiesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class ProductsController {

  @Autowired CookiesRepository cookiesRepository;

  @Autowired EntityManager entityManager;

  // http://109.201.191.116:8080/getCacheCount             http://109.201.191.116:8080/clearCache

  // ТОВАРЫ В КОРЗИНЕ ПО SESSIONID
  public static List<Product> getProductsByIds(String ids) {
    List<Product> products = new ArrayList<>();
    try {
      String response;
      Gson gson = new GsonBuilder().create();
      StringBuilder builder = new StringBuilder();
      URL url = new URL(GlobalVar.mainURL + "getProductByIds?ids=" + ids);

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      while ((response = reader.readLine()) != null) {
        builder.append(response);
      }

      JSONArray productsArray;
      if (builder.toString().contains("productList")) {
        productsArray = new JSONObject(builder.toString()).getJSONArray("productList");

        for (int i = 0; i < productsArray.length(); i++) {
          products.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return products;
  }

  public static List<Categories> getCategoriesByStoreId() {
    List<Categories> categories = new ArrayList<>();

    try {
      String response;
      StringBuilder builder = new StringBuilder();
      URL url = new URL(GlobalVar.mainURL + "getCategoryByStoreId?storeId=" + GlobalVar.storeId);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      while ((response = reader.readLine()) != null) {
        builder.append(response);
      }

      JSONArray categoriesArray;
      if (!new JSONObject(builder.toString()).getJSONArray("сategory").isEmpty()) {
        categoriesArray = new JSONObject(builder.toString()).getJSONArray("сategory");

        for (int i = 0; i < categoriesArray.length(); i++) {
          if (!categoriesArray.getJSONObject(i).get("name").toString().isEmpty()) {
            // на случай если имена категории будут одинаковыми
            // if (categories.contains(categoriesArray.getJSONObject(i).getString("name"))) {}
            categories.add(
                new Gson().fromJson(categoriesArray.getJSONObject(i).toString(), Categories.class));
            categories.get(i).setTranslit(Categories.transliterate(categories.get(i).getName()));

            if (categories.get(i).getSubCategory() != null) {
              for (int j = 0; j < categories.get(i).getSubCategory().size(); j++) {
                categories
                    .get(i)
                    .getSubCategory()
                    .get(j)
                    .setTranslit(
                        Categories.transliterate(
                            categories.get(i).getSubCategory().get(j).getName()));
              }
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return categories;
  }

  public static int getProductCount(List<Product> products) {
    return products.get(products.size() - 1).getId();
  }

  @GetMapping("/product/{seoUrl}")
  public String getProductBySeoUrl(
      @PathVariable String seoUrl,
      Model model,
      @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
      HttpServletResponse response,
      HttpSession session) {
    Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

    HashMap<String, Object> mapWithAllInfo = getAllInfoBySeoUrl(seoUrl);
    List<Product> products = (List<Product>) mapWithAllInfo.get("products");
    Product product = products.get(products.size() - 1);
    List<Categories> categories = getCategoriesByStoreId();
    List<Review> reviews = getReviewsByProductId(product.getId());

    if (session.getAttribute("userInfo") != null) {
      model.addAttribute("userInSession", "Кабинет");
    }

    if (session.getAttribute("userInfo") == null) {
      model.addAttribute("userNotInSession", "Войти");
    }

    model.addAttribute("review", new Review());
    model.addAttribute("reviews", reviews);
    model.addAttribute("reviewsCount", reviews.size());
    model.addAttribute("reviewsGrouping", calculateRatingGrouping(reviews));

    model.addAttribute("rating", product.getRating());

    model.addAttribute("searchForm", new SearchText());
    model.addAttribute("categoryName", categories);

    model.addAttribute("product", product);
    model.addAttribute("title", product.getNameShort() + " - " + GlobalVar.storeName);
    products.remove(products.size() - 1);
    model.addAttribute("related", products);
    model.addAttribute("images", mapWithAllInfo.get("images"));
    model.addAttribute("description", mapWithAllInfo.get("description"));
    model.addAttribute("sizesColors", mapWithAllInfo.get("sizesColors"));

    for (Categories category : categories) {
      if (product.getLocalCatLevel1() == category.getId()) {
        model.addAttribute("categoryNoTranslitName", category.getName());
        model.addAttribute("category", category.getTranslit());
        if (category.getSubCategory() != null) {
          for (SubCategories subCategory : category.getSubCategory()) {
            if (product.getLocalCatLevel2() == subCategory.getId()) {
              model.addAttribute("subCategoryNoTranslitName", subCategory.getName());
              model.addAttribute("subCategory", subCategory.getTranslit());
              return "product-box";
            }
          }
        }
      }
    }
    return "product-box";
  }

  public HashMap<String, Object> getAllInfoBySeoUrl(String seoUrl) {
    HashMap<String, Object> finalMap = new HashMap<>();

    List<Product> products = new ArrayList<>();
    String description = "";
    List<String> images = new ArrayList<>();
    List<SizeColor> sizeColors = new ArrayList<>();

    try {
      String response;
      StringBuilder builder = new StringBuilder();
      Gson gson = new GsonBuilder().create();
      URL url = new URL(GlobalVar.mainURL + "getProductBySeoUrl?storeId="+GlobalVar.storeId+"&seoUrl=" + seoUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      while ((response = reader.readLine()) != null) {
        builder.append(response);
      }

      if (new JSONObject(builder.toString()).getJSONArray("similarProducts") != null) {
        JSONArray similarProducts =
            new JSONObject(builder.toString()).getJSONArray("similarProducts");
        for (int i = 0; i < similarProducts.length(); i++) {
          products.add(gson.fromJson(similarProducts.getJSONObject(i).toString(), Product.class));
        }
      }

      JSONObject product = new JSONObject(builder.toString()).getJSONObject("productById");
      products.add(gson.fromJson(product.toString(), Product.class));

      // Продукты
      finalMap.put("products", products);

      // Описание
      if (builder.toString().contains("description")) {
        description =
            new JSONObject(builder.toString()).getJSONObject("description").getString("text");
      }
      finalMap.put("description", description);

      // Картинки
      if (builder.toString().contains("images")) {
        JSONArray imagesArray = new JSONObject(builder.toString()).getJSONArray("images");

        for (int i = 0; i < imagesArray.length(); i++) {
          images.add(imagesArray.getJSONObject(i).getString("imageUrl"));
        }
      }
      finalMap.put("images", images);

      // Размеры и цвета
      if (builder.toString().contains("sizesColors")) {
        JSONArray sizesColorsArray = new JSONObject(builder.toString()).getJSONArray("sizesColors");
        SizeColor sizeColor;
        for (int i = 0; i < sizesColorsArray.length(); i++) {
          sizeColor =
              (gson.fromJson(sizesColorsArray.getJSONObject(i).toString(), SizeColor.class));
          sizeColor.setColorEnName(sizeColor.getColorEnName().toLowerCase().substring(2));
          sizeColors.add(sizeColor);
        }
      }
      finalMap.put("sizesColors", sizeColors);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return finalMap;
  }

  public List<Review> getReviewsByProductId(int productId) {
    List<Review> reviews = new ArrayList<>();
    try {
      String response;
      StringBuilder builder = new StringBuilder();
      Gson gson = new GsonBuilder().create();
      URL url = new URL(GlobalVar.mainURL + "getReviewByProductId?productId=" + productId);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      while ((response = reader.readLine()) != null) {
        builder.append(response);
      }

      JSONArray reviewsArray = new JSONObject(builder.toString()).getJSONArray("reviews");
      for (int i = 0; i < reviewsArray.length(); i++) {
        reviews.add(gson.fromJson(reviewsArray.getJSONObject(i).toString(), Review.class));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return reviews;
  }

  public Map<Integer, Long> calculateRatingGrouping(List<Review> reviews) {
    Map<Integer, Long> r =
        reviews.stream()
            .filter(review -> review.getRating() != 0)
            .map(Review::getRating)
            .collect(Collectors.toList())
            .stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    for (int i = 1; i <= 5; i++) {
      if (!r.containsKey(i)) {
        r.put(i, 0L);
      }
    }

    return r;
  }
}
