package com.trippy.back.services;

import com.trippy.back.repos.PasswordResetTokenRepo;
import com.trippy.back.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenService {
    @Autowired
    PasswordResetTokenRepo passwordResetTokenRepo;

    @Autowired
    UserRepo userRepo;

}
