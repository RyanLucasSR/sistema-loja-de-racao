package br.com.ryanlucas.sistemalojaderacao.view;

import br.com.ryanlucas.sistemalojaderacao.excecao.CampoObrigatorioException;
import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;
import br.com.ryanlucas.sistemalojaderacao.model.GerenciadorDeProduto;
import br.com.ryanlucas.sistemalojaderacao.model.ProdutoRacao;
import br.com.ryanlucas.sistemalojaderacao.model.Produto;

import java.util.List;
import java.util.Scanner;

public class AplicacaoConsole {

    Scanner entrada = new Scanner(System.in);
    Menu menu = new Menu();
    GerenciadorDeProduto ger = new GerenciadorDeProduto();

    int opcao;
    boolean continuar = true;

    public void iniciar(){
        do{
            try {
                iniciarMenu();
            }catch(InvalidoException e){
                erro(e.getMessage());
            }

            switch(opcao){
                case 1:
                    try {
                        entrada.nextLine();

                        System.out.println("Digite o nome do produto: ");
                        String nome = entrada.nextLine();

                        System.out.println("Descrição do produto: ");
                        String descricao = entrada.nextLine();

                        System.out.println("Valor do produto: ");
                        String valor = entrada.nextLine().replace(",", ".");

                        double preco = Double.parseDouble(valor);

                        ger.adicionarProduto(nome, descricao, preco);

                    }catch (InvalidoException | CampoObrigatorioException e){
                        erro(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        entrada.nextLine();

                        System.out.println("Digite o nome do produto: ");
                        String nomeRacao = entrada.nextLine();

                        System.out.println("Descrição do produto: ");
                        String descricaoRacao = entrada.nextLine();

                        System.out.println("Valor do produto: ");
                        String valor = entrada.nextLine().replace(",", ".");

                        double preco = Double.parseDouble(valor);

                        System.out.println("Para qual animal e esse produto:");
                        String animalRacao = menuAnimal();

                        entrada.nextLine();

                        System.out.println("Peso da ração: ");
                        String valorPeso = entrada.nextLine().replace(",", ".");

                        double peso = Double.parseDouble(valorPeso);

                        System.out.println("Preço a avarejo: ");
                        String precoPorPeso = entrada.nextLine().replace(",", ".");

                        double pesoPorKg = Double.parseDouble(precoPorPeso);

                        String porte = menuPorte();

                        entrada.nextLine();

                        System.out.println("Filhote? [s/n]");
                        String filhote = entrada.nextLine().trim();

                        System.out.println("Castrado? [s/n]");
                        String castrado = entrada.nextLine().trim();


//        String nome, String descricao, double preco, String castrado, String filhote,
//        double peso, String porte, double precoPorKg, String tipoAnimal
                        ger.adicionarRacoes(nomeRacao, descricaoRacao, preco, peso, pesoPorKg, animalRacao,
                                porte, filhote, castrado);

                    }catch (InvalidoException | CampoObrigatorioException e){
                        erro(e.getMessage());
                    }

                    break;

                case 3:
                    List<Produto> lista = ger.listarTodos();

                    for(Produto produto : lista){
                        System.out.println(produto);
                    }

                    break;

                case 4:
                    try {
                        System.out.println("Digite o nome do produto: ");
                        entrada.nextLine();
                        String nomeProduto = entrada.nextLine();

                        List<Produto> listaConsulta = ger.consultarUm(nomeProduto);

                        for(Produto produto : listaConsulta){
                            System.out.println(produto);
                        }
                    }catch (CampoObrigatorioException e){
                        erro(e.getMessage());
                    }

                    break;

                case 5:
                    try {

                        entrada.nextLine();
                        System.out.println("Informe o peso: kg");
                        String kg = entrada.nextLine().replace(",", ".");

                        double kgConvertido = Double.parseDouble(kg);

                        List<ProdutoRacao> listarKg = ger.consultarKg(kgConvertido);

                        for(ProdutoRacao produto : listarKg){
                            System.out.println(produto);
                        }
                    }catch (CampoObrigatorioException e){
                        erro(e.getMessage());
                    }

                    break;

                case 0:
                    System.out.println("Saindo...");
                    continuar = false;
                    ger.fechar();

                    break;

                default:
                    System.out.println("Valor invalido!");
            }

        }while(continuar);
    }

    private void iniciarMenu(){
        System.out.println(menu.menu());

        opcao = entrada.nextInt();

        if(opcao < 0 || opcao > 5){
            throw new InvalidoException("Valor Invalido");
        }
    }

    private boolean erro(String mensagem){
        boolean continuarErro;

        entrada.nextLine();

        do {
            System.out.println(mensagem);

            System.out.println("Deseja Continuar? (s/n)");
            String resposta = entrada.nextLine().trim();

            if (resposta.equalsIgnoreCase("n")) {
                continuarErro = false;

                return continuar = false;
            } else if (resposta.equalsIgnoreCase("s")) {
                continuarErro = false;

                return continuar = true;
            } else {
                continuarErro = true;
            }
        }while(continuarErro);
        return continuar = true;
    }

    private String menuAnimal(){
        System.out.println(menu.menuAnimal());
        int escolha = entrada.nextInt();

        if(escolha < 0 || escolha > 3){
            throw new InvalidoException("Valor Invalido");
        }

        if(escolha == 1){
            return "Cachorro";
        }else if(escolha == 2){
            return "Gato";
        }else if(escolha == 3) {
            return "Passaros";
        }else{
            throw new InvalidoException("Valor invalido!");
        }
    }

    public String menuPorte(){
        System.out.println(menu.menuPorte());
        int escolha = entrada.nextInt();

        if(escolha < 0 || escolha > 4){
            throw new InvalidoException("Valor Invalido");
        }

        if(escolha == 1){
            return "Grande";
        }else if(escolha == 2){
            return "Medio";
        }else if(escolha == 3) {
            return "Grande/Medio";
        }else if(escolha == 4) {
            return "Pequeno";
        }else if(escolha == 5) {
            return "Passaro";
        }else {
            throw new InvalidoException("Valor invalido!");
        }
    }
}
