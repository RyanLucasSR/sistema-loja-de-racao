package br.com.ryanlucas.sistemalojaderacao.DAO;

import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;
import br.com.ryanlucas.sistemalojaderacao.model.ProdutoRacao;

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
}
