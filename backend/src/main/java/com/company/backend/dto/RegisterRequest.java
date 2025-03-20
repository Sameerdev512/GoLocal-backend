package com.company.backend.dto;

import com.company.backend.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.company.backend.entity.Role.USER;

@Getter
@Setter
public class RegisterRequest {
    //passing email in username to make authenticate the user from email
    @NotBlank(message="email should not be blank")
    @Email(message="email should be in format")
    private String username;


//    @Size(min=8,message="password should be minimum of 8 characters")
//    @NotBlank(message="password should not be blank")
    private String password;

    private Role role;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
