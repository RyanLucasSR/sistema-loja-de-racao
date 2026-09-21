package br.com.ryanlucas.sistemalojaderacao.DAO;

import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;

import javax.persistence.*;
import java.util.List;

public class DAO <E>{

    protected static EntityManagerFactory emf;
    protected EntityManager em;
    protected Class<E> classe;

    static {
        try{
            emf = Persistence.createEntityManagerFactory("sistema-loja-racao");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public DAO(){
        this(null);
    }

    public DAO(Class<E> classe){
        this.classe = classe;
        this.em = emf.createEntityManager();
    }

    public DAO<E> abrirTransacao(){
        em.getTransaction().begin();
        return this;
    }

    public void fecharTransacao(){
        em.getTransaction().commit();
    }

    public DAO<E> salvar(E entidade){
        em.persist(entidade);
        return this;
    }

    public void salvarAtomico(E entidade){
        abrirTransacao().salvar(entidade).fecharTransacao();
    }

    public List<E> listarTodos(){
        if(classe == null){
            throw new UnsupportedOperationException("Classe invalida!");
        }

        String jpql = "SELECT e FROM " + classe.getName() + " e";
        TypedQuery<E> query = em.createQuery(jpql, classe);

        return query.getResultList();
    }

    public int deletarProduto(Long id){
        if(classe == null){
            throw new InvalidoException("Produto invalido!");
        }

        String jpql = "DELETE FROM " + classe.getName() + " p WHERE p.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);

        int linhas = 0;
        return linhas = query.executeUpdate();
    }

    public void fechar(){
        em.close();
    }
}
