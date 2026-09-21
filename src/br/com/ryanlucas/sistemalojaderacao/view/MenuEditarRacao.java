package br.com.ryanlucas.sistemalojaderacao.view;

import br.com.ryanlucas.sistemalojaderacao.model.GerenciadorDeProduto;
import br.com.ryanlucas.sistemalojaderacao.model.ProdutoRacao;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuEditarRacao {

    Stage janela;
    Scene cenaGeral;
    Scene cenaEditar;
    ProdutoRacao racao;
    GerenciadorDeProduto ger;

    public MenuEditarRacao(Stage janela, Scene cenaGeral, ProdutoRacao racao) {
        this.janela = janela;
        this.cenaGeral = cenaGeral;
        this.racao = racao;

        Label titulo = new Label("Editar Ração");
        titulo.getStyleClass().add("titulo");
        titulo.setAlignment(Pos.TOP_CENTER);

        Label nomeProduto = new Label("Nome");
        nomeProduto.getStyleClass().add("texto");

        Label descricaoProduto = new Label("Descrição");
        descricaoProduto.getStyleClass().add("texto");

        Label pesoProduto = new Label("Peso");
        pesoProduto.getStyleClass().add("texto");

        Label precoProduto = new Label("Preço");
        precoProduto.getStyleClass().add("texto");

        Label precoKgProduto = new Label("Preço Kg");
        precoKgProduto.getStyleClass().add("texto");

        Label porteProduto = new Label("Porte");
        porteProduto.getStyleClass().add("texto");

        Label filhoteProduto = new Label("Idade");
        filhoteProduto.getStyleClass().add("texto");

        Label castradoProduto = new Label("Castrado");
        castradoProduto.getStyleClass().add("texto");

        Label tipoAnimalProduto = new Label("Tipo");
        tipoAnimalProduto.getStyleClass().add("texto");

        TextField nome = new TextField();
        nome.getStyleClass().add("conteudoProduto");
        nome.setPromptText(racao.getNome());
        nome.setMinWidth(300);
        nome.setMaxWidth(300);

        TextField peso = new TextField();
        peso.getStyleClass().add("conteudoProduto");
        peso.setPromptText(racao.getPeso() + "");
        peso.setMinWidth(300);
        peso.setMaxWidth(300);

        TextField preco = new TextField();
        preco.getStyleClass().add("conteudoProduto");
        preco.setPromptText(racao.getPreco() + "");
        preco.setMinWidth(300);
        preco.setMaxWidth(300);

        TextField precoKg = new TextField();
        precoKg.getStyleClass().add("conteudoProduto");
        precoKg.setPromptText(racao.getPrecoPorKg() + "");
        precoKg.setMinWidth(80);
        precoKg.setMaxWidth(80);

        TextArea descricao = new TextArea();
        descricao.getStyleClass().add("conteudoProduto");
        descricao.setPromptText(racao.getDescricao());
        descricao.setMinWidth(300);
        descricao.setMaxWidth(300);

        ComboBox<String> porte = new ComboBox<>();
        porte.getStyleClass().add("conteudoProduto");
        porte.setPromptText(racao.getPorte());
        porte.setMinWidth(100);
        porte.setMaxWidth(100);

        porte.getItems().addAll("Pequeno", "Grande", "Grande/Medio", "Medio", "Sem porte");

        ComboBox<String> idade = new ComboBox<>();
        idade.getStyleClass().add("conteudoProduto");
        idade.setPromptText(racao.getFilhote());
        idade.setMinWidth(140);
        idade.setMaxWidth(140);

        idade.getItems().addAll("Filhote", "Adulto");

        ComboBox<String> castrado = new ComboBox<>();
        castrado.getStyleClass().add("conteudoProduto");
        castrado.setPromptText(racao.getCastrado());
        castrado.setMinWidth(140);
        castrado.setMaxWidth(140);

        castrado.getItems().addAll("Sim", "Não");

        ComboBox<String> tipo = new ComboBox<>();
        tipo.getStyleClass().add("conteudoProduto");
        tipo.setPromptText(racao.getTipoAnimal());
        tipo.setMinWidth(100);
        tipo.setMaxWidth(100);

        tipo.getItems().addAll("Cachorro", "Gato", "Passaros");

        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setOnAction(e -> {
            janela.setScene(cenaGeral);
        });

        Button salvar = new Button("Salvar");
        salvar.getStyleClass().add("botoes");
        salvar.setOnAction(e -> {
            String nomeProd = nome.getText();
            double pesoProd = Double.parseDouble(peso.getText());
            double precoProd = Double.parseDouble(preco.getText());
            double precoKgProd = Double.parseDouble(precoKg.getText());
            String descProd = descricao.getText();
            String porteProd = porte.getValue();
            String idadeProd = idade.getValue();
            String castradoProd = castrado.getValue();
            String tipoProd = tipo.getValue();

            new GerenciadorDeProduto().atualizarRacao(nomeProd, racao.getNome(), descProd, precoProd,
                    castradoProd, idadeProd, pesoProd, porteProd, precoKgProd, tipoProd);
            janela.setScene(cenaGeral);
        });

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.getChildren().addAll(voltar, salvar);

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getChildren().addAll(nomeProduto, nome, descricaoProduto, descricao, pesoProduto, peso,
                precoProduto, preco, precoKgProduto, precoKg, porteProduto, porte, filhoteProduto, idade,
                castradoProduto, castrado, tipoAnimalProduto, tipo);

        VBox boxPrincipal = new VBox(5);
        boxPrincipal.getStyleClass().add("conteudo");
        boxPrincipal.setAlignment(Pos.TOP_CENTER);
        boxPrincipal.getChildren().addAll(titulo, vbox, hbox);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaEditar = new Scene(boxPrincipal, 650, 700);
        cenaEditar.getStylesheets().add(caminhoCss);

        janela.setScene(cenaEditar);
        janela.sizeToScene();
        janela.show();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
