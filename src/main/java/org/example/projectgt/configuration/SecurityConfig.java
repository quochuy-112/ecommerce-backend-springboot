//package org.example.projectgt.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.crypto.spec.SecretKeySpec;
//
//@Configuration
////@EnableWebSecurity
//public class SecurityConfig {
//    private final String[] PUBLIC_ENDPOINT = {"/user"};
//
//    @Value("${jwt.signerKey}")
//    private String signerKey;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
//                .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
//                .anyRequest().authenticated()
//        );
//
//        httpSecurity.oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
//                httpSecurityOAuth2ResourceServerConfigurer.jwt(jwtConfigurer -> jwtConfigurer
//                        .decoder(jwtDecoder())
//                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//        );
//
//        return httpSecurity.build();
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//    }
//
//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//
//        return jwtAuthenticationConverter;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }
//}
