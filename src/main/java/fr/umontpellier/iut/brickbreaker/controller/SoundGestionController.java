package fr.umontpellier.iut.brickbreaker.controller;
import fr.umontpellier.iut.brickbreaker.BrickBreakerApplication;
import javafx.scene.media.*;

public class SoundGestionController {

    private AudioClip brickDestroySound;
    private AudioClip looseBallSound;
    private AudioClip bounceSound;
    private AudioClip gameOverSound;
    private BrickBreakerController brickBreakerController;
    public SoundGestionController(BrickBreakerController brickBreakerController){
         brickDestroySound = new AudioClip(BrickBreakerApplication.class.getResource("musique/brick.wav").toString());
        brickDestroySound.setVolume(0.1);

        looseBallSound = new AudioClip(BrickBreakerApplication.class.getResource("musique/sorti.wav").toString());
        looseBallSound.setVolume(0.1);

        bounceSound = new AudioClip(BrickBreakerApplication.class.getResource("musique/rebond.wav").toString());
        bounceSound.setVolume(0.1);

        gameOverSound = new AudioClip(BrickBreakerApplication.class.getResource("musique/GameOver.wav").toString());
        gameOverSound.setVolume(0.1);

        this.brickBreakerController=brickBreakerController;

    }

    public void brickDestroyed(){
        if(brickBreakerController.getMusicState()){
            brickDestroySound.play();
        }

    }

    public void loosedBall(){
        if(brickBreakerController.getMusicState()){
            looseBallSound.play();
        }

    }

    public void bounceBall(){
        if(brickBreakerController.getMusicState()){
            bounceSound.play();
        }
    }

    public void gameOver(){
        if(brickBreakerController.getMusicState()){
            gameOverSound.play();
        }
    }


}
