package datalab.reinfect.tour.utilities;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Notification {

    @Value("${spring.application.from}")
    private String from;

    private final Common common;
    private final JavaMailSender mailSender;

    public void mailSend(String email, String subject, String template, Map<String, Object> data) throws IOException, MessagingException {

        String htmlContent = common.readHtmlEmailResource(template);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String placeHolder = "[[" + entry.getKey() + "]]";
            htmlContent = htmlContent.replace(placeHolder, String.valueOf(entry.getValue()));
        }

        String finalHtmlContent = htmlContent;

        MimeMessagePreparator message = mime -> {
            MimeMessageHelper helper = new MimeMessageHelper(mime, true);

            helper.setTo(email);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(finalHtmlContent, true);
        };

        mailSender.send(message);
    }

}
