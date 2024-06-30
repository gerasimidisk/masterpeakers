package gr.aueb.cf.masterpeakers.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class RegistrationDTO {
    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 3, max = 7, message = "Password must be between 3 and 7 characters")
    private String password;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    private String email;
}


