package br.com.ryanlucas.sistemalojaderacao.DAO;

import br.com.ryanlucas.sistemalojaderacao.model.Produto;

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
}
