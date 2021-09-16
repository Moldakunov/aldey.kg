package kg.aldey.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.products.SearchText;
import kg.aldey.repository.CookiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static kg.aldey.controller.ProductsController.getCategoriesByStoreId;

@Controller
public class FastBuyController {

    @Autowired
    CookiesRepository cookiesRepository;

    @RequestMapping(value = "/fastBuy", method = RequestMethod.POST)
    public void getUserInfoForRegister(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("productId") String productId,
            Model model,
            @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
            HttpServletResponse response) throws IOException {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", GlobalVar.storeName + " - Регистрация");
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());

        /*System.out.println(phoneNumber);
        System.out.println(productId);*/
        sendRequestForFastBuy(phoneNumber, productId);
    }

    private void sendRequestForFastBuy(String phoneNumber, String productId) {
        URL url;
        try {
            String response;
            StringBuilder builder = new StringBuilder();
            Gson gson = new GsonBuilder().create();
            url = new URL("http://212.2.230.77:8080/v1/shoppingCart/buyShoppingCartFast?productId="+productId+
                    "&userId=-1&qty=1&storeId="+GlobalVar.storeId+"&phone="+phoneNumber);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
