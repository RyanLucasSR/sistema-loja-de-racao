package br.com.ryanlucas.sistemalojaderacao.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Rações")
public class ProdutoRacao extends Produto {

    @Column(name = "peso", nullable = true)
    private double peso;

    @Column(name = "peçokg", nullable = true)
    private double precoPorKg;

    @Column(name = "porte", nullable = true)
    private String porte;

    @Column(name = "filhote" , nullable = true)
    private String filhote;

    @Column(name = "castrado", nullable = true)
    private String castrado;

    @Column(name = "tipo")
    private String tipoAnimal;

    public ProdutoRacao() {

    }

    public ProdutoRacao(String nome, String descricao, double preco, String castrado, String filhote,
                        double peso, String porte, double precoPorKg, String tipoAnimal) {
        super(nome, descricao, preco);
        this.castrado = castrado;
        this.filhote = filhote;
        this.peso = peso;
        this.porte = porte;
        this.precoPorKg = precoPorKg;
        this.tipoAnimal = tipoAnimal;
    }

    public String getCastrado() {
        return castrado;
    }

    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }

    public String getFilhote() {
        return filhote;
    }

    public void setFilhote(String filhote) {
        this.filhote = filhote;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public double getPrecoPorKg() {
        return precoPorKg;
    }

    public void setPrecoPorKg(double precoPorKg) {
        this.precoPorKg = precoPorKg;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    @Override
    public String toString() {
        return String.format("=================\nNome: %s\nPreço: %.2f\nPreço Avarejo: %.2f\nDescrição: %s" +
                "\nPeso: %.2f\nFilhote: %s\nPorte: %s\nCastrado: %s\nTipo Animal: %s\n=================",getNome(),
                getPreco(), getPrecoPorKg(), getDescricao(), getPeso(), getFilhote(), getPorte(), getCastrado(),
                getTipoAnimal());
    }
}
