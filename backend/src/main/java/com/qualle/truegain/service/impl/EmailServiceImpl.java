package com.qualle.truegain.service.impl;

import com.qualle.truegain.config.property.EmailProperties;
import com.qualle.truegain.model.email.UserEmail;
import com.qualle.truegain.model.email.VerificationCode;
import com.qualle.truegain.service.EmailService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("classpath:email/letter_code.html")
    private Resource letter;

    private final EmailProperties properties;
    private final Properties smtpProperties;
    private String content;

    public EmailServiceImpl(EmailProperties properties) {
        this.properties = properties;

        smtpProperties = new Properties();

        smtpProperties.put("mail.smtp.auth", true);
        smtpProperties.put("mail.smtp.starttls.enable", "true");
        smtpProperties.put("mail.smtp.host", "smtp.gmail.com");
        smtpProperties.put("mail.smtp.port", "587");
    }

    @PostConstruct
    private void init() {
        try {
            content = letter.getContentAsString(Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public VerificationCode sendVerificationLetter(UserEmail email) {

        VerificationCode verification = VerificationCode.builder()
                .code(generateCode())
                .build();

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getLogin(), properties.getPassword());
            }
        };

        Session session = Session.getInstance(smtpProperties,  authenticator);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getLogin()));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getEmail()));
            message.setSubject("Activate your True Gains account");

            String letterContent = content.replace("${code}", verification.getCode()+ ""); // todo optimize
            letterContent = letterContent.replace("${name}", email.getName());
            letterContent = letterContent.replace("${email}", "Qualle.inc@gmail.com");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(letterContent, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return verification;
    }

    private int generateCode(){
        return ThreadLocalRandom.current().nextInt(100000, 999999);
    }
}
