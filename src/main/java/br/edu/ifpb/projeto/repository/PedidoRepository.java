package br.edu.ifpb.projeto.repository;

import br.edu.ifpb.projeto.model.Produto;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;

public interface PedidoRepository {
    static boolean vazio() {
        return false;
    }

    public boolean adicionarProduto(Produto produto, int quantidade);

    void novo(PedidoRepository pedidos);

    Class getCodigo();

    Arrays getProduto();

    LocalDate getRealizadoEm();

    Resource getStatus();

    String getCpf();

    void finalizar();

    void cancelar();
}
