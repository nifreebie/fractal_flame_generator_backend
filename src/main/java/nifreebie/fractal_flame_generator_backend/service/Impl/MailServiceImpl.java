package nifreebie.fractal_flame_generator_backend.service.Impl;

import nifreebie.fractal_flame_generator_backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Регистрация завершена");
        message.setText("Добро пожаловать, " + username + "! Вы успешно зарегистрировались в нашей системе.");
        mailSender.send(message);
    }
}
