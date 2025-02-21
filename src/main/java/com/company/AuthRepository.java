package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthRepository {
    private List<Users> usersList;
    private List<Integer> sentCodes;
    AuthStatus status = AuthStatus.REGISTER;
    @Value("azizbeky250@gmail.com")
    private String from;

    @Autowired
    JavaMailSender mailSender;

    public String getRegister(Users user) {
        for (Users u : usersList) {
            if (u.getEmail() == user.getEmail()) {
                return "User already exists";
            }
        }
        status = AuthStatus.REGISTER;
        usersList.add(user);
        Integer code = (int) (Math.random() * 900000) + 100000;
        sendSms(user.getEmail(), "Hi " + user.getEmail(), "Your verification code is : " + code, from);
        sentCodes.add(code);
        return "6-digital code sent to your email. Go to the page /api/verification .";
    }


    private void sendSms(String to, String subject, String text, String from) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String verify(AuthVerification authVerification) {
        for (int i = 0; i < sentCodes.getLast(); i++) {
            if (authVerification.getCode().equals(sentCodes.get(i))) {
                status = AuthStatus.ACTIVE;
                return "You successfully verified";
            }
        }
        return "Your email or your verification code is incorrect";
    }

    public String login(Users user) {
        for (Users u : usersList) {
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                return "Successfully logged in";
            }
        }
        return "Your email or your password is incorrect";
    }
}
