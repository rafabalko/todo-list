# 📋 Lista de Tarefas WEB

Um sistema simples de **lista de tarefas (To-Do List)** desenvolvido para fins de estudo e prática.  
Permite criar, listar, atualizar e remover tarefas de forma rápida e organizada.

---
## ✨ Funcionalidades

- ✅ Adicionar novas tarefas
- 📌 Listar todas as tarefas
- ✏️ Editar/atualizar uma tarefa
- 🗑️ Remover uma tarefa

---

## 🛠️ Tecnologias Utilizadas

### Backend
- [Spring Boot](https://spring.io/projects/spring-boot) – API REST
- [Maven](https://maven.apache.org/) – Gerenciador de dependências
- [Java](https://www.oracle.com/java/) – Linguagem principal

### Frontend
- [Angular](https://angular.io/) – Framework frontend
- [TypeScript](https://www.typescriptlang.org/) – Linguagem para o frontend

### Banco de Dados
- [H2 Database](https://www.h2database.com/)

---

## 🚀 Como Executar o Projeto
### 📥 Clonar o repositório
```
git clone https://github.com/rafabalko/lista-tarefas.git
cd lista-tarefas/lista-tarefas-web
```

### ✅ Rodando pelo IntelliJ IDEA
1. Abra o **IntelliJ IDEA**.
2. Clique em **File > Open** e selecione a pasta do projeto (`lista-tarefas`).
3. No canto esquerdo, você verá as três pastas:
  - `lista-tarefas-api`
  - `lista-tarefas-web`
  - `lista-tarefas-desktop`
4. Navegue até:  
```
lista-tarefas-api/src/main/java/br/com/curso/lista_tarefas/api
```
5. Localize o arquivo `ListaTarefasApiApplication.java`, clique nele com o botão direito e escolha **Run**.
- A API ficará disponível em: 👉 http://localhost:8080

6. Depois, abra o **terminal dentro do IntelliJ** e execute:
```bash
  cd lista-tarefas-web
ng serve --open
```
- O Angular vai iniciar o servidor web.
- O navegador será aberto automaticamente em: 👉 http://localhost:4200/
---

### ✅ Rodando pelo Terminal (sem IDE)

1. Abra o terminal e inicie a API:
```bash
   cd C:\Users\SEU-USUARIO\PASTA-ONDE-FOI-BAIXADA\lista-tarefas\lista-tarefas-api
   mvn spring-boot:run
```
- Troque o **"SEU-USUARIO"** pelo nome do seu usuário;
- Troque **"PASTA-ONDE-FOI-BAIXADA"** pelo caminho da onde a pasta **"lista-tarefas"** se encontra.
- A API ficará disponível em: 👉 http://localhost:8080

2. Em outro aba do terminal, inicie o frontend:
```bash
   cd C:\Users\SEU-USUARIO\PASTA-ONDE-FOI-BAIXADA\lista-tarefas\lista-tarefas-web
   npm install   # apenas na primeira vez
   ng serve --open
```
- Troque o **"SEU-USUARIO"** pelo nome do seu usuário;
- Troque **"PASTA-ONDE-FOI-BAIXADA"** pelo caminho da onde a pasta **"lista-tarefas"** se encontra. 
- O frontend ficará disponível em: 👉 http://localhost:4200

---
## Rodapé
Desenvolvido por **Rafael Balko**.  
[GitHub](https://github.com/rafabalko)  
[Instagram](https://www.instagram.com/rafaabalko/?hl=pt-br)
