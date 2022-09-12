package br.com.projeto.mapper;

import br.com.projeto.dto.ProdutoDTO;
import br.com.projeto.model.Produto;

public class ProdutoMapper {

    public static ProdutoDTO produtoToDTO(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .descricao(produto.getDescricao())
                .foto(produto.getFoto())
                .build();
    }

    public static Produto dtoToProduto(ProdutoDTO produtoDTO) {
        return Produto.builder()
                .id(produtoDTO.getId())
                .nome(produtoDTO.getNome())
                .valor(produtoDTO.getValor())
                .descricao(produtoDTO.getDescricao())
                .foto(produtoDTO.getFoto())
                .build();
    }
}
