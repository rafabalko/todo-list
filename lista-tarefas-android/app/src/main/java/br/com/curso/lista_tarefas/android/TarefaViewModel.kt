package br.com.curso.lista_tarefas.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class TarefaUiState(
    val tarefas: List<Tarefa> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class TarefaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TarefaUiState())
    val uiState: StateFlow<TarefaUiState> = _uiState.asStateFlow()
    private val TAG = "TarefaViewModel"

    init {
        carregarTarefas()
    }

    fun carregarTarefas() {
        Log.d(TAG, "Iniciando o carregamento de tarefas...")
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val tarefasDaApi = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getTarefas()
                }
                withContext(Dispatchers.Main) {
                    _uiState.update { it.copy(isLoading = false, tarefas = tarefasDaApi) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "Falha CRÍTICA ao carregar tarefas", e)
                    _uiState.update { it.copy(isLoading = false, error = "Falha ao carregar tarefas") }
                }
            }
        }
    }

    // --- MÉTODOS DE CRUD ADICIONADOS ---

    fun adicionarTarefa(descricao: String) {
        viewModelScope.launch {
            try {
                val novaTarefa = Tarefa(id = null, descricao = descricao, concluida = false)
                val tarefaAdicionada = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.addTarefa(novaTarefa)
                }
                withContext(Dispatchers.Main) {
                    _uiState.update { it.copy(tarefas = it.tarefas + tarefaAdicionada) }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Falha ao adicionar tarefa", e)
            }
        }
    }

    fun updateTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                tarefa.id?.let {
                    // A chamada de rede continua a mesma
                    val tarefaAtualizada = withContext(Dispatchers.IO) {
                        RetrofitClient.instance.updateTarefa(it, tarefa)
                    }
                    // A lógica na UI foi melhorada para substituir o item
                    withContext(Dispatchers.Main) {
                        _uiState.update { currentState ->
                            val tarefasAtualizadas = currentState.tarefas.map { t ->
                                if (t.id == tarefaAtualizada.id) tarefaAtualizada else t
                            }
                            currentState.copy(tarefas = tarefasAtualizadas)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Falha ao atualizar tarefa", e)
            }
        }
    }

    fun deleteTarefa(id: Long?) {
        viewModelScope.launch {
            try {
                id?.let {
                    withContext(Dispatchers.IO) {
                        RetrofitClient.instance.deleteTarefa(it)
                    }
                    withContext(Dispatchers.Main) {
                        _uiState.update { currentState ->
                            currentState.copy(tarefas = currentState.tarefas.filter { t -> t.id != id })
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Falha ao deletar tarefa", e)
            }
        }
    }
}