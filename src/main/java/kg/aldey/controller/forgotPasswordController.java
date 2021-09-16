package kg.aldey.controller;

import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.products.SearchText;
import kg.aldey.repository.CookiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static kg.aldey.controller.ProductsController.getCategoriesByStoreId;

@Controller
public class forgotPasswordController {

    @Autowired
    CookiesRepository cookiesRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/forgotPassword")
    public String register(Model model,
                           @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
                           HttpServletResponse response) {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", GlobalVar.storeName + " - Восстановление пароля");
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());

        return "forgotPassword";
    }

    @RequestMapping(value = "/forgotPasswordPost", method = RequestMethod.POST)
    public void getEmailForgotPassword(

            @RequestParam("email") String email,

            Model model,
            @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
            HttpServletResponse response) throws IOException {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        model.addAttribute("title", GlobalVar.storeName + " - Восстановление пароля");
        model.addAttribute("searchForm", new SearchText());
        model.addAttribute("categoryName", getCategoriesByStoreId());

        /*System.out.println(email);*/
        sendJSONtoResetPassword(email);

    }

    private void sendJSONtoResetPassword(String email) throws IOException {

        URL url = new URL(GlobalVar.mainURL+"getPasswordByEmail?mailsList="+email+"&storeId="+GlobalVar.storeId);

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            /*System.out.println(response.toString());*/
        }
    }

}
