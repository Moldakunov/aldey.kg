package kg.aldey.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.aldey.RSS.HTMLRemoverParser;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.UserWithAllInfo;
import kg.aldey.model.forms.FeedbackMessage;
import kg.aldey.model.product.Product;
import kg.aldey.model.products.SearchText;
import kg.aldey.repository.CookiesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static kg.aldey.controller.ProductsController.getCategoriesByStoreId;

@Controller
public class OtherTemplatesController {

    @Autowired
    CookiesRepository cookiesRepository;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(Model model,
                        @CookieValue(value = "JSESSIONID", defaultValue = "") String givenCookie,
                        HttpServletResponse response,
                        HttpSession session) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", GlobalVar.storeName);
        model.addAttribute("categoryName", getCategoriesByStoreId());
        model.addAttribute("newProducts", newProducts());
        model.addAttribute("newIncomeProducts", newIncomeProducts());
        model.addAttribute("hitProducts", getHitProducts());
        model.addAttribute("searchForm", new SearchText());

        if (session.getAttribute("userInfo")!=null){
            model.addAttribute("userInSession", "Кабинет");
        }
        if (session.getAttribute("userInfo")==null){
            model.addAttribute("userNotInSession", "Войти");
        }

        return "index";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    @ResponseBody
    public void getSignOutStatus(HttpSession session) {
        session.removeAttribute("userInfo");
    }

    //СКИДОЧНЫЕ ТОВАРЫ
    public List<Product> newSalesProducts() {
        List<Product> salesProducts = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL + "getHotSalesProducts?limit=10&storeId=" + GlobalVar.storeId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("productList");

            for (int i = 0; i < productsArray.length(); i++) {
                salesProducts.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
            }

//            for (int i = 0; i < incomeProducts.size(); i++) {
//                System.out.println(incomeProducts.get(i).toString());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesProducts;
    }

    //НОВЫЕ ПОСТУПЛЕНИЯ
    public List<Product> newIncomeProducts() {
        List<Product> incomeProducts = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL + "getNewIncomeProducts?storeId=" + GlobalVar.storeId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("productList");

            for (int i = 0; i < productsArray.length(); i++) {
                incomeProducts.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
            }

//            for (int i = 0; i < incomeProducts.size(); i++) {
//                System.out.println(incomeProducts.get(i).toString());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incomeProducts;
    }

    // ХИТЫ ПРОДАЖ
    public List<Product> getHitProducts() {
        List<Product> hitProducts = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL + "getHotSalesProducts?storeId=" + GlobalVar.storeId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("productList");

            for (int i = 0; i < productsArray.length(); i++) {
                hitProducts.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return hitProducts;
    }

    //НОВИНКИ
    public List<Product> newProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL + "getNewProducts?storeId=" + GlobalVar.storeId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("productList");
//            System.out.println(productsArray);
            for (int i = 0; i < productsArray.length(); i++) {
                products.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @GetMapping("/delivery")
    public String delivery(Model model,
                           @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
                           HttpServletResponse response,
                           HttpSession session) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", GlobalVar.storeName + " - Доставка");
        model.addAttribute("categoryName", getCategoriesByStoreId());
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("feedbackForm", new FeedbackMessage());

        if (session.getAttribute("userInfo")!=null){
            model.addAttribute("userInSession", "Кабинет");
        }
        if (session.getAttribute("userInfo")==null){
            model.addAttribute("userNotInSession", "Войти");
        }

        return "delivery";
    }

    @RequestMapping(value = "/addToFavorite", method = RequestMethod.POST)
    @ResponseBody
    public String addProductToFavorite(
            @RequestParam("productId") String productId,
            Model model,
            @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session) throws IOException {

        UserWithAllInfo userWithAllInfo = (UserWithAllInfo) session.getAttribute("userInfo");

        int count;
        if (session.getAttribute("userInfo")!=null) {
            count = sendJsonAddToFavorite(Integer.parseInt(userWithAllInfo.getId()), Integer.parseInt(productId));

            /*System.out.println(Integer.parseInt(userWithAllInfo.getId()));
            System.out.println(Integer.parseInt(productId));*/
            return "Favorite products: " + count;
        }
        return "error";
    }

    private int sendJsonAddToFavorite(int userId, int productId) throws IOException {
        URL url = new URL(GlobalVar.mainURL+"addProductToFavorite?userId="+userId+"&productId="+productId);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {

            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        //System.out.println(response.toString());
        JSONObject jsonObject = new JSONObject(response.toString());
        return jsonObject.getInt("count");
    }

}
