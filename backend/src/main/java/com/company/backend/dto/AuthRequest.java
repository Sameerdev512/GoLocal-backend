package com.company.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    @NotBlank(message="email should not be blank")
    @Email(message="email should be in the format")
    private String email;

//  @NotBlank(message="username should not be blank")
    private String username;

    @NotBlank(message="password should not be blank")
    @Size(min=8,message="password must be of minimum 8 character")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
