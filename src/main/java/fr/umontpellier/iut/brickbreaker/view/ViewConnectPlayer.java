package fr.umontpellier.iut.brickbreaker.view;

import fr.umontpellier.iut.brickbreaker.BrickBreakerApplication;
import fr.umontpellier.iut.brickbreaker.controller.BrickBreakerController;
import fr.umontpellier.iut.brickbreaker.controller.GameMenuController;
import fr.umontpellier.iut.brickbreaker.metier.entite.AuthPlayer;
import fr.umontpellier.iut.brickbreaker.metier.manager.PlayerManager;
import fr.umontpellier.iut.brickbreaker.model.Brick;
import fr.umontpellier.iut.brickbreaker.stockage.Security;
import fr.umontpellier.iut.brickbreaker.stockage.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class ViewConnectPlayer extends Pane {
    private BrickBreakerController brickBreakerController;
    private GameMenuController gameMenu;

    @FXML
    private Text title;
    @FXML
    private Text pText;
    @FXML
    private Text lText;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Text quitText;
    @FXML
    private Text connectBtText;
    @FXML
    private Text register;
    @FXML
    private Text errorText;

    public ViewConnectPlayer(BrickBreakerController brickBreakerController){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Connect.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        this.brickBreakerController = brickBreakerController;
        this.gameMenu = new GameMenuController(brickBreakerController);
        Font cFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),7);
        Font btFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),6);
        Font tFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),12);
        title.setFont(tFont);
        lText.setFont(cFont);
        pText.setFont(cFont);
        quitText.setFont(btFont);
        connectBtText.setFont(btFont);
        register.setFont(cFont);
        errorText.setFont(cFont);
    }

    @FXML
    private void clickConnect() {
        AuthPlayer p = null;
        String loginR = this.login.getText();
        String pass = this.password.getText();
        if (loginR == null || loginR.isEmpty() || pass == null || pass.isEmpty()) {
             errorText.setText("Veuillez remplir tous les champs");
            errorText.setVisible(true);
        } else {
            p = PlayerManager.getInstance().getPlayer(loginR);
            if (p == null) {
                 errorText.setText("Le joueur n'existe pas");
                errorText.setVisible(true);
            } else {
                try {
                    if (Security.checkPassword(pass, p.getSalt(), p.getHashedPassword())) {
                        Session.getInstance().connect(p.getLogin());
                        gameMenu.showBtStartMenuConnected();
                        brickBreakerController.getChildren().remove(this);
                    } else {
                         errorText.setText("Le mot de passe est incorrect");
                        errorText.setVisible(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void clickRegister(){
        brickBreakerController.getChildren().remove(this);
        gameMenu.registerPlayer();
    }
    @FXML
    private void clickExit(){
        gameMenu.showBtStartMenuDisconnected();
        brickBreakerController.getChildren().remove(this);
    }


}
