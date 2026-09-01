package br.com.ryanlucas.sistemalojaderacao.model;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Geral", length = 15, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Geral")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomes", nullable = false, length = 100)
    private String nome;

    @Column(name = "descriçao", nullable = true)
    private String descricao;

    @Column(name = "preços", nullable = false, scale = 2, precision = 3)
    private double preco;

    public Produto() {

    }

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("=================\nNome: %s \nPreço: R$%.2f " +
                "\nDescricao: %s\n=================", getNome(), getPreco(), getDescricao());
    }
}
