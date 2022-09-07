package br.edu.ifpb.projeto.Api.presenter;

import br.edu.ifpb.projeto.Api.PedidosResources;
import br.edu.ifpb.projeto.model.Produto;
import br.edu.ifpb.projeto.repository.PedidoRepository;


import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoPresenter {
    private String codigo;
    private LocalDate realizadoEm;
    private String status;
    private String cliente; //cpf
    private List<Link> livros; //HATEOS
    private Link finalizar;
    private UriInfo uriInfo;
    private Produto produto;

    public PedidoPresenter() {
    }

    public PedidoPresenter(PedidoRepository pedidos, UriInfo uriInfo) {
        this.codigo = String.valueOf(pedidos.getCodigo());
        this.realizadoEm = pedidos.getRealizadoEm();
        this.status = pedidos.getStatus().name();
        this.cliente = pedidos.getCpf();
        this.uriInfo = uriInfo;
        incluirPedidos(pedidos);
        incluirAcoes(pedidos);
    }

    private void incluirPedidos(PedidoRepository pedidos){
        this.produto = pedidos.getProduto()
                .stream()
                .map(this::toLink)
                .collect(Collectors.toList());
    }
    private void incluirAcoes(PedidoRepository pedidos) {
        URI location = uriInfo.getBaseUriBuilder() // ../api/
                .path(PedidosResources.class) // ../api/pedidos
                .path(pedidos.getCodigo()) // ../api/pedidos/1
                .path("finalizar") // ../api/pedidos/1/finalizar
                .build();

        this.finalizar = new Link(
                "finalizar",
                location.toString()
        ) {
            @Override
            public URI getUri() {
                return null;
            }

            @Override
            public UriBuilder getUriBuilder() {
                return null;
            }

            @Override
            public String getRel() {
                return null;
            }

            @Override
            public List<String> getRels() {
                return null;
            }

            @Override
            public String getTitle() {
                return null;
            }

            @Override
            public String getType() {
                return null;
            }

            @Override
            public Map<String, String> getParams() {
                return null;
            }

            @Override
            public String toString() {
                return null;
            }
        };
    }

    private Link toLink(Produto produto){
        // ../api/
        URI location = uriInfo.getBaseUriBuilder()
                .path(PedidosResources.class)
                .path(String.valueOf(produto.getId()))
                .build();

        return new Link(
                (String) produto.nomeProduto(),
                location.toString()
        );

    }




}
