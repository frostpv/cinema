package com.dev.cinema.dto.user;

import com.dev.cinema.validator.ValidEmail;
import com.dev.cinema.validator.ValidPassword;
import lombok.Data;

@Data
@ValidPassword(password = "password", repeatPassword = "repeatPassword")
public class UserRegistrationDto {
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;
}
