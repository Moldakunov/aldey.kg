package kg.aldey.controller;

import com.google.gson.Gson;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.product.Product;
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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static kg.aldey.controller.ProductsController.getCategoriesByStoreId;
import static kg.aldey.controller.ProductsController.getProductCount;

@Controller
@RequestMapping("/category")
public class CategoriesController {

  @Autowired CookiesRepository cookiesRepository;

  public static List<Product> getProductsByCategory(int localCatId) {

    List<Product> products = new ArrayList<>();
    try {
      String response;
      StringBuilder builder = new StringBuilder();
      URL url =
          new URL(
              GlobalVar.mainURL
                  + "getProductsByCategory?storeId="
                  + GlobalVar.storeId
                  + "&limit=20&"
                  + "localCatId="
                  + localCatId);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      while ((response = reader.readLine()) != null) {
        builder.append(response);
      }

      JSONArray categoriesArray = new JSONObject(builder.toString()).getJSONArray("productList");
      int totalCountOfSearchProducts = new JSONObject(builder.toString()).getInt("totalCount");

      for (int i = 0; i < categoriesArray.length(); i++) {
        products.add(
            new Gson().fromJson(categoriesArray.getJSONObject(i).toString(), Product.class));
      }

      Product searchInt = new Product(totalCountOfSearchProducts);
      products.add(searchInt);
      //            for (int i = 0; i < categories.size(); i++) {
      //                System.out.println(categories.get(i).toString());
      //            }
    } catch (IOException e) {
      e.printStackTrace();
    }
    //        System.out.println(categories);
    return products;
  }

  @GetMapping("/{categoryName}")
  public String getCategory(
      @PathVariable String categoryName,
      Model model,
      @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
      HttpServletResponse response,
      HttpSession session) {
    Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

    List<Categories> categories = getCategoriesByStoreId();
    if (session.getAttribute("userInfo") != null) {
      model.addAttribute("userInSession", "Кабинет");
    }
    if (session.getAttribute("userInfo") == null) {
      model.addAttribute("userNotInSession", "Войти");
    }
    model.addAttribute("categoryName", categories);
    model.addAttribute("searchForm", new SearchText());

    for (Categories category : categories) {
      if (categoryName.equals(category.getTranslit())) {
        List<Product> products = getProductsByCategory(-category.getId());
        int totalCount = getProductCount(products);
        int totalPages =
            totalCount <= 20
                ? 1
                : ((totalCount % 20) == 0 ? totalCount / 20 : (totalCount / 20) + 1);

        model.addAttribute("title", GlobalVar.storeName + " - " + category.getName());
        model.addAttribute("category", categoryName);
        model.addAttribute("categoryNoTranslitName", category.getName());
        model.addAttribute("categoryMeta", category.getDescription());
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);
        /*System.out.println(totalCount);*/
        /*getProductCount(sortField.isEmpty() && direction.isEmpty()
        ? getProductsByCategory(-category.getId())
        : getProductsByCategorySorting(-category.getId(), sortField, direction)));*/

        return "list";
      }
    }
    return "list";
  }

  @GetMapping("/{categoryName}/{subCategoryName}")
  public String getSubCategory(
      @PathVariable String categoryName,
      @PathVariable String subCategoryName,
      Model model,
      @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
      HttpServletResponse response,
      HttpSession session) {
    Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

    List<Categories> categories = getCategoriesByStoreId();
    model.addAttribute("searchForm", new SearchText());
    model.addAttribute("categoryName", categories);
    if (session.getAttribute("userInfo") != null) {
      model.addAttribute("userInSession", "Кабинет");
    }
    if (session.getAttribute("userInfo") == null) {
      model.addAttribute("userNotInSession", "Войти");
    }
    for (Categories category : categories) {
      if (category.getSubCategory() != null) {
        for (SubCategories subCategory : category.getSubCategory()) {
          if (categoryName.equals(category.getTranslit())
              && subCategoryName.equals(subCategory.getTranslit())) {
            List<Product> products = getProductsByCategory(subCategory.getId());

            int totalCount = getProductCount(products);
            int totalPages =
                totalCount <= 20
                    ? 1
                    : ((totalCount % 20) == 0 ? totalCount / 20 : (totalCount / 20) + 1);

            model.addAttribute(
                "title",
                GlobalVar.storeName + " - " + category.getName() + " - " + subCategory.getName());
            model.addAttribute("category", categoryName);
            model.addAttribute("categoryMeta", category.getDescription());
            model.addAttribute("subCategoryMeta", subCategory.getDescription());
            model.addAttribute("categoryNoTranslitName", category.getName());
            model.addAttribute("subCategoryNoTranslitName", subCategory.getName());
            model.addAttribute("subCategory", subCategoryName);
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("totalPages", totalPages);

            return "list";
          }
        }
      }
    }
    return "list";
  }
}
