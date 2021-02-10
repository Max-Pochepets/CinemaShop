package cinema.shop.service.dto.mapping.impl.response;

import cinema.shop.model.ShoppingCart;
import cinema.shop.model.Ticket;
import cinema.shop.model.dto.response.ShoppingCartResponseDto;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartResponseMapper
        implements DtoResponseMapper<ShoppingCartResponseDto, ShoppingCart> {
    @Override
    public ShoppingCartResponseDto toDto(ShoppingCart object) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();

        responseDto.setId(object.getId());
        responseDto.setUserEmail(object.getUser().getEmail());
        responseDto.getTicketsId().addAll(object.getTickets().stream()
                                                .map(Ticket::getId)
                                                .collect(Collectors.toList()));

        return responseDto;
    }
}
