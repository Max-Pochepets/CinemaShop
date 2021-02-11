package cinema.shop.model.dto.request;

import cinema.shop.lib.annotations.ValidEmail;
import cinema.shop.lib.annotations.ValidPassword;
import jakarta.validation.constraints.Size;

@ValidPassword(field = "password", fieldMatch = "repeatPassword")
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 4)
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
