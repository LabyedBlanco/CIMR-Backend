package com.example.CIMR_DSI;

import java.io.UnsupportedEncodingException;

import org.springframework.data.repository.query.Param;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.LoginResponse;
import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Service.AuthenticationService;
import com.example.CIMR_DSI.Service.JwtService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8090")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody Collaborateur loginCollaborateurDto) {
        Collaborateur authenticatedCollaborateur = authenticationService.authenticate(loginCollaborateurDto);

        String jwtToken = jwtService.generateToken(authenticatedCollaborateur);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setId(authenticatedCollaborateur.getId());
        if (authenticatedCollaborateur.isEnabled()) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return null;
        }

    }

    @PostMapping("/signup")
    public String processRegister(@RequestBody Collaborateur user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        user.setRole("user");
        authenticationService.register(user, getSiteURL(request));
        return "register_success";
    }

    @GetMapping("/verify")
    public RedirectView verifyUser(@Param("code") String code, RedirectAttributes attributes) {
        boolean isVerified = authenticationService.verify(code);

        String redirectUrl = isVerified ? "http://localhost:4200/login"
                : "http://localhost:4200/login";

        attributes.addFlashAttribute("verified", isVerified);

        return new RedirectView(redirectUrl);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}