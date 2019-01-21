package grzegorzurbanski.notebook.notebooksbapp.mail;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMailSender implements FeedbackSender {

    private JavaMailSenderImpl javaMailSender;

    public FeedbackMailSender(Environment environment){
        javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(environment.getProperty("spring.mail.host"));

        javaMailSender.setUsername(environment.getProperty("spring.mail.username"));
        javaMailSender.setPassword(environment.getProperty("spring.mail.password"));
    }


    @Override
    public void sendFeedback(String from, String name, String feedback) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("grzesiiek@op.pl");
        message.setSubject("New feedback from" + name);
        message.setText(feedback);
        message.setFrom(from);

        this.javaMailSender.send(message);
    }
}
