package br.com.ryanlucas.sistemalojaderacao.view;

import br.com.ryanlucas.sistemalojaderacao.excecao.InvalidoException;
import br.com.ryanlucas.sistemalojaderacao.model.GerenciadorDeProduto;
import br.com.ryanlucas.sistemalojaderacao.model.ProdutoRacao;
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

public class MenuConsultarKg {

    Stage janela;
    Scene cenaGeral;
    Scene cena;
    Scene cenaConsulta;
    GerenciadorDeProduto ger = new GerenciadorDeProduto();

    public MenuConsultarKg(Stage janela, Scene cenaGeral) {
        this.janela = janela;
        this.cenaGeral = cenaGeral;

        Label titulo  = new Label("Consultar Por Kg");
        titulo.setAlignment(Pos.TOP_CENTER);
        titulo.getStyleClass().add("titulo");

        Label procurarKg  = new Label("Procurar por kg");
        procurarKg.setAlignment(Pos.TOP_LEFT);
        procurarKg.getStyleClass().add("texto");

        TextField campoKg = new TextField();
        campoKg.setAlignment(Pos.CENTER_LEFT);
        campoKg.getStyleClass().add("conteudoProduto");
        campoKg.setPromptText("Kg do produto");
        campoKg.setMinWidth(350);
        campoKg.setMaxWidth(350);

        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setOnAction(e -> {
            janela.setScene(cenaGeral);
        });

        Button procurar = new Button("Procurar");
        procurar.getStyleClass().add("botoes");
        procurar.setOnAction(e -> {
            try {
                double kg = Double.parseDouble(campoKg.getText().replace(",", "."));

                janela.setScene(cenaConsulta);
                consultarKg(kg);
            }catch (InvalidoException ev){
                mostrarErro(ev.getMessage());
                janela.setScene(cenaGeral);
            }
        });

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.getChildren().addAll(campoKg, procurar);

        VBox nomeProdutoVbox = new VBox(5);
        nomeProdutoVbox.setAlignment(Pos.CENTER_LEFT);
        nomeProdutoVbox.getChildren().addAll(procurarKg, hbox);

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getStyleClass().add("conteudo");
        vbox.getChildren().addAll(titulo, nomeProdutoVbox, voltar);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cena = new Scene(vbox, 700, 300);
        cena.getStylesheets().add(caminhoCss);

        janela.setScene(cena);
        janela.sizeToScene();
        janela.show();
    }

    public void consultarKg(double kg) {

        List<ProdutoRacao> lista = ger.consultarKg(kg);

        TableView<ProdutoRacao> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela");
        tabela.setMinSize(500, 520);

        TableColumn<ProdutoRacao, String> colunaNome = new TableColumn<>("nome");
        TableColumn<ProdutoRacao, String> colunaDescricao = new TableColumn<>("descricao");
        TableColumn<ProdutoRacao, Double> colunaPreco = new TableColumn<>("preco");
        TableColumn<ProdutoRacao, String> colunaCastrado = new TableColumn<>("castrado");
        TableColumn<ProdutoRacao, String> colunaFilhote = new TableColumn<>("filhote");
        TableColumn<ProdutoRacao, Integer> colunaPeso = new TableColumn<>("peso");
        TableColumn<ProdutoRacao, String> colunaPorte = new TableColumn<>("porte");
        TableColumn<ProdutoRacao, Double> colunaPrecoKg = new TableColumn<>("precoPorKg");
        TableColumn<ProdutoRacao, String> colunaTipo = new TableColumn<>("tipoAnimal");

        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );
        colunaDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );

        colunaPreco.setCellValueFactory(
                new PropertyValueFactory<>("preco")
        );

        colunaCastrado.setCellValueFactory(
                new PropertyValueFactory<>("castrado")
        );

        colunaFilhote.setCellValueFactory(
                new PropertyValueFactory<>("filhote")
        );

        colunaPeso.setCellValueFactory(
                new PropertyValueFactory<>("peso")
        );

        colunaPorte.setCellValueFactory(
                new PropertyValueFactory<>("porte")
        );

        colunaPrecoKg.setCellValueFactory(
                new PropertyValueFactory<>("precoPorKg")
        );

        colunaTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipoAnimal")
        );

        tabela.getColumns().add(colunaNome);
        tabela.getColumns().add(colunaDescricao);
        tabela.getColumns().add(colunaPreco);
        tabela.getColumns().add(colunaCastrado);
        tabela.getColumns().add(colunaFilhote);
        tabela.getColumns().add(colunaPeso);
        tabela.getColumns().add(colunaPorte);
        tabela.getColumns().add(colunaPrecoKg);
        tabela.getColumns().add(colunaTipo);

        ObservableList<ProdutoRacao> listaRacoes = FXCollections.observableArrayList(lista);

        tabela.setItems(listaRacoes);

        Label titulo = new Label("Consultar Por Kg");
        titulo.getStyleClass().add("titulo");
        titulo.setAlignment(Pos.TOP_CENTER);

        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setAlignment(Pos.BASELINE_CENTER);
        voltar.setOnAction(e -> {
            janela.setScene(cenaGeral);
        });

        Button outraConsulta = new  Button("Outra Consulta");
        outraConsulta.getStyleClass().add("botoes");
        outraConsulta.setAlignment(Pos.BASELINE_CENTER);
        outraConsulta.setOnAction(e -> {
            janela.setScene(cena);
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

        cenaConsulta = new Scene(vbox, 1200, 700);
        cenaConsulta.getStylesheets().add(caminhoCss);

        janela.setScene(cenaConsulta);
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
