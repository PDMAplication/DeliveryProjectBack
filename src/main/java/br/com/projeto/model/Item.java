package br.com.projeto.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Produto produto;

    private Integer quantidadeProduto;

    private BigDecimal valorItem;

    public Item(Long id, Usuario usuario, Produto produto, Integer quantidadeProduto, BigDecimal valorItem) {
        this.id = id;
        this.usuario = usuario;
        this.produto = produto;
        this.quantidadeProduto = quantidadeProduto;
        this.valorItem = produto.getValor().multiply(new BigDecimal(this.getQuantidadeProduto()));

    }

    public void aumentarQuantidade() {
        this.quantidadeProduto++;
    }

    public void diminuirQuantidade() {
        this.quantidadeProduto--;
    }
}
