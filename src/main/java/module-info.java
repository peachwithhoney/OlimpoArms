module olimpo.arms {
    requires java.desktop;
    
    requires transitive java.sql;

    exports controller;
    exports dao;
    exports database;
    exports facade;
    exports model;
    exports main;
}
