module fr.umontpellier.iut.brickbreaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;

    opens fr.umontpellier.iut.brickbreaker to javafx.fxml;
    exports fr.umontpellier.iut.brickbreaker;
    exports fr.umontpellier.iut.brickbreaker.metier.entite;
    opens fr.umontpellier.iut.brickbreaker.metier.entite to javafx.fxml;
    exports fr.umontpellier.iut.brickbreaker.view;
    opens fr.umontpellier.iut.brickbreaker.view to javafx.fxml;
    exports fr.umontpellier.iut.brickbreaker.model;
    opens fr.umontpellier.iut.brickbreaker.model to javafx.fxml;
    exports fr.umontpellier.iut.brickbreaker.controller;
    opens fr.umontpellier.iut.brickbreaker.controller to javafx.fxml;

}