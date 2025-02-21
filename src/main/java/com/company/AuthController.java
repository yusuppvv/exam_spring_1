package com.company;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Users user) {
        return authService.getRegister(user);

    }

    @PostMapping("/verification")
    public String verify(@RequestBody AuthVerification authVerification) {
        return authService.verify(authVerification);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return authService.login(user);
    }


}
