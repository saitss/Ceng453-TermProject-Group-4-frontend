package com.client.pane.game;

import com.client.controller.observer.IObserverText;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Guı for showing active player.
 * It updates Iplayer object. Effect is directly visible from the gui
 */
public class PlayerTurn extends VBox implements IObserverText {

    Label activeUser;

    /**
     * Adjust player parameters
     * @param player current player
     */
    public PlayerTurn(IPlayer player){
        GameManager.getInstance().addUserInformation(this); // set this to gameManager

        setPadding(new Insets(20,50,5,15));
        Label currentUser= new Label("Current User");
        currentUser.setFont(new Font("Arial" , 24));
        activeUser = new Label(player.getName());
        activeUser.setFont(new Font("Arial" , 24));
        getChildren().add(currentUser);
        getChildren().add(activeUser);
        setStyle("-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 5;\n" +
                "-fx-border-style: dashed;\n");

    }


    /**
     * Player purchased the space so update the gui.
     * @param player current player
     */

    @Override
    public void updateOwner(IPlayer player) {

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i <1; i++){

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    final int d = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            activeUser.setText(player.getName()); // change the current user value


                        }
                    });
                }


            }
        });

        taskThread.start();
    }
}
