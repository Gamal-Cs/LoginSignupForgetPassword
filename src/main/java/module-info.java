module com.mycompany.loginsignupfunctionality {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome; // ⬅️ المهم

    opens com.mycompany.loginsignupfunctionality to javafx.fxml;
    exports com.mycompany.loginsignupfunctionality;
}
