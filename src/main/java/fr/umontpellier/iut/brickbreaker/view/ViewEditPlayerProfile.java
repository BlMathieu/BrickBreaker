package fr.umontpellier.iut.brickbreaker.view;

import fr.umontpellier.iut.brickbreaker.BrickBreakerApplication;
import fr.umontpellier.iut.brickbreaker.controller.BrickBreakerController;
import fr.umontpellier.iut.brickbreaker.controller.GameMenuController;
import fr.umontpellier.iut.brickbreaker.metier.entite.AuthPlayer;
import fr.umontpellier.iut.brickbreaker.metier.manager.PlayerManager;
import fr.umontpellier.iut.brickbreaker.stockage.Security;
import fr.umontpellier.iut.brickbreaker.stockage.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;

public class ViewEditPlayerProfile extends Pane {

    @FXML
    private Text backText;
    @FXML
    private Text depText;
    @FXML
    private Text oldText;
    @FXML
    private Text newText;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Text confirmText;
    @FXML
    private ComboBox depComboBox;
    @FXML
    private Text errorText;
    private AuthPlayer player;

    private BrickBreakerController brickBreakerController;
    private GameMenuController gameMenuController;
    public ViewEditPlayerProfile(BrickBreakerController brickBreakerController){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPlayerProfile.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        this.brickBreakerController=brickBreakerController;
        gameMenuController = new GameMenuController(brickBreakerController);
        Font cFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),7);

        newText.setFont(cFont);
        oldText.setFont(cFont);
        backText.setFont(cFont);
        depText.setFont(cFont);
        confirmText.setFont(cFont);
        errorText.setFont(cFont);


        Map<String, String> departments = PlayerManager.getInstance().getDepartments();
        for (String key : departments.keySet()) {
            String field = key + " - " + departments.get(key);
            depComboBox.getItems().add(field);
        }
        player = PlayerManager.getInstance().getPlayer(Session.getInstance().getLogin());
        if(player.getDepartment() != null) depComboBox.setValue(player.getDepartment());
    }

    @FXML
    private void clickBack(){
        brickBreakerController.getChildren().remove(this);
        gameMenuController.playerProfile();
    }
    @FXML
    private void clickConfirm(){

        String oP = oldPassword.getText();
        String nP = newPassword.getText();
        String dep;
        String login = Session.getInstance().getLogin();
        try {
            dep = depComboBox.getValue().toString().split(" ")[0];
        }
        catch (NullPointerException e){
            dep = "";
        }
        try {
            if (login == null || oP == null || nP == null || dep == null || login.equals("") || oP.equals("") || nP.equals("") || dep.equals("")) {
                errorText.setText("Veuillez remplir tous les champs");
                errorText.setVisible(true);
            } else if (!Security.checkPassword(oP, player.getSalt(), player.getHashedPassword())) {
                errorText.setText("L'ancien mot de passe est incorrect");
                errorText.setVisible(true);
                oldPassword.clear();
            } else {
                PlayerManager.getInstance().updatePlayer(login, dep, nP);
                brickBreakerController.getChildren().remove(this);
                gameMenuController.showBtStartMenuConnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
