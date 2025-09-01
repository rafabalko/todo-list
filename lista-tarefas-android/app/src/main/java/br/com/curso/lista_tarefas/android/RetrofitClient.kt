package br.com.curso.lista_tarefas.android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // ESTA É A LINHA CRÍTICA!
    // Garanta que a URL base seja esta para o emulador se comunicar com o backend local.
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    val instance: TarefaApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(TarefaApiService::class.java)
    }
}