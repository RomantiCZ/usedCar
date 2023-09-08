package cn.ikun.carshop.utils;


import cn.ikun.carshop.pojo.Email;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Async
    public void SendEmailByRental(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo(email.getTo());
        message.setSubject(email.setRentSubject());
        message.setText(email.setRentalText());

        mailSender.send(message);
    }

    @Async
    public void SendEmailBySale(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo(email.getTo());
        message.setSubject(email.setSaleSubject());
        message.setText(email.setSaleText());

        mailSender.send(message);
    }


}
