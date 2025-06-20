package com.Ecom.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendWelcomeEmail(String toEmail, String fname, String lname) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail); // recipient
            message.setSubject("Welcome to GadgetHub"); // subject
            message.setText("Hi " + fname + " " + lname + ",\n\n" +
                    "Thanks for registering with us via Google.\n" +
                    "We're happy to have you in our site \n\n" +
                    "CEO- Mr Rohit");

            message.setFrom("mrrohit2k25@gmail.com");
            mailSender.send(message);

            System.out.println("Email sent to " + toEmail);
        } catch (Exception e) {
            System.err.println("Error sending email:");
            e.printStackTrace();
        }
    }
}
