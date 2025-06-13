module GerEquipamentos {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports Main;
    exports ui.GUI to javafx.fxml;
    opens ui.GUI to javafx.fxml;
}