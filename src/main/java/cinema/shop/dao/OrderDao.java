package cinema.shop.dao;

import cinema.shop.model.Order;
import cinema.shop.model.ShoppingCart;
import cinema.shop.model.User;
import java.util.List;

public interface OrderDao {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
