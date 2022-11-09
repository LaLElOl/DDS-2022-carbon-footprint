package controllers;

import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import models.dominio.persona.Contacto;
import models.dominio.persona.Miembro;
import models.dominio.persona.TipoDoc;
import models.dominio.transporte.Ubicacion;
import models.repositorios.RepositorioDeMiembros;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MiembroController {

    private RepositorioDeMiembros repositorioDeMiembros;

    public MiembroController(){repositorioDeMiembros = new RepositorioDeMiembros();}

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Miembro> todosLosMiembros = this.repositorioDeMiembros.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembros", todosLosMiembros);
        }}, "miembros.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = this.repositorioDeMiembros.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembro", miembroBuscado);
            //put("cant_tareas", servicioBuscado.cantTareas());//TRAMOS,TRAYECTOS?
        }}, "miembro.hbs"); // TODO REVISAR
    }

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_miembro.hbs");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        Miembro miembro = new Miembro();
        Ubicacion ubicacion = new Ubicacion();
        Contacto contacto = new Contacto();


        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        usuario.setTipoUsuario(TipoUsuario.MIEMBRO);
        miembro.setNombre(request.queryParams("nombre"));
        miembro.setApellido(request.queryParams("apellido"));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        TipoDoc tipoDoc = TipoDoc.valueOf(request.queryParams("tipo_doc").toUpperCase(Locale.ROOT));
        miembro.setNumDoc(Integer.valueOf(request.queryParams("nro_doc")));
        contacto.setNumTelefono(request.queryParams("telefono"));
        contacto.setEmail(request.queryParams("email"));
        usuario.setEmail(request.queryParams("email"));
        miembro.setUsuario(usuario);
        miembro.setDomicilio(ubicacion);
        miembro.setContacto(contacto);
        miembro.setTipoDoc(tipoDoc);

        this.repositorioDeMiembros.guardar(miembro);

        response.redirect("/home_miembro");
        return response;
    }

    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = this.repositorioDeMiembros.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembro", miembroBuscado);

        }}, "form_miembro.hbs");//TODO REVISAR

    }


    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = this.repositorioDeMiembros.buscar(new Integer(idBuscado));
        Ubicacion ubicacion = new Ubicacion();
        Contacto contacto = new Contacto();

        miembroBuscado.setNombre(request.queryParams("nombre"));
        miembroBuscado.setApellido(request.queryParams("apellido"));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        TipoDoc tipoDoc = TipoDoc.valueOf(request.queryParams("tipo_doc").toUpperCase(Locale.ROOT));
        miembroBuscado.setNumDoc(Integer.valueOf(request.queryParams("nro_doc")));
        contacto.setNumTelefono(request.queryParams("numero_telefono"));
        contacto.setEmail(request.queryParams("email"));
        miembroBuscado.setDomicilio(ubicacion);
        miembroBuscado.setContacto(contacto);
        miembroBuscado.setTipoDoc(tipoDoc);

        this.repositorioDeMiembros.guardar(miembroBuscado);

        response.redirect("/index");
        return response;
    }
}
