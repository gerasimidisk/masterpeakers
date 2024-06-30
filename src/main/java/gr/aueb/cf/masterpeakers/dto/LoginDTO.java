package gr.aueb.cf.masterpeakers.dto;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
public class LoginDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private boolean rememberMe;
}
