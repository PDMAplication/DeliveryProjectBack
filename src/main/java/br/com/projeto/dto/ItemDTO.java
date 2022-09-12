package br.com.projeto.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    private UsuarioDTO usuarioDTO;

    private ProdutoDTO produtoDTO;

    private Integer quantidadeProduto;

    private BigDecimal valorItem;

    public ItemDTO(Long id, UsuarioDTO usuarioDTO, ProdutoDTO produtoDTO, Integer quantidadeProduto, BigDecimal valorItem) {
        this.id = id;
        this.usuarioDTO = usuarioDTO;
        this.produtoDTO = produtoDTO;
        this.quantidadeProduto = quantidadeProduto;
        this.valorItem = produtoDTO.getValor().multiply(new BigDecimal(this.getQuantidadeProduto()));

    }

}
