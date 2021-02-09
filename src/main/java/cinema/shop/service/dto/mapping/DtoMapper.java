package cinema.shop.service.dto.mapping;

public interface DtoMapper<D, C> {
    D toDto(C object);

    C fromDto(D dto);
}
