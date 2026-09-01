package br.com.ryanlucas.sistemalojaderacao.model;

import br.com.ryanlucas.sistemalojaderacao.DAO.DAO;
import br.com.ryanlucas.sistemalojaderacao.DAO.ProdutoDAO;
import br.com.ryanlucas.sistemalojaderacao.DAO.RacaoDAO;
import br.com.ryanlucas.sistemalojaderacao.excecao.CampoObrigatorioException;
import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;

import java.util.List;

public class GerenciadorDeProduto {

    DAO<Produto> dao = new DAO<>(Produto.class);
    ProdutoDAO prodDAO = new ProdutoDAO();
    RacaoDAO racaoDAO = new RacaoDAO();

    public void adicionarProduto(String nome, String descricao, double preco) {
        if(nome == null || nome.isEmpty()){
            throw new CampoObrigatorioException("Campo obrigatorio!");
        }

        if(preco < 0){
            throw new InvalidoException("Valor invalido!");
        }

        dao.salvarAtomico(new Produto(nome, descricao, preco));

        System.out.println("Produto adicionado com sucesso!");
    }

    public void adicionarRacoes(String nome, String descricao, double preco, double peso, double pesoPorKg,
                                String animal, String porte, String filhote, String castrado) {

        if(nome == null || nome.isEmpty()){
            throw new CampoObrigatorioException("Campo obrigatorio!");
        }

        if(preco < 0){
            throw new InvalidoException("Valor invalido!");
        }

        if(peso < 0){
            throw new InvalidoException("Valor invalido!");
        }else if (peso > 100){
            throw new InvalidoException("Valor invalido!");
        }

        if(pesoPorKg < 0){
            throw new InvalidoException("Valor invalido!");
        }else if (pesoPorKg > 100){
            throw new InvalidoException("Valor invalido!");
        }

        if(animal == null || animal.isEmpty()){
            throw new CampoObrigatorioException("Campo obrigatorio!");
        }

        if(porte == null || porte.isEmpty()){
            throw new CampoObrigatorioException("Campo obrigatorio!");
        }

        if(filhote == null || filhote.isEmpty()){
            throw new CampoObrigatorioException("Campo obrigatorio!");
        }

        if(castrado == null || castrado.isEmpty()){
            throw new CampoObrigatorioException("Campo obrigatorio!");
        }

        dao.salvarAtomico(new ProdutoRacao(nome, descricao, preco, castrado, filhote, peso, porte, pesoPorKg,
                animal));
    }

    public List<Produto> listarTodos(){
        return dao.listarTodos();
    }

    public List<Produto> consultarUm(String nomeConsulta){
        if(nomeConsulta == null || nomeConsulta.isEmpty()){
            throw new CampoObrigatorioException("Campo obrigatorio!");
        }

        return prodDAO.consultarPorNome(nomeConsulta);
    }

    public List<ProdutoRacao> consultarKg(double kg){

        if(kg < 0){
            throw new InvalidoException("Valor invalido!");
        }

        if(kg > 100){
            throw new InvalidoException("Valor invalido!");
        }

        return racaoDAO.listarPorKg(kg);
    }

    public void fechar(){
        dao.fechar();
    }
}