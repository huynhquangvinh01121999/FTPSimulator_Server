package handlers;

/**
 *
 * @author HUỲNH QUANG VINH
 */
import bll.helpers.ThreadRandoms;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MailSender;

public class SendMail extends SendMailBase {

    public SendMail(int code, MailSender mailSender) {
        setVerifyCode(code);
        SendEmail(mailSender);
    }

    @Override
    public void SendEmail(MailSender mailSender) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //kích hoạt chuẩn bảo mật STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSender.getFromEmail(), mailSender.getPassword());
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);

        try {
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.addHeader("format", "flowed");  // thiết lập trạng thái follow mail
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            try {
                msg.setFrom(new InternetAddress(mailSender.getFromEmail(),
                        mailSender.getAliasAddress()));
            } catch (UnsupportedEncodingException ex) {
                System.err.println("Có lỗi xảy ra khi giải mã URL - UnsupportedEncodingException");
                Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            }

            msg.setReplyTo(InternetAddress.parse(mailSender.getFromEmail(), false));

            msg.setSubject(mailSender.getSubject(), "UTF-8");

            msg.setText(mailSender.getBody() + verifyCode, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailSender.getToEmail(), false));
            Transport.send(msg);
            System.out.println("Gửi mail thành công.!!!");
        } catch (MessagingException ex) {
            System.err.println("Có lỗi xảy ra khi cấu hình MimeMessage");
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        System.out.println("Mail is being sent...");

        new SendMail(new ThreadRandoms().generateCode(), new MailSender("huynhquangvinh01121999@gmail.com"));
    }
}
