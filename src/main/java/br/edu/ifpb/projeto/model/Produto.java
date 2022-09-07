package br.edu.ifpb.projeto.model;

public class Produto {

    private String nomeProduto;
    private String codProduto;
    private String deecricao;
    private int id;
    private int estoque;
    public Produto(String nomeProduto, String codProduto, String descricao, int i) {
    }

    public int getId() {
        return 0;
    }

    public int getEstoque() {
        return 0;
    }

    public boolean isDisponibilidade() {
        return false;
    }

    public Object nomeProduto() {
        return nomeProduto;
    }
}
