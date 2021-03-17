package com.website.demo.emails;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private EmailConfiguration emailConfiguration;

    public FeedbackController(EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

    @PostMapping
    public void sendEmail() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("projektfunkcyjny@gmail.com");
        mailSender.setPassword("Pr0j3ktfunkcyjny!");
        mailSender.setPort(587);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("hakowe4631@grokleft.com");
        mailMessage.setSubject("emanko");
        mailMessage.setText("WITAJ!");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {

            Thread t1 = new Thread(()->{
                mailSender.send(mailMessage);
            });
            Thread t2 = new Thread(()->{
                while(t1.isAlive()){
                }
                long end = System.currentTimeMillis();
                System.out.println(t1.getName() + " is dead!" + (end - start));

            });
            t1.start();
            t2.start();
        }


    }

}
