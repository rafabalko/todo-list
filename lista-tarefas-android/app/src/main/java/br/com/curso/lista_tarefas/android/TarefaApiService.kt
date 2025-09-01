package br.com.curso.lista_tarefas.android

import retrofit2.Response
import retrofit2.http.*

interface TarefaApiService {
    @GET("tarefas")
    suspend fun getTarefas(): List<Tarefa> // 'suspend' indica que é para coroutines

    @POST("tarefas")
    suspend fun addTarefa(@Body tarefa: Tarefa): Tarefa

    @PUT("tarefas/{id}")
    suspend fun updateTarefa(@Path("id") id: Long, @Body tarefa: Tarefa): Tarefa

    @DELETE("tarefas/{id}")
    suspend fun deleteTarefa(@Path("id") id: Long): Response<Void>
}