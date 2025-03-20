package com.company.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    //In springBoot authentication is done with username by default

  @NotBlank(message="email should not be blank")
  @Email(message="email must be in the format")
    private String username; //in username we'll store email

//    @NotBlank(message="password should not be blank")
//    @Size(min=8,message="password must be of minimum 8 character")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
