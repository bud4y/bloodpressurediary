package edu.progmatic.blood_presssure_diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class BloodPresssureDiaryApplication {

    @Autowired
    private JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(BloodPresssureDiaryApplication.class, args);
    }

//    public void sendEmail(String email) {
//        System.out.println("Sending Email...");
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(email, "toth.csaba.szeged@gmail.com", "budaibence@yahoo.com");
//        msg.setSubject("Testing Bloodperssure from Spring Boot");
//        msg.setText("Hello World \n Spring Boot Email");
//
//        javaMailSender.send(msg);
//
//    }
}
