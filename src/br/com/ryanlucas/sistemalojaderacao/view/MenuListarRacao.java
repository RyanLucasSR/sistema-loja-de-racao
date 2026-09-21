package br.com.ryanlucas.sistemalojaderacao.view;

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
import java.util.Optional;

public class MenuListarRacao {

    Scene cenaListarRacao;
    Scene cenaGeral;
    Stage janela;
    GerenciadorDeProduto gerenciadorDeProduto = new GerenciadorDeProduto();

    public MenuListarRacao(Stage janela, Scene cenaGeral) {
        this.cenaGeral = cenaGeral;
        this.janela = janela;

        TableView<ProdutoRacao> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela");

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

        List<ProdutoRacao> produtoRacaos = gerenciadorDeProduto.listarTodasRacoes();

        ObservableList<ProdutoRacao> lista = FXCollections.observableArrayList(produtoRacaos);

        tabela.setItems(lista);

        Label titulo = new Label("Lista de Rações");
        titulo.getStyleClass().add("Titulo");
        titulo.setAlignment(Pos.TOP_CENTER);

        Button voltar = new Button("<<< Voltar");
        voltar.getStyleClass().add("botoes");
        voltar.setAlignment(Pos.BASELINE_CENTER);
        voltar.setOnAction(e -> {
            janela.setScene(cenaGeral);
        });

        Button editar = new Button("Editar");
        editar.getStyleClass().add("botoes");
        editar.setAlignment(Pos.BOTTOM_CENTER);
        editar.setOnAction(e -> {
            ProdutoRacao racao = tabela.getSelectionModel().getSelectedItem();

            if(racao == null){
                mostrarErro("Selecione um produto");
                janela.setScene(cenaListarRacao);
            }

            janela.setScene(new MenuEditarRacao(janela, cenaGeral, racao).cenaEditar);
        });

        Button excluir = new Button("Excluir");
        excluir.getStyleClass().add("botoes");
        excluir.setAlignment(Pos.BOTTOM_CENTER);
        excluir.setOnAction(e -> {
            ProdutoRacao racao = tabela.getSelectionModel().getSelectedItem();

            if(racao == null){
                mostrarErro("Selecione um produto");
            }

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Excluir Produto");
            alerta.setHeaderText("Excluir Produto");
            alerta.setContentText("Tem certeza que deseja excluir / " + racao.getNome() + " /?");

            Optional<ButtonType> resultado = alerta.showAndWait();
            if(resultado.isPresent() && resultado.get() == ButtonType.OK){
                janela.setScene(cenaGeral);
                gerenciadorDeProduto.deletarProduto(racao.getId());
            }else {
                mostrarErro("Erro ao excluir Produto");
            }
        });

        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(voltar, editar,  excluir);

        VBox vbox = new VBox(2.5);
        vbox.getStyleClass().add("conteudo");

        vbox.getChildren().addAll(titulo, tabela, hBox);

        cenaListarRacao = new Scene(vbox, 1200, 500);

        String caminhoCss = getClass().getResource
                ("/br/com/ryanlucas/sistemalojaderacao/view/sistema.css").toExternalForm();

        cenaListarRacao.getStylesheets().add(caminhoCss);

        janela.setScene(cenaListarRacao);
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
