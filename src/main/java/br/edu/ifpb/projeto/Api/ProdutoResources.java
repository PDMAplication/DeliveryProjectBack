package br.edu.ifpb.projeto.Api;

import br.edu.ifpb.projeto.repository.ProdutoRepository;
import br.edu.ifpb.projeto.model.Produto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("produtos") // ../api/produto
@Stateless
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProdutoResources {
    @Inject
    private ProdutoRepository produtos;
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Produto> listarTodos(){
        return produtos.todos();
    }

    @GET
    @Path("{id}") // /produtos/{id}
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Produto recuperarPorId(@PathParam("id") long id){
        return produtos.buscarPorId(id);
    }

    //    @Context UriInfo uriInfo;
    @POST
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criar(JsonObject json, @Context UriInfo uriInfo){
        Produto produto = produtos.criar(
                livroDe(json)
        );
        URI location = uriInfo.getAbsolutePathBuilder() //http://localhost:8080/src/api/produtos"
                .path(String.valueOf(produto.getId())) //http://localhost:8080/src/api/produtos
                .build();
//        URI location = URI.create(
//                "http://localhost:8080/src/api/produtos/"+produtos.getId()
//        );
        return Response.created(location) //201
                .entity(produto) // produtos
                .build();
    }
    @PUT
    @Path("{id}") // ../api/produtos/{id}
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response atualizar(@PathParam("id") long id,
                              Produto produto){
        Produto resposta = produtos.atualizar(id, produto);
        return Response.ok() //201
                .entity(resposta) // livro
                .build();
    }

    @DELETE
    @Path("{id}") // /livros/{id}
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response remover(@PathParam("id") long id){
        if(produtos.remover(id)) {//true -> sucesso
            return Response.ok().build();
        }
        return Response.notModified().build();
    }

    private Produto livroDe(JsonObject json) {
        return new Produto(
                json.getString("nomeProduto"),
                json.getString("codProduto"),
                json.getString("descricao"),
                -1
        );
    }

}
