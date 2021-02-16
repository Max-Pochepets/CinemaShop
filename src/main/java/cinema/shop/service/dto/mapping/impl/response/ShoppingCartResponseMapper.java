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
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setId(shoppingCart.getId());
        responseDto.setUserEmail(shoppingCart.getUser().getEmail());
        responseDto.setTicketIds(shoppingCart.getTickets().stream()
                                                .map(Ticket::getId)
                                                .collect(Collectors.toList()));
        return responseDto;
    }
}
