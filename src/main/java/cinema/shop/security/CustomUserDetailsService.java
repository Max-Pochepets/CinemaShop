package cinema.shop.security;

import cinema.shop.model.User;
import cinema.shop.service.UserService;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userByEmail = userService.findByEmail(email);
        if (userByEmail.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user by email " + email + ". ");
        }

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(userByEmail.get().getPassword());
        builder.roles(userByEmail.get().getRole().toString());

        return builder.build();
    }
}
