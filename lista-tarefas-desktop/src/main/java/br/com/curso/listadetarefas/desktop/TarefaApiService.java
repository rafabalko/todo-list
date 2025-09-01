package br.com.curso.listadetarefas.desktop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class TarefaApiService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String API_URL = "http://localhost:8080/api/tarefas";

    /**
     * Lista todas as tarefas da API.
     * @return lista de tarefas ou lista vazia em caso de erro
     */
    public List<Tarefa> listarTarefas() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), new TypeReference<>() {});
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Adiciona uma nova tarefa através da API.
     * @param novaTarefa O objeto Tarefa a ser criado (sem o ID).
     * @return O objeto Tarefa criado, agora com o ID retornado pela API, ou null em caso de erro.
     */
    public Tarefa adicionarTarefa(Tarefa novaTarefa) {
        try {
            // 1. Converte o objeto Java (novaTarefa) em uma string JSON
            String jsonBody = objectMapper.writeValueAsString(novaTarefa);

            // 2. Cria a requisição POST, definindo o cabeçalho e o corpo
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json") // Informa à API que estamos enviando JSON
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody)) // Define o método como POST e envia o corpo
                    .build();

            // 3. Envia a requisição
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Verifica se a criação foi bem-sucedida (código 200 ou 201)
            if (response.statusCode() == 200 || response.statusCode() == 201) {
                // Converte a resposta (que é a tarefa criada com ID) de volta para um objeto Java
                return objectMapper.readValue(response.body(), Tarefa.class);
            } else {
                System.err.println("Erro ao adicionar tarefa: " + response.statusCode() + " - " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro de conexão ou ao processar a requisição: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Atualiza uma tarefa existente.
     * @param tarefa Tarefa a ser atualizada (deve ter ID)
     */
    public void atualizarTarefa(Tarefa tarefa) {
        try {
            String jsonBody = objectMapper.writeValueAsString(tarefa);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + tarefa.getId()))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Deleta uma tarefa pelo ID.
     * @param id ID da tarefa a ser deletada
     */
    public void deletarTarefa(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .DELETE()
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao deletar tarefa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
