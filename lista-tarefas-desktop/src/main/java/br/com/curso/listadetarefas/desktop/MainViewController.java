package br.com.curso.listadetarefas.desktop;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML private TableView<Tarefa> tabelaTarefas;
    @FXML private TableColumn<Tarefa, Boolean> colunaConcluida;
    @FXML private TableColumn<Tarefa, String> colunaDescricao;
    @FXML private TableColumn<Tarefa, Void> colunaAcoes;
    @FXML private TextField campoNovaTarefa;
    @FXML private Button botaoAdicionar;
    @FXML private Label labelStatus;
    @FXML private Button botaoAtualizar;

    private final TarefaApiService tarefaApiService = new TarefaApiService();
    private final ObservableList<Tarefa> tarefasObservaveis = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabelaTarefas.setItems(tarefasObservaveis);
        tabelaTarefas.setEditable(true);

        // Descrição: célula editável
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaDescricao.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaDescricao.setOnEditCommit(event -> {
            Tarefa tarefaEditada = event.getRowValue();
            if (tarefaEditada != null) {
                tarefaEditada.setDescricao(event.getNewValue());
                atualizarTarefa(tarefaEditada);
            }
        });

        // Concluída: checkbox na célula
        colunaConcluida.setCellValueFactory(new PropertyValueFactory<>("concluida"));
        colunaConcluida.setCellFactory(tc -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();
            {
                checkBox.setOnAction(event -> {
                    Tarefa tarefa = getTableRow().getItem();
                    if (tarefa != null) {
                        tarefa.setConcluida(checkBox.isSelected());
                        atualizarTarefa(tarefa);
                    }
                });
            }
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                }
            }
        });

        // Ações: botão Deletar
        Callback<TableColumn<Tarefa, Void>, TableCell<Tarefa, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btnDeletar = new Button("Deletar");
            {
                btnDeletar.setOnAction(event -> {
                    Tarefa tarefa = getTableView().getItems().get(getIndex());
                    if (tarefa != null) {
                        deletarTarefa(tarefa);
                    }
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDeletar);
                }
            }
        };
        colunaAcoes.setCellFactory(cellFactory);

        carregarTarefas();
    }

    @FXML
    private void atualizarListaDeTarefas() {
        carregarTarefas();
    }

    /**
     * Adiciona a nova tarefa usando Task<Tarefa> para receber o objeto criado pela API.
     * Isso evita que a tabela exiba uma linha em branco (quando o backend não atualiza imediatamente o GET).
     */
    @FXML
    private void onAdicionarAction() {
        final String descricao = campoNovaTarefa.getText();

        if (descricao == null || descricao.trim().isEmpty()) {
            labelStatus.setText("Status: Descrição não pode ser vazia.");
            return;
        }

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setDescricao(descricao.trim());
        novaTarefa.setConcluida(false);

        Task<Tarefa> task = new Task<>() {
            @Override
            protected Tarefa call() throws Exception {
                return tarefaApiService.adicionarTarefa(novaTarefa); // pega a resposta da API (com id, descrição, concluída)
            }
        };

        task.setOnSucceeded(event -> {
            Tarefa tarefaCriada = task.getValue();
            if (tarefaCriada != null) {
                tarefasObservaveis.add(tarefaCriada);
                campoNovaTarefa.clear();
                labelStatus.setText("Status: Tarefa adicionada com sucesso!");
            } else {
                // Caso a API não retorne o objeto, recarregamos (fallback)
                carregarTarefas();
                campoNovaTarefa.clear();
                labelStatus.setText("Status: Tarefa adicionada (recarregada).");
            }
        });

        task.setOnFailed(event -> {
            labelStatus.setText("Status: Erro ao adicionar tarefa.");
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }

    private void carregarTarefas() {
        executarEmBackground(() -> {
            List<Tarefa> tarefasDaApi = tarefaApiService.listarTarefas();
            Platform.runLater(() -> {
                tarefasObservaveis.setAll(tarefasDaApi);
                labelStatus.setText("Status: Tarefas carregadas.");
            });
        });
    }

    private void atualizarTarefa(Tarefa tarefa) {
        executarEmBackground(() -> {
            tarefaApiService.atualizarTarefa(tarefa);
            Platform.runLater(() -> labelStatus.setText("Status: Tarefa '" + tarefa.getDescricao() + "' atualizada."));
        });
    }

    private void deletarTarefa(Tarefa tarefa) {
        executarEmBackground(() -> {
            tarefaApiService.deletarTarefa(tarefa.getId());
            Platform.runLater(() -> {
                tarefasObservaveis.remove(tarefa);
                labelStatus.setText("Status: Tarefa deletada.");
            });
        });
    }

    /**
     * Helper para executar qualquer Runnable em background com tratamento básico de erro.
     */
    private void executarEmBackground(Runnable acao) {
        labelStatus.setText("Status: Processando...");
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                acao.run();
                return null;
            }
        };
        task.setOnFailed(e -> {
            task.getException().printStackTrace();
            Platform.runLater(() -> labelStatus.setText("Status: Erro na operação. Veja o console."));
        });
        new Thread(task).start();
    }
}
