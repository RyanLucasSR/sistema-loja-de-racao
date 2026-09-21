package br.com.ryanlucas.sistemalojaderacao.view;

import br.com.ryanlucas.sistemalojaderacao.excecao.CampoObrigatorioException;
import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;
import br.com.ryanlucas.sistemalojaderacao.model.GerenciadorDeProduto;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuProduto {

    Stage janela;
    Scene cenaProduto;
    Scene cenaGeral;
    GerenciadorDeProduto ger;

    public MenuProduto(Stage janela, Scene cenaGeral){

        this.janela = janela;
        this.cenaGeral = cenaGeral;

        Label labelTitulo = new Label("Adicionar Produto");
        labelTitulo.getStyleClass().add("titulo");

        Label labelNomeProduto = new Label("Nome");
        labelNomeProduto.getStyleClass().add("texto");
        labelNomeProduto.setAlignment(Pos.BASELINE_LEFT);

        Label labelDescricaoProduto = new Label("Descrição");
        labelDescricaoProduto.getStyleClass().add("texto");

        Label labelValorProduto = new Label("Preço");
        labelValorProduto.getStyleClass().add("texto");

        TextField nomeProd = new TextField();
        nomeProd.getStyleClass().add("conteudoProduto");
        nomeProd.setPromptText("Nome do produto:");
        nomeProd.setPrefWidth(150);
        nomeProd.setMaxWidth(200);

        TextArea descricaoProd = new TextArea();
        descricaoProd.getStyleClass().add("conteudoProduto");
        descricaoProd.setPromptText("Descrição do produto:");
        descricaoProd.setPrefWidth(400);
        descricaoProd.setMaxWidth(400);
        descricaoProd.setPrefHeight(80);

        TextField valorProd = new TextField();
        valorProd.getStyleClass().add("conteudoProduto");
        valorProd.setPromptText("Preço do produto:");
        valorProd.setPrefWidth(107);
        valorProd.setMaxWidth(107);


        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoesProduto");
        voltar.setOnAction(e -> {
            janela.setScene(cenaGeral);
        });

        Button salvar = new Button("Salvar");
        salvar.getStyleClass().add("botoesProduto");
        salvar.setOnAction(e -> {
            try {
                String nome = nomeProd.getText();
                String descricao = descricaoProd.getText();
                double valor = Double.parseDouble(valorProd.getText().replace(",", "."));

                new GerenciadorDeProduto().adicionarProduto(nome, descricao, valor);

                janela.setScene(cenaGeral);
            }catch (CampoObrigatorioException | InvalidoException ex){
                mostrarErro(ex.getMessage());
                janela.setScene(cenaGeral);
            }
        });

        VBox boxTexto = new VBox(5);
        boxTexto.setAlignment(Pos.TOP_LEFT);

        boxTexto.getChildren().add(labelNomeProduto);
        boxTexto.getChildren().add(nomeProd);
        boxTexto.getChildren().add(labelDescricaoProduto);
        boxTexto.getChildren().add(descricaoProd);
        boxTexto.getChildren().add(labelValorProduto);
        boxTexto.getChildren().add(valorProd);

        HBox boxBotao = new HBox(3);
        boxBotao.setAlignment(Pos.BASELINE_CENTER);

        boxBotao.getChildren().add(voltar);
        boxBotao.getChildren().add(salvar);

        VBox boxSecundaria = new VBox();
        boxSecundaria.getStyleClass().add("conteudo");
        boxSecundaria.setAlignment(Pos.TOP_CENTER);

        boxSecundaria.getChildren().addAll(labelTitulo, boxTexto, boxBotao);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaProduto = new Scene(boxSecundaria, 500, 400);

        cenaProduto.getStylesheets().add(caminhoCss);
        janela.setScene(cenaProduto);
        janela.sizeToScene();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}