package br.com.ryanlucas.sistemalojaderacao.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuGeral extends Application {

    Scene cenaGeral;

    @Override
    public void start(Stage janela) throws Exception {
        Label labelTitulo = new Label("Silvano Rações");
        labelTitulo.setAlignment(Pos.TOP_CENTER);
        labelTitulo.getStyleClass().add("titulo");

        Button botaoAdicionarP = new Button("Adciconar Produto");
        botaoAdicionarP.getStyleClass().add("botoes");
        botaoAdicionarP.setOnAction(e -> {
            janela.setTitle("Adicionar-Produto");

            janela.setScene(new MenuProduto(janela, cenaGeral).cenaProduto);
        });
        Button botaoAdicionarR = new Button("Adicionar Ração");
        botaoAdicionarR.getStyleClass().add("botoes");
        botaoAdicionarR.setOnAction(e -> {
            janela.setScene(new MenuRacao(janela, cenaGeral).cenaRacao);
        });

        Button listarP = new Button("Listar Produtos");
        listarP.getStyleClass().add("botoes");
        listarP.setOnAction(e -> {
            janela.setScene(new MenuListarProduto(janela, cenaGeral).cenaListarProduto);
        });

        Button listarR = new Button("Listar Rações");
        listarR.getStyleClass().add("botoes");
        listarR.setOnAction(e -> {
            janela.setScene(new MenuListarRacao(janela, cenaGeral).cenaListarRacao);
        });

        Button consultarP = new Button("Consultar Produto");
        consultarP.getStyleClass().add("botoes");
        consultarP.setOnAction(e -> {
            janela.setScene(new MenuConsultarProduto(janela, cenaGeral).cenaConsultarP);
        });
        Button consultarPs = new Button("Consultar Por Kg");
        consultarPs.getStyleClass().add("botoes");
        consultarPs.setOnAction(e -> {
            janela.setScene(new MenuConsultarKg(janela, cenaGeral).cena);
        });

        Button sair = new Button("Sair");
        sair.getStyleClass().add("botoes");
        sair.setOnAction(e -> {
            System.out.println("Saindo");
            System.exit(0);
        });

        HBox boxBotoesCima = new HBox(2.5);
        boxBotoesCima.setAlignment(Pos.CENTER);
        boxBotoesCima.getChildren().add(botaoAdicionarP);
        boxBotoesCima.getChildren().add(listarP);
        boxBotoesCima.getChildren().add(consultarPs);

        HBox boxBotoesBaixo = new HBox(2.5);
        boxBotoesBaixo.setAlignment(Pos.BASELINE_CENTER);
        boxBotoesBaixo.getChildren().add(botaoAdicionarR);
        boxBotoesBaixo.getChildren().add(listarR);
        boxBotoesBaixo.getChildren().add(consultarP);

        VBox boxMaisBaixa = new VBox();
        boxMaisBaixa.setAlignment(Pos.BASELINE_CENTER);
        boxMaisBaixa.getChildren().add(sair);

        VBox boxPrincipal = new VBox(2.5);
        boxPrincipal.getStyleClass().add("conteudo");
        boxPrincipal.setAlignment(Pos.CENTER);
        boxPrincipal.getChildren().addAll(labelTitulo, boxBotoesCima,  boxBotoesBaixo,  boxMaisBaixa);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaGeral = new Scene(boxPrincipal, 650, 500);
        cenaGeral.getStylesheets().add(caminhoCss);

        janela.setTitle("Sistema-loja-de-rações");
        janela.setScene(cenaGeral);
        janela.sizeToScene();
        janela.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}