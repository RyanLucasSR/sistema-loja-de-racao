package br.com.ryanlucas.sistemalojaderacao.DAO;

import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;
import br.com.ryanlucas.sistemalojaderacao.model.Produto;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProdutoDAO extends DAO<Produto>{

    public ProdutoDAO() {
        super(Produto.class);
    }


    public List<Produto> consultarPorNome(String nome){
        if(classe == null){
            throw new UnsupportedOperationException("Classe invalida!");
        }

        String jpql =  "SELECT e FROM " + classe.getName() + " e WHERE e.nome LIKE :nome";
        TypedQuery<Produto> query = em.createQuery(jpql, classe);
        query.setParameter("nome", "%" + nome + "%");

        return query.getResultList();
    }

    public int updateProduto(String nomeNovo, String nomeAntigo, String descricao, double preco){
        if(classe == null){
            throw new InvalidoException("Produto invalido!");
        }

        String jpql = "UPDATE " + classe.getName() + " p SET p.nome = :nome, p.descricao = :descricao, " +
                "p.preco = :preco WHERE p.nome = :nomeAntigo";

        Query query = em.createQuery(jpql);
        query.setParameter("nome", nomeNovo);
        query.setParameter("descricao", descricao);
        query.setParameter("preco", preco);
        query.setParameter("nomeAntigo", nomeAntigo);

        int linhas = 0;

        return linhas = query.executeUpdate();
    }

}
