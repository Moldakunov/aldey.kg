package kg.aldey.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.product.Product;
import kg.aldey.model.products.SearchText;
import kg.aldey.repository.CookiesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static kg.aldey.controller.ProductsController.getCategoriesByStoreId;
import static kg.aldey.controller.ProductsController.getProductCount;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    CookiesRepository cookiesRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping
    public String search(@ModelAttribute("searchForm") SearchText searchText,
                         Model model,
                         @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
                         HttpServletResponse response,
                         HttpSession session) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        List<Product> products = searchProducts(searchText.getText());
        int totalCount = getProductCount(products);
        int totalPages = totalCount <= 20
                ? 1
                : ((totalCount % 20) == 0
                ? totalCount / 20
                : (totalCount / 20) + 1);

        model.addAttribute("title", GlobalVar.storeName + " - Поиск");
        model.addAttribute("categoryName", getCategoriesByStoreId());
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("searchTextForUser", searchText.getText());
        if (session.getAttribute("userInfo")!=null){
            model.addAttribute("userInSession", "Кабинет");
        }
        if (session.getAttribute("userInfo")==null){
            model.addAttribute("userNotInSession", "Войти");
        }
        return "list";
    }

    //ПОИСК ТОВАРА
    public static List<Product> searchProducts(String searchText) {
        List<Product> searchProducts = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL
                    + "getSearchProducts?storeId=" + GlobalVar.storeId
                    + "&text=" + URLEncoder.encode(searchText, "UTF-8")
                    + "&limit=20");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("productList");
            int totalCountOfSearchProducts = new JSONObject(builder.toString()).getInt("totalCount");
//            System.out.println(productsArray);
            for (int i = 0; i < productsArray.length(); i++) {
                searchProducts.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
            }
            // для получения totalCount в метод header
            Product searchInt = new Product(totalCountOfSearchProducts);
            searchProducts.add(searchInt);

            /*for (int i = 0; i < searchProducts.size(); i++) {
                System.out.println(searchProducts.get(i).toString());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchProducts;
    }

    //ПОИСК ТОВАРА СО СТРАНИЦЕЙ
    public static List<Product> searchProducts(String searchText, int page, String sortField, String direction) {
        List<Product> searchProducts = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL
                    + "getSearchProducts?storeId=" + GlobalVar.storeId
                    + "&text=" + URLEncoder.encode(searchText, "UTF-8")
                    + "&limit=20"
                    + "&page=" + page
                    + (sortField.isEmpty() && direction.isEmpty() ? "" : "&sortField=" + sortField + "&direction=" + direction));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }
            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("productList");

//            System.out.println(productsArray);
            for (int i = 0; i < productsArray.length(); i++) {
                searchProducts.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
            }

            /*for (int i = 0; i < searchProducts.size(); i++) {
                System.out.println(searchProducts.get(i).toString());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchProducts;
    }

}
