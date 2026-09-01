package br.com.ryanlucas.sistemalojaderacao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO <E>{

    protected static EntityManagerFactory emf;
    protected EntityManager em;
    protected Class<E> classe;

    static {
        try{
            emf = Persistence.createEntityManagerFactory("sistema-loja-racao");
        }catch(Exception e){
            System.out.println("Erro ao iniciar!");
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

    public DAO<E> fecharTransacao(){
        em.getTransaction().commit();
        return this;
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

    public void fechar(){
        em.close();
    }
}
