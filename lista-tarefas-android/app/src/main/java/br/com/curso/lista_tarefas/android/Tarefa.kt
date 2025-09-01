package br.com.curso.lista_tarefas.android

// A data class em Kotlin já gera getters, setters, equals, etc.
data class Tarefa(
    val id: Long?,
    var descricao: String,
    var concluida: Boolean
)