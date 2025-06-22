package org.example.projectgt.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersCreation {
    @Email(message = "EMAIL_INVALID")
    String email;

    @NotNull(message = "PASSWORD_INVALID")
    @Size(min = 8, max = 32,message = "PASSWORD_INVALID")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,32}$", message = "PASSWORD_INVALID")
    String password;
}
