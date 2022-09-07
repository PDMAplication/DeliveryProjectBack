package br.edu.ifpb.projeto.Api;
import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.repository.UsuarioRepository;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResources {

    @Context
    private UriInfo uriInfo;

    @Context
    ResourceContext resourceContext;
    @Inject
    private UsuarioRepository usuarios;

    public UsuarioResources(UriInfo uriInfo, UsuarioRepository usuarios) {
        this.uriInfo = uriInfo;
        this.usuarios = usuarios;
    }

    @GET
    @Path("{key}")
    public Response buscar(@PathParam("key") String key){
        Usuario usuario = usuarios.buscar(key);
        if(usuario == null){
            return Response.noContent()
                    .build();
        }
        return Response.ok(usuario).build();
    }

    @POST
    public Response criar(JsonObject json, @Context UriInfo uriInfo){
        Usuario usuario = new Usuario(
                json.getString("nome"),
                json.getString("email"),
                json.getString("senha")
        );
        usuarios.novoUsuario(usuario);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(usuario.getEmail()))
                .build();
        return Response.created(location) //201
                .entity(usuario)
                .build();
    }

    @POST
    @Path("login")
    public Response logar(JsonObject json){
        String key = usuarios.logar(
                json.getString("email"),
                json.getString("senha"));

        if("".equals(key.trim())){
            return Response.noContent()
                    .build();
        }
        return Response.accepted(key).build();
    }

}
