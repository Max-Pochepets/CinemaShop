package cinema.shop.service.abstraction.src;

import java.util.List;

public interface Service<T> {
    T add(T movie);

    List<T> getAll();
}
