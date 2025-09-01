// In src/main/java/module-info.java

module br.com.curso.todolist.desktop {
    // ---- JavaFX Dependencies ----
    requires javafx.controls;
    requires javafx.fxml;

    // ---- External Library Dependencies ----
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    // ---- Module System Permissions ----
    // Allows JavaFX to launch the application
    exports br.com.curso.listadetarefas.desktop;

    // **THIS IS THE CORRECTED, COMBINED LINE**
    // Allows FXML and Jackson to access your package via reflection
    opens br.com.curso.listadetarefas.desktop to javafx.fxml, com.fasterxml.jackson.databind;
}