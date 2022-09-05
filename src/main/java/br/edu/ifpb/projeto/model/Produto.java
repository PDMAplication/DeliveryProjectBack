package br.edu.ifpb.projeto.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "objectid")
    private String id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private String foto;

    public Produto(String nome, String descricao, BigDecimal preco, String foto) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", foto='" + foto + '\'' +
                '}';
    }
}
