package com.qualle.truegain.service;

import com.qualle.truegain.model.email.UserEmail;
import com.qualle.truegain.model.email.VerificationCode;

public interface EmailService {

    VerificationCode sendVerificationLetter(UserEmail email);
}
