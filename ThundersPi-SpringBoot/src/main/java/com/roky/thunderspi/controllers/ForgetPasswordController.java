package com.roky.thunderspi.controllers;


import com.roky.thunderspi.dto.ResetPass;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.services.UserServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reset")
public class ForgetPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@Param(value = "email") String email) {

        String token = RandomString.make(30);
        System.out.println(email);
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = "http://localhost:4200" + "/reset?token=" + token;
            sendEmail(email, resetPasswordLink);


        } catch (Exception ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("margoumik@gmail.com", "\n" +
                "Sakly Textile Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public ResponseEntity<?> showResetPasswordForm(@Param(value = "token") String token) {
        User customer = userService.getByResetPasswordToken(token);


        if (customer == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> processResetPassword(@RequestBody ResetPass reset) {
        String token = reset.getToken();
        String password = reset.getPassword();

        User customer = userService.getByResetPasswordToken(token);


        if (customer == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.updatePassword(customer, password);


        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}

