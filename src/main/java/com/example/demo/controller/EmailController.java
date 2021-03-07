package com.example.demo.controller;


import com.example.demo.pojo.EmailRequest;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@CrossOrigin
public class EmailController {


    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping(value = "/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        try {
            emailService.sendMail(emailRequest);
            return ResponseEntity.ok().body("Email is sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred");
        }
    }


    // for testing purposes for now
    @PostMapping(value = "/testSendEmail")
    public ResponseEntity<?> testSendEmail(@RequestBody EmailRequest emailRequest){
        try {
            // for test
            emailService.sendMail("receiverMail@examplemail.com", "Test Subject 123abc", "Test Message 987");
            return ResponseEntity.ok().body("Email is sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred");
        }
    }
}
