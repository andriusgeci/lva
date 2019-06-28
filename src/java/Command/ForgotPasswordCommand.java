package Command;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotPasswordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        // take email from user input
        String to = request.getParameter("email");
        final String username = "lvamoviess@gmail.com";
        final String password = "fkxszaptnqodcqye";

        try {
            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(username));
            InternetAddress[] toAddresses = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Password Reset LVAMovies");
            msg.setSentDate(new Date());
//            msg.setText("To reset password please click this link: http://lvamovies.azurewebsites.net/home/changePassword.jsp");
            msg.setText("To reset password please click this link: http://localhost:8084/LVA_Movies/changePassword.jsp");

            // sends the e-mail
            Transport.send(msg);
            forwardToJsp = "/checkEmail.jsp";
        } catch (MessagingException e) {
            forwardToJsp = "/error.html";
            throw new RuntimeException(e);
        }
        return forwardToJsp;
    }
}
