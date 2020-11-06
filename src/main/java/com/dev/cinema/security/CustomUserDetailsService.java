package com.dev.cinema.security;

import com.dev.cinema.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
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
    public UserDetails loadUserByUsername(String email) {
        com.dev.cinema.model.User user = userService.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Can't get user by email: " + email));
        UserBuilder builder = User.withUsername(email);
        builder.password(user.getPassword());
        String[] roles = user.getRoles().stream()
                .map(r -> r.getRoleName().toString())
                .toArray(String[]::new);
        builder.roles(roles);
        return builder.build();
    }
}

