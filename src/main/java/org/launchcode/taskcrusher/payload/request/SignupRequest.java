package org.launchcode.taskcrusher.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {

    @NotNull(message = "Username cannot be blank")
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be 3-20 characters long")
    private String username;

    @NotNull(message = "Email cannot be blank")
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    private Set<String> role;

    @NotNull(message = "Password cannot be blank")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 40, message = "Password must be 8-40 characters long")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
