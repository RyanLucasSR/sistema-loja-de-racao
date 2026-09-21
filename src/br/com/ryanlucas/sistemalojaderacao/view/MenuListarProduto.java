package br.com.ryanlucas.sistemalojaderacao.view;

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
import java.util.Optional;

public class MenuListarProduto {

    Stage janela;
    Scene cenaListarProduto;
    Scene cenaGeral;
    GerenciadorDeProduto gerenciadorDeProduto = new GerenciadorDeProduto();

    public MenuListarProduto(Stage janelaGeral, Scene cenaGeral) {
        this.janela = janelaGeral;
        this.cenaGeral = cenaGeral;

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

        List<Produto> produtos = gerenciadorDeProduto.listarTodosProdutos();

        ObservableList<Produto> lista = FXCollections.observableArrayList(produtos);

        tabela.setItems(lista);

        Label labelTitulo = new Label("Lista de Produtos");
        labelTitulo.getStyleClass().add("titulo");
        labelTitulo.setAlignment(Pos.TOP_CENTER);

        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setAlignment(Pos.BOTTOM_CENTER);
        voltar.setOnAction(e -> {
           janela.setScene(cenaGeral);
        });

        Button editar = new Button("Editar");
        editar.getStyleClass().add("botoes");
        editar.setAlignment(Pos.BOTTOM_CENTER);
        editar.setOnAction(e -> {
            Produto produto = tabela.getSelectionModel().getSelectedItem();

            if(produto == null){
                mostrarErro("Selecione um produto");
                janela.setScene(cenaListarProduto);
            }

            new MenuEditarProduto(cenaGeral, janela, produto);
        });

        Button exlcuir = new Button("Excluir");
        exlcuir.getStyleClass().add("botoes");
        exlcuir.setAlignment(Pos.BOTTOM_CENTER);
        exlcuir.setOnAction(e -> {
            Produto produto = tabela.getSelectionModel().getSelectedItem();

            if (produto == null) {
                mostrarErro("Selecione um produto");
            }

            Alert alerta = new  Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar exclusão");
            alerta.setHeaderText("Excluir produto");
            alerta.setContentText("Tem certeza que deseja excluir / " + produto.getNome() + " /?");

            Optional<ButtonType> resultado = alerta.showAndWait();

            if(resultado.isPresent() && resultado.get() == ButtonType.OK){
                janela.setScene(cenaGeral);
                gerenciadorDeProduto.deletarProduto(produto.getId());
            }else {
                janela.setScene(cenaGeral);
                mostrarErro("Erro ao excluir produto");
            }

        });

        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(voltar, editar, exlcuir);


        VBox vbox = new VBox(5);
        vbox.getStyleClass().add("conteudo");
        vbox.setAlignment(Pos.TOP_CENTER);

        vbox.getChildren().addAll(labelTitulo, tabela, hBox);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaListarProduto = new Scene(vbox, 1200, 700);
        cenaListarProduto.getStylesheets().add(caminhoCss);

        janela.setScene(cenaListarProduto);
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
