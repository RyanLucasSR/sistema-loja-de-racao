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

public class MenuRacao {

    Stage janela;
    Scene cenaRacao;
    Scene cenaGeral;
    GerenciadorDeProduto ger;

    public MenuRacao(Stage JanelaGeral, Scene cenaGeral) {
        this.janela = JanelaGeral;
        this.cenaGeral = cenaGeral;

        Label labelTitulo = new Label("Adicionar Ração");
        labelTitulo.getStyleClass().add("titulo");


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
        nome.setPromptText("Nome do Produto");
        nome.setMinWidth(300);
        nome.setMaxWidth(300);

        TextField peso = new TextField();
        peso.getStyleClass().add("conteudoProduto");
        peso.setPromptText("Peso");
        peso.setMinWidth(300);
        peso.setMaxWidth(300);

        TextField preco = new TextField();
        preco.getStyleClass().add("conteudoProduto");
        preco.setPromptText("Preço");
        preco.setMinWidth(300);
        preco.setMaxWidth(300);

        TextField precoKg = new TextField();
        precoKg.getStyleClass().add("conteudoProduto");
        precoKg.setPromptText("Preço Kg");
        precoKg.setMinWidth(80);
        precoKg.setMaxWidth(80);

        TextArea descricao = new TextArea();
        descricao.getStyleClass().add("conteudoProduto");
        descricao.setPromptText("Descrição do Produto");
        descricao.setMinWidth(300);
        descricao.setMaxWidth(300);

        ComboBox<String> porte = new ComboBox<>();
        porte.getStyleClass().add("conteudoProduto");
        porte.setPromptText("Porte");
        porte.setMinWidth(100);
        porte.setMaxWidth(100);

        porte.getItems().addAll("Pequeno", "Grande", "Grande/Medio", "Medio", "Sem porte");

        ComboBox<String> idade = new ComboBox<>();
        idade.getStyleClass().add("conteudoProduto");
        idade.setPromptText("Idade");
        idade.setMinWidth(140);
        idade.setMaxWidth(140);

        idade.getItems().addAll("Filhote", "Adulto");

        ComboBox<String> castrado = new ComboBox<>();
        castrado.getStyleClass().add("conteudoProduto");
        castrado.setPromptText("Castrado");
        castrado.setMinWidth(140);
        castrado.setMaxWidth(140);

        castrado.getItems().addAll("Sim", "Não");

        ComboBox<String> tipo = new ComboBox<>();
        tipo.getStyleClass().add("conteudoProduto");
        tipo.setPromptText("Tipo Animal");
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
            try {
                String nomeProd = nome.getText();
                double pesoProd = Double.parseDouble(peso.getText());
                double precoProd = Double.parseDouble(preco.getText());
                double precoKgProd = Double.parseDouble(precoKg.getText());
                String descProd = descricao.getText();
                String porteProd = porte.getValue();
                String idadeProd = idade.getValue();
                String castradoProd = castrado.getValue();
                String tipoProd = tipo.getValue();

                new GerenciadorDeProduto().adicionarRacoes(nomeProd, descProd, precoProd, pesoProd,
                        precoKgProd, tipoProd, porteProd, idadeProd, castradoProd);
            }catch (CampoObrigatorioException | InvalidoException ex){
                mostrarErro(ex.getMessage());
                janela.setScene(cenaGeral);
            }

           janela.setScene(cenaGeral);
        });

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.TOP_LEFT);

        vbox.getChildren().add(nomeProduto);
        vbox.getChildren().add(nome);
        vbox.getChildren().add(descricaoProduto);
        vbox.getChildren().add(descricao);
        vbox.getChildren().add(pesoProduto);
        vbox.getChildren().add(peso);
        vbox.getChildren().add(precoProduto);
        vbox.getChildren().add(preco);
        vbox.getChildren().add(precoKgProduto);
        vbox.getChildren().add(precoKg);
        vbox.getChildren().add(porteProduto);
        vbox.getChildren().add(porte);
        vbox.getChildren().add(filhoteProduto);
        vbox.getChildren().add(idade);
        vbox.getChildren().add(castradoProduto);
        vbox.getChildren().add(castrado);
        vbox.getChildren().add(tipoAnimalProduto);
        vbox.getChildren().add(tipo);

        HBox hbox = new HBox(3);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.getChildren().add(voltar);
        hbox.getChildren().add(salvar);

        VBox boxSecundaria = new VBox(3);
        boxSecundaria.getStyleClass().add("conteudo");
        boxSecundaria.setAlignment(Pos.TOP_CENTER);
        boxSecundaria.getChildren().addAll(labelTitulo ,vbox, hbox);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaRacao = new Scene(boxSecundaria, 650, 700);

        cenaRacao.getStylesheets().add(caminhoCss);
        janela.setScene(cenaRacao);
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
