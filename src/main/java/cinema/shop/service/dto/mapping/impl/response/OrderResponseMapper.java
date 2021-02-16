package cinema.shop.service.dto.mapping.impl.response;

import cinema.shop.model.Order;
import cinema.shop.model.Ticket;
import cinema.shop.model.dto.response.OrderResponseDto;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapper implements DtoResponseMapper<OrderResponseDto, Order> {

    public static final String PATTERN = "dd.MM.yyyy HH:mm";

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setTicketIds(order.getTickets().stream()
                                                .map(Ticket::getId)
                                                .collect(Collectors.toList()));
        responseDto.setOrderDate(order.getOrderDate().format(DateTimeFormatter
                                                                .ofPattern(PATTERN)));
        responseDto.setUserEmail(order.getUser().getEmail());
        return responseDto;
    }
}
