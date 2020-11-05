package com.dev.cinema.util;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private final RoleService roleService;
    private final UserService userService;

    public DataInjector(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role userRole = Role.of("USER");
        Role adminRole = Role.of("ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);
        User admin = new User();
        admin.setEmail("frostpv@gmail.com");
        admin.setPassword("1234");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(admin);
    }

}

