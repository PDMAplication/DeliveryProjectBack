package br.edu.ifpb.projeto.model;

import br.edu.ifpb.projeto.enums.Pagamento;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "objectid")
    private String id;

    private Usuario usuario;

    private Produto produto;

    private BigDecimal valorPedido;

    @Enumerated(EnumType.STRING)
    private Pagamento pagamento;

    public Pedido(Usuario usuario, Produto produto, BigDecimal valorPedido, Pagamento pagamento) {
        this.usuario = usuario;
        this.produto = produto;
        this.valorPedido = valorPedido;
        this.pagamento = pagamento;
    }
}
