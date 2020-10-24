package com.trippy.back.services;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.PasswordResetToken;
import com.trippy.back.repos.PasswordResetTokenRepo;
import com.trippy.back.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetTokenService {
    @Autowired
    PasswordResetTokenRepo passwordResetTokenRepo;

    @Autowired
    UserRepo userRepo;

    public void createPasswordResetToken(Account account, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, account);
        passwordResetTokenRepo.save(myToken);
    }

    public SimpleMailMessage constructResetTokenEmail(String token, Account account){
        String url = "Here is your reset email: http://localhost:8080/#/changepassword?token=" + token;

        return constructEmail("Reset Password", url, account);
    }

    private SimpleMailMessage constructEmail(String subject, String body, Account account){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(account.getEmail());
        email.setFrom("Trippy");
        return email;
    }

    public PasswordResetToken getPasswordResetToken(final String token){
        return passwordResetTokenRepo.findByToken(token);
    }

    public Optional<Account> getUserByPasswordResetToken(final String token){
        return Optional.ofNullable(passwordResetTokenRepo.findByToken(token).getAccount());
    }
}
