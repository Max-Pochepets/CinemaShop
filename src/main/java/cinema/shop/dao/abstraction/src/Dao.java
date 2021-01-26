package cinema.shop.dao.abstraction.src;

import java.util.List;

public interface Dao<T> {
    T add(T movie);

    List<T> getAll();
}
