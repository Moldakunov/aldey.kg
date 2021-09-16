package kg.aldey.controller;

import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.model.buy.Order;
import kg.aldey.model.cart.Cart;
import kg.aldey.model.product.Product;
import kg.aldey.repository.CookiesRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CookiesRepository cookiesRepository;

    @Autowired
    EntityManager entityManager;

    @PutMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<?> addToCartNewProduct(@RequestParam String product,
                                                 @CookieValue(value = "JSESSIONID", defaultValue = "") String givenCookie) {
        Cart cart;
        if (cookiesRepository.findBySessionId(givenCookie) != null) {
            cart = cookiesRepository.findBySessionId(givenCookie);
            cart.addProduct(product);
        } else {
            cart = new Cart(givenCookie, product);
        }

        entityManager.persist(cart);
//        return ResponseEntity.ok(Arrays.asList(cart.getProducts().split(",")).size());
        return ResponseEntity.ok("added");
    }

    @GetMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<?> getFromCart(@CookieValue(value = "JSESSIONID", defaultValue = "") String givenCookie) {
        try {
            List<Product> products = ProductsController.getProductsByIds(
                    cookiesRepository.getAllBySessionId(givenCookie)
                            .stream()
                            .map(cart -> String.valueOf(cart.getProducts()))
                            .collect(Collectors.joining(",", "", "")));
            return ResponseEntity.ok(products);
        } catch (JSONException e) {
            e.printStackTrace();
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }

    /*@DeleteMapping(value = "/cart")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> deleteFromCart(@CookieValue(value = "JSESSIONID", defaultValue = "") String givenCookie) {
        cookiesRepository.removeCartBySessionId(givenCookie);
        return ResponseEntity.ok("ok");
    }*/

    @DeleteMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<?> deleteFromCart(@CookieValue(value = "JSESSIONID", defaultValue = "") String givenCookie,
                                            @RequestParam String product) {
        if (cookiesRepository.findBySessionId(givenCookie) == null) {
            return ResponseEntity.ok("[]");
        } else if (cookiesRepository.findBySessionId(givenCookie).getProducts().contains(",")) {
            Cart cart = cookiesRepository.findBySessionId(givenCookie);

            cart.removeProduct(product);
            entityManager.persist(cart);
            return ResponseEntity.ok("removed");
        } else if (product == null || product.isEmpty()) {
            entityManager.remove(cookiesRepository.findBySessionId(givenCookie));
            return ResponseEntity.ok("removed");
        } else {
            cookiesRepository.removeCartBySessionId(givenCookie);
            return ResponseEntity.ok("removed");
        }
    }

    @PostMapping(value = "/buy", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> buy(@CookieValue(value = "JSESSIONID", defaultValue = "") String givenCookie,
                                 @RequestBody Order order) {
        if (givenCookie == null || givenCookie.isEmpty()) {
            return ResponseEntity.ok("error");
        }

//        System.out.println("order: " + order.toString());
        return buy(order).equals("success") ? ResponseEntity.ok("success") : ResponseEntity.ok("error");
    }

    public String buy(Order order) {
        try {
            String response;
            URL url = new URL(GlobalVar.mainURL + "buyProductByUser?storeId=" + GlobalVar.storeId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            byte[] input = new JSONObject(order).toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();
            while ((response = reader.readLine()) != null) {
                builder.append(response.trim());
            }

/*            System.out.println(builder.toString().contains("success"));
            System.out.println(builder.toString().contains("error"));*/
            return builder.toString().contains("success") ? "success" : "error";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
