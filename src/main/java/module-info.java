module com.example.javafxlab2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.javafxlab2 to javafx.fxml;
    opens com.example.javafxlab2.model to javafx.base;
    exports com.example.javafxlab2;
}