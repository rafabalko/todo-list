import { Component, OnInit } from '@angular/core';
import { TarefaService } from '../../services/tarefa';
import { Tarefa } from '../../models/tarefa';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [ CommonModule, FormsModule ],
  templateUrl: './task-list.html',
  styleUrls: ['./task-list.css']
})
export class TaskListComponent implements OnInit {

  tarefas: Tarefa[] = [];
  novaTarefa: Tarefa = { descricao: '', concluida: false };
  private descricaoOriginal: string = ''; // Guarda o texto original durante a edição

  constructor(private tarefaService: TarefaService) { }

  ngOnInit(): void {
    this.carregarTarefas();
    document.body.classList.add('light-theme');
  }

  carregarTarefas(): void {
    this.tarefaService.getTarefas().subscribe(tarefasRecebidas => {
      this.tarefas = tarefasRecebidas;
    });
  }

  adicionarTarefa(): void {
    if (this.novaTarefa.descricao.trim() === '') return;

    this.tarefaService.addTarefa(this.novaTarefa).subscribe(tarefaAdicionada => {
      this.tarefas.push(tarefaAdicionada);
      this.novaTarefa = { descricao: '', concluida: false };
    });
  }

  atualizarStatus(tarefa: Tarefa): void {
    this.tarefaService.updateTarefa(tarefa).subscribe(() => {
      console.log('Status da tarefa ' + tarefa.id + ' atualizado.');
    });
  }

  deletarTarefa(id: number | undefined): void {
    if (id === undefined) return;

    this.tarefaService.deleteTarefa(id).subscribe(() => {
      this.tarefas = this.tarefas.filter(t => t.id !== id);
      console.log('Tarefa ' + id + ' deletada.');
    });
  }

  // --- NOVOS MÉTODOS PARA EDIÇÃO INLINE ---
  iniciarEdicao(tarefa: Tarefa): void {
    this.descricaoOriginal = tarefa.descricao; // Salva o valor original
    tarefa.editando = true;
  }

  salvarEdicao(tarefa: Tarefa): void {
    if (tarefa.descricao.trim() === '') {
      tarefa.descricao = this.descricaoOriginal; // Restaura se ficar vazio
    }
    tarefa.editando = false;
    this.atualizarStatus(tarefa); // Atualiza no backend
  }

  cancelarEdicao(tarefa: Tarefa): void {
    tarefa.descricao = this.descricaoOriginal; // Restaura o valor original
    tarefa.editando = false;
  }
  toggleTheme() {
    // Pega o elemento host do componente
    const host = document.querySelector('app-task-list');
    if (!host) return;

    // Alterna a classe dark-theme
    if (host.classList.contains('dark-theme')) {
      host.classList.remove('dark-theme');
    } else {
      host.classList.add('dark-theme');
    }
  }

}
