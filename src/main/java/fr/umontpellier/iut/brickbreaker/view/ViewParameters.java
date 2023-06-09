package fr.umontpellier.iut.brickbreaker.view;

import fr.umontpellier.iut.brickbreaker.BrickBreakerApplication;
import fr.umontpellier.iut.brickbreaker.controller.BrickBreakerController;
import fr.umontpellier.iut.brickbreaker.controller.GameMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class ViewParameters extends Pane {


    @FXML
    private Text backTxt;
    @FXML
    private Text audioTxt;
    @FXML
    private Text mouseTxt;
    @FXML
    private Text title;
    @FXML
    private CheckBox checkMouse;
    @FXML
    private ImageView checkAudio;



    private BrickBreakerController brickBreakerController;
    private GameMenuController gameMenu;
    private Font cFont;
    private Font titleFont;

    public ViewParameters(BrickBreakerController brickBreakerController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Parameters.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        this.brickBreakerController=brickBreakerController;
        gameMenu=new GameMenuController(brickBreakerController);
        cFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),8);
        audioTxt.setFont(cFont);
        mouseTxt.setFont(cFont);
        backTxt.setFont(cFont);

        titleFont = Font.loadFont(BrickBreakerApplication.class.getResource("font/m42.TTF").toString(),12);
        title.setFont(titleFont);

        checkInitImages();
    }


    private void checkInitImages(){
        if(brickBreakerController.getMusicState()==true){
            checkAudio.setImage(new Image(BrickBreakerApplication.class.getResource("textures/musikOn.png").toString()));
        }
        else if(brickBreakerController.getMusicState()==false){
            checkAudio.setImage(new Image(BrickBreakerApplication.class.getResource("textures/musikOff.png").toString()));
        }
        else{
            checkAudio.setImage(new Image(BrickBreakerApplication.class.getResource("textures/musikOn.png").toString()));
        }

        if(brickBreakerController.getSelectedMouse()){
            checkMouse.setSelected(true);
        }
        else if(brickBreakerController.getSelectedMouse()==false){
            checkMouse.setSelected(false);
        }
        else{
            checkMouse.setSelected(false);
        }
    }

    @FXML
    private void clickBack(MouseEvent event){
        brickBreakerController.getChildren().remove(this);
        gameMenu.pauseMenu();
    }
    @FXML
    private void clickAudio(MouseEvent event){
        if(brickBreakerController.getMusicState()==false){
            brickBreakerController.setMusicState(true);
            checkAudio.setImage(new Image(BrickBreakerApplication.class.getResource("textures/musikOn.png").toString()));
        }
        else if(brickBreakerController.getMusicState()==true){
            brickBreakerController.setMusicState(false);
            checkAudio.setImage(new Image(BrickBreakerApplication.class.getResource("textures/musikOff.png").toString()));
        }
    }
    @FXML
    private void clickMouse(ActionEvent actionEvent){
        if(brickBreakerController.getSelectedMouse()==false){
            brickBreakerController.setSelectedMouse(true);
        }
        else if(brickBreakerController.getSelectedMouse()){
            brickBreakerController.setSelectedMouse(false);
        }
        else{
            brickBreakerController.setSelectedMouse(false);
        }

    }
}
