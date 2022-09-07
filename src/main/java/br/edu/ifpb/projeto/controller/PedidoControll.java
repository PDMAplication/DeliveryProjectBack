package br.edu.ifpb.projeto.controller;

import br.edu.ifpb.projeto.model.Produto;
import br.edu.ifpb.projeto.repository.PedidoRepository;

public class PedidoControll implements PedidoRepository {
    public boolean adicionarProduto (Produto produto, int quantidade) {
        boolean retorno = false;
        if (produto.isDisponibilidade ()){
            if (quantidade > 0){
                if (quantidade <= produto.getEstoque())
                    retorno = true;
            }
        }


        return retorno;
    }
}
