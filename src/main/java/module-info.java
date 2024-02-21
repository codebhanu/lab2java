module com.example.javafxlab2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.javafxlab2 to javafx.fxml;
    exports com.example.javafxlab2;
}