package org.example.projectgt.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.validator.BirthdayConstraint;

import java.time.LocalDate;

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
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])", message = "PASSWORD_INVALID")
    String password;

    String firstName;

    String lastName;

    @BirthdayConstraint(min = 18, message = "INVALID_BIRTHDATE")
    LocalDate birthDate;
}
