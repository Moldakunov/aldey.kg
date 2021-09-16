package kg.aldey.controller;

import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.model.Review;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> review(@Valid @RequestBody Review review) {
        System.out.println(review.toString());

        return ResponseEntity.ok(addReviewToProduct(review));
    }

    public String addReviewToProduct(Review review) {
        try {
            String response;
            StringBuilder builder = new StringBuilder();

            URL url = new URL(GlobalVar.mainURL
                    + "addReviewToProduct?text="
                    + URLEncoder.encode(review.getText(), "UTF-8") + "&productId="
                    + review.getProductId() + "&userName="
                    + URLEncoder.encode(review.getUserName(), "UTF-8") + "&rating="
                    + review.getRating());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = reader.readLine()) != null) {
                builder.append(response);
            }

            if (builder.toString().contains("success")) {
                return "success";
            }
            else {
                return "error";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
