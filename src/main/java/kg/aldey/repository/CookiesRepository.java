package kg.aldey.repository;

import kg.aldey.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookiesRepository extends JpaRepository<Cart, String> {
    List<Cart> getAllBySessionId(@Param("sessionId") String sessionId);
    Cart findBySessionId(@Param("sessionId") String sessionId);
    void removeCartBySessionId(@Param("sessionId") String sessionId);
    void removeCartBySessionIdAndProducts(@Param("sessionId") String sessionId, String products);
}
