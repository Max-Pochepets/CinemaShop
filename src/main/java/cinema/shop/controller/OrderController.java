package cinema.shop.controller;

import cinema.shop.model.Order;
import cinema.shop.model.User;
import cinema.shop.model.dto.response.OrderResponseDto;
import cinema.shop.service.OrderService;
import cinema.shop.service.ShoppingCartService;
import cinema.shop.service.UserService;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final DtoResponseMapper<OrderResponseDto, Order> responseMapper;

    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService,
                           DtoResponseMapper<OrderResponseDto, Order> responseMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.responseMapper = responseMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow(()
                -> new RuntimeException("No user found with such email " + email + "."));
        orderService.completeOrder(shoppingCartService.getByUser(user));
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow(()
                -> new RuntimeException("There is no such user with email " + email + "."));
        return orderService.getOrdersHistory(user).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
