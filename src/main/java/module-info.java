module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.dataformat.xml;


    opens app to javafx.fxml;
    opens core.people to com.fasterxml.jackson.databind, javafx.base;
    opens core.vehicles to com.fasterxml.jackson.databind, javafx.base;
    opens core.booking to com.fasterxml.jackson.databind, javafx.base;

    exports app;
    exports core.people;
    exports core.vehicles;
    exports core.booking;
    exports json_manager;
    exports backup_manager;
    exports logging;
    exports utilities.menus;
}