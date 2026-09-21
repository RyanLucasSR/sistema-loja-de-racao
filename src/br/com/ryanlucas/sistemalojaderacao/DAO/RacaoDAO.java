package br.com.ryanlucas.sistemalojaderacao.DAO;

import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;
import br.com.ryanlucas.sistemalojaderacao.model.ProdutoRacao;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class RacaoDAO extends DAO<ProdutoRacao>{

    public RacaoDAO(){
        super(ProdutoRacao.class);
    }

    public List<ProdutoRacao> listarPorKg(double peso){
        if (classe == null){
            throw new UnsupportedOperationException("Erro!");
        }

        if(peso < 0 || peso > 100){
            throw new InvalidoException("Valor Invalido!");
        }

        String jpql = "SELECT p FROM " + classe.getName() + " p WHERE p.peso = :peso";
        TypedQuery<ProdutoRacao> query = em.createQuery(jpql, classe);
        query.setParameter("peso", peso);

        return  query.getResultList();
    }

    public int updateRacao(String nomeNovo, String nomeAntigo, String descricao, double preco, String castrado,
                           String filhote, double peso, String porte, double precoPorKg, String tipoAnimal){
        if(classe == null){
            throw new InvalidoException("Produto invalido!");
        }

        String jpql = "UPDATE " + classe.getName() + " p SET p.nome = :nome, p.descricao = :descricao, " +
                "p.preco = :preco, p.castrado = :castrado, p.filhote = :filhote, p.peso = :peso," +
                "p.porte = :porte, p.precoPorKg = :precoPorKg, p.tipoAnimal = :tipoAnimal" +
                " WHERE p.nome = :nomeAntigo";

        Query query = em.createQuery(jpql);
        query.setParameter("nome", nomeNovo);
        query.setParameter("descricao", descricao);
        query.setParameter("preco", preco);
        query.setParameter("castrado", castrado);
        query.setParameter("filhote", filhote);
        query.setParameter("peso", peso);
        query.setParameter("porte", porte);
        query.setParameter("precoPorKg", precoPorKg);
        query.setParameter("tipoAnimal", tipoAnimal);
        query.setParameter("nomeAntigo", nomeAntigo);

        int linhas = 0;

        return linhas = query.executeUpdate();
    }
}
