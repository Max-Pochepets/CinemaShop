package cinema.shop.service.impl;

import cinema.shop.dao.ShoppingCartDao;
import cinema.shop.dao.TicketDao;
import cinema.shop.model.MovieSession;
import cinema.shop.model.ShoppingCart;
import cinema.shop.model.Ticket;
import cinema.shop.model.User;
import cinema.shop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final TicketDao ticketDao;
    private final ShoppingCartDao shoppingCartDao;

    public ShoppingCartServiceImpl(TicketDao ticketDao, ShoppingCartDao shoppingCartDao) {
        this.ticketDao = ticketDao;
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ShoppingCart shoppingCartByUser = getByUser(user);
        shoppingCartByUser.getTickets().add(ticket);

        ticketDao.add(ticket);
        shoppingCartDao.update(shoppingCartByUser);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
