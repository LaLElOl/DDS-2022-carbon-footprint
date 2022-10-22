package controllers;

import models.dominio.Usuario;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import models.dominio.persona.Contacto;
import models.dominio.persona.Miembro;
import models.dominio.persona.TipoDoc;
import models.dominio.transporte.Ubicacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class MiembroController {

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_miembro.html");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        Miembro miembro = new Miembro();
        Ubicacion ubicacion = new Ubicacion();
        Contacto contacto = new Contacto();


        usuario.setNickname(request.queryParams("usuario"));
        //usuario.setContrasenia(request.queryParams("contrasenia"));
        miembro.setNombre(request.queryParams("nombre"));
        miembro.setApellido(request.queryParams("apellido"));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        TipoDoc tipoDoc = TipoDoc.valueOf(request.queryParams("tipo_doc").toUpperCase(Locale.ROOT));
        miembro.setNumDoc(Integer.valueOf(request.queryParams("numero_documento")));
        contacto.setNumTelefono(request.queryParams("numero_telefono"));
        contacto.setEmail(request.queryParams("email"));


        System.out.println(usuario.getNickname());
        //System.out.println(usuario.getContrasenia());
        System.out.println(miembro.getNombre());
        System.out.println(miembro.getApellido());
        System.out.println(ubicacion.getCalle());
        System.out.println(ubicacion.getAltura());
        System.out.println(tipoDoc);
        System.out.println(miembro.getNumDoc());
        System.out.println(contacto.getNumTelefono());
        System.out.println(contacto.getEmail());

        response.redirect("/alta_miembro");
        return response;
    }
}
