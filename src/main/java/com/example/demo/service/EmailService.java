package com.example.demo.service;

import com.example.demo.POJOs.EmailRequest;
import com.example.demo.config.EmailSenderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
public class EmailService {

    private final EmailSenderConfig emailSenderConfig;

    @Autowired
    public EmailService( EmailSenderConfig emailSenderConfig) {
        this.emailSenderConfig = emailSenderConfig;
    }

    public void sendMailMultipart(String toEmail, String subject, String message, File file) throws MessagingException {

        MimeMessage mimeMessage = emailSenderConfig.getJavaMailSender().createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailSenderConfig.getFromAddress());
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(message);

        if(file != null){
            helper.addAttachment(file.getName(), file);
        }
        emailSenderConfig.getJavaMailSender().send(mimeMessage);
    }


    // sending example welcome mail to new registered customers.
    public void sendMail(EmailRequest request) throws MessagingException {
        sendMailMultipart(
                request.getToMail(),
                "Welcome to Spring Boot Example Mail Operator",
                "Registration operation was made successfully. \n" +
                        "Date: " + request.getDate() + "\n", null);
    }

    public void sendMail(String toEmail, String subject, String message) throws MessagingException {
        sendMailMultipart(toEmail, subject, message, null);
    }

    public void sendMail(String toEmail, String subject, String message, File file) throws MessagingException {
        sendMailMultipart(toEmail, subject, message, file);
    }
}
