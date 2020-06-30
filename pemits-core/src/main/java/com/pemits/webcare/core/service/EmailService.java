package com.pemits.webcare.core.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pemits.webcare.core.constant.EmailConstant;
import com.pemits.webcare.core.dto.EmailDto;

/**
 * @author Elvin Shrestha on 6/30/2020
 */
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void send(final EmailDto email) {
        new Thread(() -> sendEmail(email)).start();
    }

    private void sendEmail(final EmailDto email) {
        this.javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setSubject(email.getTemplate().get());
            message.setTo(email.getTo());
            message.setFrom(email.getFrom());

            final Context ctx = new Context();
            ctx.setVariable("data", email);
            String content = templateEngine
                .process(EmailConstant.MAIL.get(email.getTemplate()), ctx);
            message.setText(content, true);
        });
    }

}
