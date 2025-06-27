package org.example.projectgt.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.example.projectgt.dto.request.AuthenticationRequest;
import org.example.projectgt.dto.request.IntrospectRequest;
import org.example.projectgt.dto.response.AuthenticationResponse;
import org.example.projectgt.dto.response.IntrospectResponse;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationService {
    UsersRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        var verified = signedJWT.verify(jwsVerifier);

        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .valid(verified && expirationDate.after(new Date()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws JOSEException {
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user.getEmail());

        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    private String generateToken(String email) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("projectgt.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now()
                        .plus(1, ChronoUnit.HOURS)
                        .toEpochMilli()
                ))
                .claim("customClaim", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

        return jwsObject.serialize();
    }
}
