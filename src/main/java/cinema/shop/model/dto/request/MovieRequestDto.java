package cinema.shop.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovieRequestDto {
    @NotNull
    @Size(min = 4)
    private String title;
    @NotNull
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
