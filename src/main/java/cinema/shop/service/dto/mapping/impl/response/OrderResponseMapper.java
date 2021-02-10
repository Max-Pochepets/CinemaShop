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
    @Override
    public OrderResponseDto toDto(Order object) {
        OrderResponseDto responseDto = new OrderResponseDto();

        responseDto.setId(object.getId());
        responseDto.getTicketsId().addAll(object.getTickets().stream()
                                                .map(Ticket::getId)
                                                .collect(Collectors.toList()));
        responseDto.setOrderDate(object.getOrderDate().format(DateTimeFormatter
                                                                .ofPattern("dd.MM.yyyy HH:mm")));
        responseDto.setUserEmail(object.getUser().getEmail());

        return responseDto;
    }
}
