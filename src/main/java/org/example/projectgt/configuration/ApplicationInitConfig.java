package org.example.projectgt.configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.projectgt.entity.Users;
import org.example.projectgt.enums.Role;
import org.example.projectgt.repository.UsersRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UsersRepository usersRepository) {
        return args -> {
            if(usersRepository.findByEmail("admin@gmail.com").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                Users users = Users.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123456@Aa"))
                        //.roles(roles)
                        .build();

                usersRepository.save(users);

                log.warn("Admin account created with password 123456@Aa, please change it");
            }
        };
    }
}
