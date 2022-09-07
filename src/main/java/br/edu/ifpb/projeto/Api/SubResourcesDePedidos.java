package br.edu.ifpb.projeto.Api;

import br.edu.ifpb.projeto.model.Produto;
import br.edu.ifpb.projeto.repository.PedidoRepository;
import br.edu.ifpb.projeto.repository.ProdutoRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Stateless
public class SubResourcesDePedidos {
    @Inject
    private ProdutoRepository produtos;
    @Inject
    private PedidoRepository pedidos;

    private Logger logger = Logger.getLogger(SubResourcesDePedidos.class.getName());


    @PUT
    @Path("{idLivro}")
    public Response incluirLivroEm(
            @PathParam("codigo") String codigo,
            @PathParam("idLivro") long idLivro){
        logger.log(Level.INFO, "Executando o método PUT, após a alteração");
        Produto produto = produtos.buscarPorId(idProdutos);
        PedidoRepository pedidos =  pedidos.incluirPedidos(codigo, produto);
        return  Response.ok().
                entity(pedidos)
                .build();
    }
    @GET
    public Response listarLivrosDoEmprestimo(
            @PathParam("codigo") String codigo){
        logger.log(Level.INFO, "Executando o método GET");
        PedidoRepository pedidos =  pedidos.localizarPorCodigo(codigo);
        List<Produto> list = (List<Produto>) pedidos.getProduto();
        return  Response.ok().
                entity(list)
                .build();
    }
}
