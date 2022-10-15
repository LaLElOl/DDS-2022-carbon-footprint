package models.services.mediosNotiicacion;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import models.dominio.persona.Contacto;

public class Whatsapp implements MedioNotificacion{

    public void notificar(Contacto contacto, String mensaje) {

        String ACCOUNT_SID = "ACe762ffd4d3ff9ed0ab2390e757002aba";
        String AUTH_TOKEN = "2cadbc790d44584797201356c9052a93";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:+549"+contacto.getNumTelefono()),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        mensaje)
                .create();
    }
}
