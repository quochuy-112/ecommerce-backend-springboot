package org.example.projectgt.controller;

import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.AuthenticationRequest;
import org.example.projectgt.dto.request.IntrospectRequest;
import org.example.projectgt.dto.request.LogoutRequest;
import org.example.projectgt.dto.request.RefreshRequest;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.AuthenticationResponse;
import org.example.projectgt.dto.response.IntrospectResponse;
import org.example.projectgt.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws JOSEException {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(authenticationService.authenticate(authenticationRequest));

        return apiResponse;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@Valid @RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(authenticationService.introspect(introspectRequest));

        return apiResponse;
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> authenticate(@Valid @RequestBody RefreshRequest refreshRequest) throws JOSEException, ParseException {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(authenticationService.refreshToken(refreshRequest));

        return apiResponse;
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@Valid @RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {

        authenticationService.logout(logoutRequest);

        return ApiResponse.<Void>builder()
                .build();
    }

}
