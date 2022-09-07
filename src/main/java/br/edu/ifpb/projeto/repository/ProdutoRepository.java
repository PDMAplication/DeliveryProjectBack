package br.edu.ifpb.projeto.repository;

import br.edu.ifpb.projeto.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    List<Produto> todos();

    Produto criar(Produto livroDe);

    Produto buscarPorId(long id);

    Produto atualizar(long id, Produto produto);

    boolean remover(long id);
}
