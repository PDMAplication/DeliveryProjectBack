package br.com.projeto.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDTO {

    private Long id;

    private String nome;

    private BigDecimal valor;

    private String descricao;

    private String foto;

}
