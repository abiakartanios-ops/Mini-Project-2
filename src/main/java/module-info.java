module com.example.cardealershipclone1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.cardealershipclone1 to javafx.fxml;
    opens com.example.cardealershipclone1.controllers to javafx.fxml;

    exports com.example.cardealershipclone1;
    exports com.example.cardealershipclone1.controllers;
    exports com.example.cardealershipclone1.models;
    opens com.example.cardealershipclone1.models to javafx.fxml;
    opens com.example.demo1 to javafx.fxml;
}