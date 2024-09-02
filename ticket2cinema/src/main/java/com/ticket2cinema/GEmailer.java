package com.ticket2cinema;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class GEmailer {

    public boolean sendEmail(String to, String from, String subject, String text) {
        boolean flag = false;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = System.getenv("GMAIL_USERNAME");
        String password = System.getenv("GMAIL_APP_PASSWORD");


        if (username == null || password == null) {
            System.err.println("Gmail credentials are not set in the environment variables.");
            return flag;
        }

        // Session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            flag = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return flag;
    }

}
