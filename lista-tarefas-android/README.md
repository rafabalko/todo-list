# 📱 Lista de Tarefas Android

Um aplicativo móvel simples de **lista de tarefas (To-Do List)** desenvolvido para fins de estudo e prática.  
Permite criar, listar, atualizar e remover tarefas de forma rápida e organizada.

---

## ✨ Funcionalidades

- ✅ Adicionar novas tarefas
- 📌 Listar todas as tarefas
- ✏️ Editar/atualizar uma tarefa
- 🗑️ Remover uma tarefa

---

## 🛠️ Tecnologias Utilizadas

- [Android Studio](https://developer.android.com/studio) – IDE principal
- [Java](https://www.oracle.com/java/) / [Kotlin](https://kotlinlang.org/) – Linguagem principal
- [Android SDK](https://developer.android.com/studio) – Desenvolvimento Android
- [Room / SQLite / SharedPreferences] – Persistência de dados local

---

## 🚀 Como Executar o Projeto

### 📥 Clonar o repositório
```bash
git clone https://github.com/rafabalko/lista-tarefas.git
cd lista-tarefas/lista-tarefas-android
```
### ✅ Rodando pelo Android Studio

1. Abra o Android Studio.

- Clique em File > Open e selecione a pasta do projeto lista-tarefas-android.
- Aguarde o Gradle Sync finalizar.
- Abra o AVD Manager (ícone de celular no canto superior direito).
- Inicie um emulador compatível (recomendado: Pixel 7, API 34).
- Se o emulador estiver travado, use Cold Boot Now ou Wipe Data.
- Clique em Run (Play) para compilar e instalar o app no emulador.
- Caso o emulador apresente problemas, você pode testar em um dispositivo físico via USB.

### ✅ Rodando pelo Terminal / linha de comando

- Certifique-se de que o ADB está configurado no PATH do sistema.
2. No PowerShell ou Terminal, liste os dispositivos conectados:
```
adb devices
```

3. Verifique se o emulador aparece como device.
- Gere o APK (caso não queira rodar pelo Android Studio):
```bash
cd lista-tarefas-android
./gradlew assembleDebug
```
- O APK será gerado em app/build/outputs/apk/debug/app-debug.apk.
4. Instale o APK no emulador:
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```
### 📌 Observações
- O app funciona melhor em dispositivos API 24 ou superior.
- Todas as tarefas criadas são salvas localmente no dispositivo.
- Para testar em outra máquina, certifique-se de que Android Studio, SDK e AVD estejam instalados corretamente.

---

## Rodapé
Desenvolvido por **Rafael Balko**.  
[GitHub](https://github.com/rafabalko)  
[Instagram](https://www.instagram.com/rafaabalko/?hl=pt-br)



