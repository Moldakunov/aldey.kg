package kg.aldey.controller;

import com.google.gson.Gson;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.model.product.Product;
import kg.aldey.model.products.Categories;
import kg.aldey.model.products.SubCategories;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static kg.aldey.controller.ProductsController.getCategoriesByStoreId;
import static kg.aldey.controller.SearchController.searchProducts;

@Controller
@RequestMapping("/paginate")
public class PaginationController {

  /*@GetMapping
  @ResponseBody
  public ResponseEntity<?> paginate(@RequestParam("pageNumber") int page,
                                    @RequestParam("searchText") String searchText,
                                    @RequestParam("categoryName") String categoryName,
                                    @RequestParam("subCategoryName") String subCategoryName,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("direction") String direction) {
      if (!searchText.isEmpty()) {
          return ResponseEntity.ok(
                  sortField.isEmpty() && direction.isEmpty()
                          ? searchProducts(searchText, page, "", "")
                          : searchProducts(searchText, page, sortField, direction));
      } else if (!categoryName.isEmpty() && subCategoryName.isEmpty()) {
          List<Categories> categories = getCategoriesByStoreId();

          for (Categories category : categories)
              if (categoryName.equals(category.getTranslit())) {
                  return ResponseEntity.ok(
                          sortField.isEmpty() && direction.isEmpty()
                                  ? getProductsByCategory(-category.getId(), page, "", "")
                                  : getProductsByCategory(-category.getId(), page, sortField, direction)
                  );
              }
      } else {
          List<Categories> categories = getCategoriesByStoreId();

          for (Categories category : categories)
              for (SubCategories subCategory : category.getSubCategory())
                  if (categoryName.equals(category.getTranslit()) && subCategoryName.equals(subCategory.getTranslit())) {
                      return ResponseEntity.ok(
                              sortField.isEmpty() && direction.isEmpty()
                                      ? getProductsByCategory(subCategory.getId(), page, "", "")
                                      : getProductsByCategory(subCategory.getId(), page, sortField, direction)
                      );
                  }
      }
      return ResponseEntity.ok("nothing");
  }*/

  @GetMapping("/search")
  @ResponseBody
  public ResponseEntity<?> paginateSearch(
      @RequestParam("pageNumber") int page,
      @RequestParam("searchText") String searchText,
      @RequestParam("sortField") String sortField,
      @RequestParam("direction") String direction) {
    if (!searchText.isEmpty()) {
      return ResponseEntity.ok(
          sortField.isEmpty() && direction.isEmpty()
              ? searchProducts(searchText, page, "", "")
              : searchProducts(searchText, page, sortField, direction));
    }
    return ResponseEntity.ok("nothing");
  }

  @GetMapping("/category")
  @ResponseBody
  public ResponseEntity<?> paginateCategory(
      @RequestParam("pageNumber") int page,
      @RequestParam("categoryName") String categoryName,
      @RequestParam("sortField") String sortField,
      @RequestParam("direction") String direction) {
    if (!categoryName.isEmpty()) {
      List<Categories> categories = getCategoriesByStoreId();

      for (Categories category : categories)
        if (categoryName.equals(category.getTranslit())) {
          return ResponseEntity.ok(
              sortField.isEmpty() && direction.isEmpty()
                  ? getProductsByCategory(-category.getId(), page, "", "")
                  : getProductsByCategory(-category.getId(), page, sortField, direction));
        }
    }
    return ResponseEntity.ok("nothing");
  }

  @GetMapping("/subcategory")
  @ResponseBody
  public ResponseEntity<?> paginate(
      @RequestParam("pageNumber") int page,
      @RequestParam("categoryName") String categoryName,
      @RequestParam("subCategoryName") String subCategoryName,
      @RequestParam("sortField") String sortField,
      @RequestParam("direction") String direction) {
    List<Categories> categories = getCategoriesByStoreId();

    for (Categories category : categories)
      if (category.getSubCategory() != null) {
        for (SubCategories subCategory : category.getSubCategory())
          if (categoryName.equals(category.getTranslit())
              && subCategoryName.equals(subCategory.getTranslit())) {
            return ResponseEntity.ok(
                sortField.isEmpty() && direction.isEmpty()
                    ? getProductsByCategory(subCategory.getId(), page, "", "")
                    : getProductsByCategory(subCategory.getId(), page, sortField, direction));
          }
      }

    return ResponseEntity.ok("nothing");
  }

  public List<Product> getProductsByCategory(
      int localCatId, int page, String sortField, String direction) {
    List<Product> products = new ArrayList<>();
    try {
      String response;
      StringBuilder builder = new StringBuilder();
      URL url =
          new URL(
              GlobalVar.mainURL
                  + "getProductsByCategory?storeId="
                  + GlobalVar.storeId
                  + "&limit=20"
                  + "&localCatId="
                  + localCatId
                  + "&page="
                  + page
                  + (sortField.isEmpty() && direction.isEmpty()
                      ? ""
                      : "&sortField=" + sortField + "&direction=" + direction));

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      while ((response = reader.readLine()) != null) {
        builder.append(response);
      }

      JSONArray categoriesArray = new JSONObject(builder.toString()).getJSONArray("productList");

      for (int i = 0; i < categoriesArray.length(); i++) {
        products.add(
            new Gson().fromJson(categoriesArray.getJSONObject(i).toString(), Product.class));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return products;
  }
}
