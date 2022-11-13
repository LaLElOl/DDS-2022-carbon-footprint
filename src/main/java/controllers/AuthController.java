package controllers;
import helpers.EntityManagerHelper;
import java.util.List;
import java.util.Map;
import spark.Request;

public abstract class AuthController {

    void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){

            List persona = EntityManagerHelper.createQuery("from Persona " +
                            "where usuario.id ="+request.session().attribute("id"))
                    .getResultList();

            List admin = EntityManagerHelper.createQuery("from Organizacion " +
                            "where usuario.id ="+request.session().attribute("id"))
                    .getResultList();
            if(!persona.isEmpty()){
                parametros.put("persona", persona.get(0));
            }
            else if(!admin.isEmpty()){
                parametros.put("organizacion", admin.get(0));
            }
//            else {
//                parametros.put("usuario", usuario);
//            }

        }
    }
}
