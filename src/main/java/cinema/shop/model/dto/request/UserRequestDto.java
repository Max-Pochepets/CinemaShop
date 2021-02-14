package cinema.shop.model.dto.request;

import cinema.shop.lib.annotations.ValidEmail;
import cinema.shop.lib.annotations.ValidPassword;
import javax.validation.constraints.NotNull;

@ValidPassword(field = "password", fieldMatch = "repeatPassword")
public class UserRequestDto {
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;
    @NotNull
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
