package kg.aldey.model.cart;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String sessionId;

    @Column(length = 1000)
    private String products;

    public Cart(String sessionId, String products) {
        this.sessionId = sessionId;
        this.products = products;
    }

    public Cart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public void addProduct(String product) {
        List<String> list = new LinkedList<>(Arrays.asList(this.products.split(",")));
        if (!list.contains(product)) {
            list.add(product);
            this.products = String.join(",", list);
        }
    }

    public void removeProduct(String product) {
        List<String> list = new LinkedList<>(Arrays.asList(this.products.split(",")));
        if (list.contains(product)) {
            list.remove(product);
            this.products = String.join(",", list);
        };
    }

    @Override
    public String toString() {
        return "Cart{" +
                "sessionId='" + sessionId + '\'' +
                ", products='" + products + '\'' +
                '}';
    }
}
