package services.mediosNotiicacion;

import dominio.persona.Contacto;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email implements MedioNotificacion{

    public void notificar(Contacto contacto, String mensaje) {

        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(propiedad);

        String correoRemitente = "rescatepatitastpdds@gmail.com";
        String contrasena = "lossinnombre";
        String asunto = "Rescate Patitas";

        MimeMessage mail = new MimeMessage(sesion);

        try {
            mail.setFrom(new InternetAddress(correoRemitente));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(contacto.getEmail()));
            mail.setSubject(asunto);
            mail.setText(mensaje);

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoRemitente, contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();

            System.out.println("Correo Enviado\n");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
