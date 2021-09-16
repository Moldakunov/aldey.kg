package kg.aldey.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.config.session.Cookie;
import kg.aldey.model.UserForUpdatePassword;
import kg.aldey.repository.CookiesRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class UpdatePasswordController {

    @Autowired
    CookiesRepository cookiesRepository;

    @Autowired
    EntityManager entityManager;

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(
            @RequestParam("userId") String userId,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            Model model,
            @CookieValue(value = "JSESSIONID", defaultValue = "", required = false) String givenCookie,
            HttpServletResponse response) throws IOException {
        Cookie.workingWithCookie(cookiesRepository, givenCookie, response, model);

        int userIdInt=Integer.parseInt(userId);
        UserForUpdatePassword user = new UserForUpdatePassword(userIdInt, oldPassword, newPassword);
        return sendJSONtoUpdatePassword(user);
    }

    private String sendJSONtoUpdatePassword(UserForUpdatePassword user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonUser = mapper.writeValueAsString(user);
        /*System.out.println(jsonUser);*/

        URL url = new URL(GlobalVar.mainURL+"updatePassword");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonUser.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        String responseString;
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //System.out.println(response.toString());
            JSONObject jsonObj = new JSONObject(response.toString());
            responseString = jsonObj.getString("status");
        }
        if (responseString.equals("success")) {return "success";}
        if (responseString.equals("error")) {return "error";}
        return "errorerror";
    }
}
