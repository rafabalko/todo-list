import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarefa } from '../models/tarefa';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  private apiUrl = 'http://localhost:8080/api/tarefas';

  constructor(private http: HttpClient) { }

  // READ
  getTarefas(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(this.apiUrl);
  }

  // CREATE
  addTarefa(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.post<Tarefa>(this.apiUrl, tarefa);
  }

  // UPDATE
  updateTarefa(tarefa: Tarefa): Observable<Tarefa> {
    // Para atualizar, enviamos o objeto completo para o endpoint específico do ID
    const url = `${this.apiUrl}/${tarefa.id}`;
    return this.http.put<Tarefa>(url, tarefa);
  }

  // DELETE
  deleteTarefa(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
