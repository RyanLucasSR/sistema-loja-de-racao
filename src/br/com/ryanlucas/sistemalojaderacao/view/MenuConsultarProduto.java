package br.com.ryanlucas.sistemalojaderacao.view;

import br.com.ryanlucas.sistemalojaderacao.excecao.CampoObrigatorioException;
import br.com.ryanlucas.sistemalojaderacao.model.GerenciadorDeProduto;
import br.com.ryanlucas.sistemalojaderacao.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MenuConsultarProduto {

    Scene cenageral;
    Stage janela;
    Scene cenaConsultarP;
    Scene cenaConsultar;
    GerenciadorDeProduto ger = new GerenciadorDeProduto();

    public MenuConsultarProduto(Stage janela, Scene cenageral) {
        this.janela = janela;
        this.cenageral = cenageral;

        Label titulo = new Label("Consultar Produto");
        titulo.getStyleClass().add("titulo");
        titulo.setAlignment(Pos.CENTER);

        Label nomeProduto = new Label("Nome do produto:");
        nomeProduto.setAlignment(Pos.TOP_LEFT);
        nomeProduto.getStyleClass().add("texto");

        TextField nome = new TextField();
        nome.setAlignment(Pos.CENTER_LEFT);
        nome.getStyleClass().add("conteudoProduto");
        nome.setPromptText("Nome do produto");
        nome.setMinWidth(350);
        nome.setMaxWidth(350);


        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setAlignment(Pos.BASELINE_CENTER);
        voltar.setOnAction(e -> {
            janela.setScene(cenageral);
        });

        Button procurar = new Button("Procurar");
        procurar.getStyleClass().add("botoes");
        procurar.setOnAction(e -> {
            try {
                String nomeConsulta = nome.getText();

                janela.setScene(cenaConsultar);
                consultarProdutos(nomeConsulta);
            }catch (CampoObrigatorioException ex){
                mostrarErro(ex.getMessage());
                janela.setScene(cenageral);
            }
        });

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.getChildren().addAll(nome, procurar);

        VBox nomeProdutoVbox = new VBox(5);
        nomeProdutoVbox.setAlignment(Pos.TOP_LEFT);
        nomeProdutoVbox.getChildren().addAll(nomeProduto, hbox);

        VBox vbox = new VBox(10);
        vbox.getStyleClass().add("conteudo");
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(titulo, nomeProdutoVbox, voltar);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaConsultarP = new Scene(vbox, 700, 300);
        cenaConsultarP.getStylesheets().add(caminhoCss);

        janela.setScene(cenaConsultarP);
        janela.sizeToScene();
        janela.show();
    }

    public void consultarProdutos(String nomeConsulta) {

        List<Produto> lista = ger.consultarUm(nomeConsulta);

        TableView<Produto> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela");
        tabela.setMinSize(500, 520);

        TableColumn<Produto, String> colunaNome = new TableColumn<>("nome");
        TableColumn<Produto, String> colunaDescricao = new TableColumn<>("descricao");
        TableColumn<Produto, Double> colunaPreco = new TableColumn<>("preco");

        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colunaDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );

        colunaPreco.setCellValueFactory(
                new PropertyValueFactory<>("preco")
        );

        tabela.getColumns().add(colunaNome);
        tabela.getColumns().add(colunaDescricao);
        tabela.getColumns().add(colunaPreco);

        ObservableList<Produto> listaProduto = FXCollections.observableList(lista);
        

        tabela.setItems(listaProduto);

        Label titulo = new Label("Consultar Produto");
        titulo.getStyleClass().add("titulo");
        titulo.setAlignment(Pos.TOP_CENTER);

        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setAlignment(Pos.BASELINE_CENTER);
        voltar.setOnAction(e -> {
            janela.setScene(cenageral);
        });

        Button outraConsulta = new Button("Outra Consulta");
        outraConsulta.getStyleClass().add("botoes");
        outraConsulta.setAlignment(Pos.BASELINE_CENTER);
        outraConsulta.setOnAction(e -> {
            janela.setScene(cenaConsultarP);
        });

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.getChildren().addAll(voltar, outraConsulta);

        VBox vbox = new VBox(2.5);
        vbox.getStyleClass().add("conteudo");
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(titulo, tabela, hbox);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaConsultar = new  Scene(vbox, 1200, 700);
        cenaConsultar.getStylesheets().add(caminhoCss);
        janela.setScene(cenaConsultar);
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
