package kg.aldey.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.OrderInAccount;
import kg.aldey.model.UserForSignin;
import kg.aldey.model.UserWithAllInfo;
import kg.aldey.model.product.Product;
import kg.aldey.model.products.SearchText;
import kg.aldey.repository.CookiesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static kg.aldey.controller.ProductsController.getCategoriesByStoreId;

@Controller
public class SignInController {

    @Autowired
    CookiesRepository cookiesRepository;

    @Autowired
    EntityManager entityManager;

    //For send UserInfo when user sign in in personal account
    private UserWithAllInfo userWithAllInfoGlobal;

    @GetMapping("/sign-in")
    public String signIn(Model model,
                          @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
                          HttpServletResponse response,
                          HttpSession session) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", GlobalVar.storeName + " - Войти");
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());

        return "sign-in";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public String getUserInfoForSignin(
            @RequestParam("emailSignin") String email,
            @RequestParam("passwordSignin") String password,
            Model model,
            @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
            HttpServletResponse response,
            HttpServletRequest request) throws IOException {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", GlobalVar.storeName + " - Войти");
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());

        UserForSignin userForSignin = new UserForSignin(email, password, GlobalVar.storeId);
        Gson g = new Gson();
        UserWithAllInfo userWithAllInfo = g.fromJson(sendJsonForSignin(userForSignin), UserWithAllInfo.class);
        //userWithAllInfoGlobal = userWithAllInfo;

        //Work with Session in signin
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", userWithAllInfo);
        model.addAttribute("userInfoInSession", session.getAttribute("userInfo"));

        String statusOfResponse = userWithAllInfo.getId();

        if (statusOfResponse.equals("0")) return "false";
        return "true";
    }

    private String sendJsonForSignin(UserForSignin userForSignin) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonUser = mapper.writeValueAsString(userForSignin);

        URL url = new URL(GlobalVar.mainURL+"authUser");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonUser.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }

    @GetMapping("/account")
    public String account(Model model,
                          @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
                          HttpServletResponse response,
                          HttpSession session) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", "Личный кабинет - " + GlobalVar.storeName);
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());
        if (session.getAttribute("userInfo")!=null){
            model.addAttribute("userInSession", "Кабинет");
        }
        if (session.getAttribute("userInfo")==null){
            model.addAttribute("userNotInSession", "Войти");
        }
        //User Info
        if (session.getAttribute("userInfo")!=null){
            UserWithAllInfo user = (UserWithAllInfo) session.getAttribute("userInfo");
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("address", user.getAddress());
            model.addAttribute("nickName", user.getNickName());
            model.addAttribute("phone", user.getPhone());
            model.addAttribute("id", user.getId());
            model.addAttribute("countFavorite", user.getCountFavorite());

            /*System.out.println("User в сессии: "+user.toString());*/
            return "account";
        }
        return "404";
    }

    @GetMapping("/orders")
    public String orders(Model model,
                         @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
                         HttpServletResponse response,
                         HttpSession session) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", "Мои покупки - " + GlobalVar.storeName);
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());
        if (session.getAttribute("userInfo")!=null){
            model.addAttribute("userInSession", "Кабинет");
        }
        if (session.getAttribute("userInfo")==null){
            model.addAttribute("userNotInSession", "Войти");
        }
        //User Info
        if (session.getAttribute("userInfo")!=null){

        UserWithAllInfo user = (UserWithAllInfo) session.getAttribute("userInfo");

        model.addAttribute("userName", user.getUserName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("nickName", user.getNickName());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("id", user.getId());
        model.addAttribute("orders", getOrdersByUser(user.getPhone()));
        model.addAttribute("countFavorite", user.getCountFavorite());

        System.out.println("User в сессии: "+user.toString());
        return "orders";
        }

        return "404";
    }

    @GetMapping("/favorites")
    public String favorites(Model model,
                         @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
                         HttpServletResponse response,
                         HttpSession session) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", "Мои избранные - " + GlobalVar.storeName);
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());
        if (session.getAttribute("userInfo")!=null){
            model.addAttribute("userInSession", "Кабинет");
        }
        if (session.getAttribute("userInfo")==null){
            model.addAttribute("userNotInSession", "Войти");
        }
        //User Info
        if (session.getAttribute("userInfo")!=null){

            UserWithAllInfo user = (UserWithAllInfo) session.getAttribute("userInfo");

            model.addAttribute("userName", user.getUserName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("address", user.getAddress());
            model.addAttribute("nickName", user.getNickName());
            model.addAttribute("phone", user.getPhone());
            model.addAttribute("id", user.getId());
            model.addAttribute("favorites", getFavoriteProductsbyUser(user.getId()));
            model.addAttribute("countFavorite", user.getCountFavorite());

            //System.out.println("User в сессии: "+user.toString());
            return "favorites";
        }
        return "404";
    }

    public ArrayList<OrderInAccount> getOrdersByUser(String phone){
        ArrayList<OrderInAccount> orders = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL + "getclientOrder?phone="+phone+"&sort=desc&storeId=" + GlobalVar.storeId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("orderList");

            for (int i = 0; i < productsArray.length(); i++) {
                orders.add(gson.fromJson(productsArray.getJSONObject(i).toString(), OrderInAccount.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public ArrayList<Product> getFavoriteProductsbyUser(String userId){
        ArrayList<Product> favorites = new ArrayList<>();
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            URL url = new URL(GlobalVar.mainURL + "getFavoriteProducts?userId="+userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            JSONArray productsArray = new JSONObject(builder.toString()).getJSONArray("favoriteProducts");

            for (int i = 0; i < productsArray.length(); i++) {
                favorites.add(gson.fromJson(productsArray.getJSONObject(i).toString(), Product.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return favorites;
    }
}
