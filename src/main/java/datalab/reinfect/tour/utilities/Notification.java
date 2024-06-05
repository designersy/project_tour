package datalab.reinfect.tour.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Notification {

    private final JavaMailSender mailSender;

    public void mailSend(String from, String email, String subject, String htmlContent) {

        MimeMessagePreparator message = mime -> {
            MimeMessageHelper helper = new MimeMessageHelper(mime, true);

            helper.setTo(email);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
        };

        mailSender.send(message);
    }

}
