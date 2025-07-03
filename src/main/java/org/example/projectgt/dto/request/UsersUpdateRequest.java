package org.example.projectgt.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.validator.BirthdayConstraint;

import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersUpdateRequest {
    String password;
    List<String> roles;

    String firstName;

    String lastName;

    @BirthdayConstraint(min = 18, message = "INVALID_BIRTHDATE")
    LocalDate birthDate;
}
