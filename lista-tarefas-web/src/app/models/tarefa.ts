// src/app/models/tarefa.ts
export interface Tarefa {
  id?: number; // O ID é opcional, pois o backend o gera na criação.
  descricao: string;
  concluida: boolean;
  editando?: boolean; // Propriedade para controlar o estado de edição na UI
}
