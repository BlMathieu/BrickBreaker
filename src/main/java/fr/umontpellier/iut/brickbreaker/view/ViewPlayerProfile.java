package fr.umontpellier.iut.brickbreaker.view;

import fr.umontpellier.iut.brickbreaker.BrickBreakerApplication;
import fr.umontpellier.iut.brickbreaker.controller.BrickBreakerController;
import fr.umontpellier.iut.brickbreaker.controller.GameMenuController;
import fr.umontpellier.iut.brickbreaker.metier.entite.AuthPlayer;
import fr.umontpellier.iut.brickbreaker.metier.entite.Player;
import fr.umontpellier.iut.brickbreaker.metier.manager.PlayerManager;
import fr.umontpellier.iut.brickbreaker.stockage.Session;
import fr.umontpellier.iut.brickbreaker.stockage.sql.StockagePlayerDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.BindException;


public class ViewPlayerProfile extends Pane {

    @FXML
    private Text playerName;
    @FXML
    private Text backText;
    @FXML
    private Text title;
    @FXML
    private Text deleteText;
    @FXML
    private Text editText;
    @FXML
    private Text depText;
    private BrickBreakerController brickBreakerController;
    private GameMenuController gameMenuController;
    private AuthPlayer player;

    public ViewPlayerProfile(BrickBreakerController brickBreakerController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerProfile.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        this.brickBreakerController=brickBreakerController;
        this.gameMenuController=new GameMenuController(brickBreakerController);

        Font cFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),8);
        Font titleFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),12);

        backText.setFont(cFont);
        playerName.setFont(cFont);
        title.setFont(titleFont);
        editText.setFont(cFont);
        deleteText.setFont(cFont);
        depText.setFont(cFont);

        if(Session.getInstance().isConnected()){
            player = PlayerManager.getInstance().getPlayer(Session.getInstance().getLogin());
            playerName.setText("Nom du joueur : "+ player.getLogin());
            depText.setText("Departement : " + PlayerManager.getInstance().getDepartments().get(player.getDepartment()));
        }
    }

    @FXML
    private void clickBack(){
        gameMenuController.showBtStartMenuConnected();
        brickBreakerController.getChildren().remove(this);
    }

    @FXML
    private void clickDelete(){
        if (Session.getInstance().isConnected()){
            PlayerManager.getInstance().deletePlayer(Session.getInstance().getLogin());
            gameMenuController.showBtStartMenuDisconnected();
            brickBreakerController.getChildren().remove(this);
        }
    }
    @FXML
    private void clickEdit(){
        if (Session.getInstance().isConnected()){
            brickBreakerController.getChildren().remove(this);
            gameMenuController.editPlayerProfile();
        }
    }
}
