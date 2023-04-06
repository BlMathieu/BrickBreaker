package fr.umontpellier.iut.brickbreaker;

import fr.umontpellier.iut.brickbreaker.controller.BrickBreakerController;
import fr.umontpellier.iut.brickbreaker.controller.GameMenuController;
import fr.umontpellier.iut.brickbreaker.metier.entite.Player;
import fr.umontpellier.iut.brickbreaker.stockage.sql.SQLUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class BrickBreakerApplication extends Application {
    private BrickBreakerController brickBreakerController;
    private GameMenuController gameMenu;
    @Override
    public void start(Stage stage) throws IOException {
        brickBreakerController = new BrickBreakerController();
        gameMenu = new GameMenuController(brickBreakerController);
        Scene scene = new Scene(brickBreakerController, 1280, 720);
        stage.setTitle("Casse-Briques");
        stage.setScene(scene);
        stage.setResizable(false); // Empêche le redimensionnement de la fenêtre
        stage.show();
        gameMenu.startMenu();
        brickBreakerController.createBindings();

    }

    @Override
    public void stop() throws Exception {
        SQLUtils.getInstance().closeConnection();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}