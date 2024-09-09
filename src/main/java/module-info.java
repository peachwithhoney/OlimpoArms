module olimpo.arms {
    exports controller;
    exports dao;
    exports database;
    exports facade;
    exports model;
    exports exceptions;

    requires transitive java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires mysql.connector.java;

    opens controller to javafx.fxml;
    opens model to javafx.base;
}
