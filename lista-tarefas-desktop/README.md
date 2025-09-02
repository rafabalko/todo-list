# 💻 Lista de Tarefas DESKTOP

Um sistema simples de **lista de tarefas (To-Do List)** desenvolvido para fins de estudo e prática.  
Permite criar, listar, atualizar e remover tarefas de forma rápida e organizada, utilizando o **desktop** como frontend e uma **API Spring Boot** como backend.

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
- [JavaFX](https://openjfx.io/) – Interface gráfica desktop

### Banco de Dados
- [H2 Database](https://www.h2database.com/)

---

## 🚀 Como Executar o Projeto
### 📥 Clonar o repositório
```
git clone https://github.com/rafabalko/lista-tarefas.git
cd lista-tarefas/lista-tarefas-desktop
```
### ✅ Rodando pelo IntelliJ IDEA
1. Abra o **IntelliJ IDEA**.
2. Clique em **File > Open**.
3. Dê 2 cliques na pasta `lista-tarefas` e mais 2 cliques na subpasta `lista-tarefas-api` para abrir na IDE.

> Isso deve selecionar automaticamente as duas pastas principais do projeto:
> - `lista-tarefas-api`
> - `lista-tarefas-desktop`

4. Para rodar o **Backend**, abra a classe principal:  
```
lista-tarefas-api/src/main/java/br/com/curso/lista_tarefas/api/ListaTarefasApiApplication.java
```
- Clique com o botão direito e selecione **Run**.
- A API ficará disponível em: 👉 `http://localhost:8080`

5. Para rodar o **Frontend Desktop**, abra a classe principal:
```
lista-tarefas-desktop/src/main/java/br/com/curso/listadetarefas/desktop/MainApp.java
```
6. Localize o arquivo **Main.java** dentro da pasta desktop e clique **Run**.

---

### ✅ Rodando pelo Terminal (sem IDE)

1. Abra o terminal e inicie a API:
```bash
cd C:\Users\SEU-USUARIO\PASTA-ONDE-FOI-BAIXADA\lista-tarefas\lista-tarefas-api
mvn spring-boot:run
```
- Troque o "SEU-USUARIO" pelo nome do seu usuário;
- Troque "PASTA-ONDE-FOI-BAIXADA" pelo caminho da onde a pasta "lista-tarefas" se encontra.
- A API ficará disponível em: 👉 http://localhost:8080

2. Em outra aba do terminal, compile e execute a versão desktop:
```bash
  cd C:\Users\SEU-USUARIO\PASTA-ONDE-FOI-BAIXADA\lista-tarefas\lista-tarefas-desktop
  mvn clean package
  mvn javafx:run
```
- Troque o "SEU-USUARIO" pelo nome do seu usuário;
- Troque "PASTA-ONDE-FOI-BAIXADA" pelo caminho da onde a pasta "lista-tarefas" se encontra.
---
### 🧪 Testando a API
#### Você pode testar de diversas formas:

1. Diretamente pelo frontend desktop

- Abra a aplicação desktop e veja se as tarefas aparecem.

- Clique em Atualizar Tarefas caso não apareçam automaticamente.

2. Via YARC (extensão do Chrome)

- URL: http://localhost:8080/api/tarefas

- Método: POST

- Body (JSON):
```
{
"descricao": "Aprender Python",
"concluida": false
}
```
- Clique em SEND, retornará 200 OK se der certo.

3. Via Postman

- URL: http://localhost:8080/api/tarefas

- Método: POST

- Aba Body > RAW > formato JSON

- Exemplo de tarefa:
```
{
"descricao": "Aprender Python",
"concluida": false
}
```
- Clique em SEND, retornará 200 OK se der certo.

---
## Rodapé
Desenvolvido por **Rafael Balko**.  
[GitHub](https://github.com/rafabalko)  
[Instagram](https://www.instagram.com/rafaabalko/?hl=pt-br)