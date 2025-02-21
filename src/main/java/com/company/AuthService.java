package com.company;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public String getRegister(Users user) {
        return authRepository.getRegister(user);
    }


    public String verify(AuthVerification authVerification) {
        return authRepository.verify(authVerification);
    }

    public String login(Users user) {
        return authRepository.login(user);
    }
}
