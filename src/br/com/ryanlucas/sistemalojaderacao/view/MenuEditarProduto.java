package br.com.ryanlucas.sistemalojaderacao.view;

import br.com.ryanlucas.sistemalojaderacao.model.GerenciadorDeProduto;
import br.com.ryanlucas.sistemalojaderacao.model.Produto;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuEditarProduto {

    Scene cenaGeral;
    Scene cenaEditar;
    Stage janela;
    Produto produto;
    GerenciadorDeProduto ger = new GerenciadorDeProduto();

    public MenuEditarProduto(Scene cenaGeral, Stage janela, Produto produto){
        this.cenaGeral = cenaGeral;
        this.janela = janela;
        this.produto = produto;


        Label titulo = new Label("Editar Produto");
        titulo.getStyleClass().add("titulo");
        titulo.setAlignment(Pos.CENTER);

        Label labelNomeProduto = new Label("Nome");
        labelNomeProduto.getStyleClass().add("texto");
        labelNomeProduto.setAlignment(Pos.BASELINE_LEFT);

        Label labelDescricaoProduto = new Label("Descrição");
        labelDescricaoProduto.getStyleClass().add("texto");

        Label labelValorProduto = new Label("Preço");
        labelValorProduto.getStyleClass().add("texto");

        TextField campoNome = new TextField();
        campoNome.setPromptText(produto.getNome());
        campoNome.getStyleClass().add("conteudoProduto");
        campoNome.setMinWidth(300);
        campoNome.setMaxWidth(300);

        TextField campoDescricao = new TextField();
        campoDescricao.setPromptText(produto.getDescricao());
        campoDescricao.getStyleClass().add("conteudoProduto");
        campoDescricao.setMinWidth(300);
        campoDescricao.setMaxWidth(300);

        TextField campoPreco = new TextField();
        campoPreco.setPromptText(produto.getPreco() + "");
        campoPreco.getStyleClass().add("conteudoProduto");
        campoPreco.setMinWidth(300);
        campoPreco.setMaxWidth(300);

        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setOnAction(e -> {
           janela.setScene(cenaGeral);
        });

        Button salvar = new Button("Salvar");
        salvar.getStyleClass().add("botoes");
        salvar.setOnAction(e -> {
           String nome = campoNome.getText();
           String descricao = campoDescricao.getText();

           double preco = Double.parseDouble(campoPreco.getText());

           ger.atualizarProduto(nome, produto.getNome(), descricao, preco);
           janela.setScene(cenaGeral);
        });

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.getChildren().addAll(voltar, salvar);

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getChildren().addAll(labelNomeProduto, campoNome, labelDescricaoProduto, campoDescricao,
                labelValorProduto, campoPreco);

        VBox boxPrincipal = new VBox(5);
        boxPrincipal.getStyleClass().add("conteudo");
        boxPrincipal.setAlignment(Pos.TOP_CENTER);
        boxPrincipal.getChildren().addAll(titulo, vbox, hbox);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaEditar = new Scene(boxPrincipal, 700, 400);
        cenaEditar.getStylesheets().add(caminhoCss);

        janela.setScene(cenaEditar);
        janela.sizeToScene();
        janela.show();
    }
}
