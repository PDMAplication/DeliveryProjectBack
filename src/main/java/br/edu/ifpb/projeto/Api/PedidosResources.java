package br.edu.ifpb.projeto.Api;

import br.edu.ifpb.projeto.Api.presenter.PedidoPresenter;
import br.edu.ifpb.projeto.model.*;
import br.edu.ifpb.projeto.repository.PedidoRepository;
import br.edu.ifpb.projeto.repository.ProdutoRepository;
import br.edu.ifpb.projeto.repository.UsuarioRepository;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;

@Path("pedidos")
@Produces(MediaType.APPLICATION_JSON)
public class PedidosResources {
    @Context
    private UriInfo uriInfo;
    @Context
    ResourceContext resourceContext;
    @Inject
    private PedidoRepository pedidos;
    @Inject
    private ProdutoRepository produtos;
    @Inject
    private UsuarioRepository usuarios;
    @POST
    public Response criar(){
        PedidoRepository pedidos = new PedidoRepository() {
            @Override
            public boolean adicionarProduto(Produto produto, int quantidade) {
                return false;
            }

            @Override
            public void novo(PedidoRepository pedidos) {

            }

            @Override
            public Class getCodigo() {
                return null;
            }

            @Override
            public Arrays getProduto() {
                return null;
            }

            @Override
            public LocalDate getRealizadoEm() {
                return null;
            }

            @Override
            public Resource getStatus() {
                return null;
            }

            @Override
            public String getCpf() {
                return null;
            }

            @Override
            public void finalizar() {

            }

            @Override
            public void cancelar() {

            }
        };
        pedidos.novo(pedidos);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(pedidos.getCodigo()))
                .build();
        return Response.created(location) //201
                .entity(pedidos) // emprestimo
                .build();
    }
    @GET
    @Path("{id}") // ../api/emprestimos/6017cf1b-ad15-457d-9e8a-e46bfd2748c8
    public Response recuperarPorCodigo(@PathParam("id") String codigo){
        PedidoRepository pedidos = pedidos.localizarPorCodigo(codigo);
        if(PedidoRepository.vazio().equals(pedidos)){
            return Response.noContent()
                    .build();
        }
        PedidoPresenter presenter = new PedidoPresenter(pedidos, uriInfo);
        return Response.ok()
                .entity(presenter)
                .build();
    }

    @Path("{codigo}/pedidos")
    public SubResourcesDePedidos incluirLivroEm(
            @PathParam("codigo") String codigo){
        return resourceContext.getResource(SubResourcesDePedidos.class);
    }
    @PUT
    @Path("{codigo}/cliente/{key}")
    public Response incluirClienteEm(
            @PathParam("codigo") String codigo,
            @PathParam("key") String key){
        PedidoRepository pedidos =  pedidos.localizarPorCodigo(codigo);
        Usuario usuario = usuarios.buscar(key);
        if(PedidoRepository.vazio().equals(pedidos)){
            return Response.notModified()
                    .build();
        }
        if(usuario == null){
            return Response.notModified()
                    .build();
        }
        pedidos.setKey(key);
        usuario.adicionarPedidos(pedidos);
        return Response.ok()
                .entity(pedidos)
                .build();
    }
    @PUT
    @Path("{codigo}")
    public Response cancelar(@PathParam("codigo") String codigo){
        PedidoRepository pedidos =  pedidos.localizarPorCodigo(codigo);
        if(PedidoRepository.vazio().equals(pedidos)){
            return Response.notModified()
                    .build();
        }
        pedidos.cancelar();
        return Response.ok()
                .entity(pedidos)
                .build();
    }
    @PUT
    @Path("{codigo}/finalizar/cliente/{key}")
    public Response finalizar(
            @PathParam("codigo") String codigo,
            @PathParam("key") String key){
        PedidoRepository pedidos =  pedidos.localizarPorCodigo(codigo);
        if(PedidoRepository.vazio().equals(pedidos)){
            return Response.notModified()
                    .build();
        }
        if(usuarios.temPedido(key,codigo)){
            pedidos.finalizar();
            return Response.ok()
                    .entity(pedidos)
                    .build();
        }
        return Response.notModified()
                .build();
    }
}


