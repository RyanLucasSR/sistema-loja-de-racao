package br.com.ryanlucas.sistemalojaderacao.view;

public class Menu {

    public String menu(){
        return "=========================\n" +
                "         MENU         \n" +
                "=========================\n" +
                "[1] Adciconar Produto\n" +
                "[2] Adicionar Ração\n" +
                "[3] Listar Produtos\n" +
                "[4] Consultar Produtos\n" +
                "[5] Consultar Produto\n" +
                "[0] Sair\n" +
                "======================";
    }

    public String menuAnimal(){
        return "=========================\n" +
                "         MENU         \n" +
                "=========================\n" +
                "[1] Cachorro\n" +
                "[2] Gato\n" +
                "[3] Passaros\n" +
                "======================";
    }

    public String menuPorte(){
        return "=========================\n" +
                "         MENU         \n" +
                "=========================\n" +
                "[1] Grande\n" +
                "[2] Medio\n" +
                "[3] Grande/Media\n" +
                "[4] Pequeno\n" +
                "[5] Passaro\n" +
                "======================";
    }
}
