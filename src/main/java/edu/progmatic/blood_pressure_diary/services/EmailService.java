package edu.progmatic.blood_pressure_diary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email, String name, String activation) {
        SimpleMailMessage message = null;

        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(email);
            message.setSubject("Sikeres regisztrálás");
            message.setText("Kedves " + name + "! \n \n Köszönjük, hogy regisztráltál az oldalunkra!" +
                    "\n Aktivácós link: https://bloodpressurediary.herokuapp.com/user/activation/" + activation);
            javaMailSender.send(message);

        } catch (Exception e) {
            //log.error("Hiba e-mail küldéskor az alábbi címre: " + email + "  " + e);
        }
    }
}
