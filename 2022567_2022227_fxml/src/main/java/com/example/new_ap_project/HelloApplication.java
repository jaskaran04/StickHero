package com.example.new_ap_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
//        class Platform{
//    //    Body
//
//            public Platform(){
//    //        Body
//            }
//        }
//
//        enum GameStatus{
//            ONGOING,
//            GAME_OVER,
//            LEVEL_COMPLETE
//        }
//
//        class StickHero{
//    //    Body
//
//            public StickHero(){
//    //        Body
//            }
//
//            void stretchstick(){
//    //        Body
//            }
//
//            void nextPlatform(){
//    //        Body
//            }
//
//            void FlipAndCollectRewards(){
//    //        Body
//            }
//
//            void handlerewardcollection(){
//    //        Body
//            }
//
//            void SaveGame(){
//    //        Body
//            }
//
//            void LoadGame(){
//    //        Body
//            }
//
//            void LevelComplete(){
//    //        Body
//            }
//
//            GameStatus getGameStatus(){
//    //        Body
//                return null; //Initially a null statement has been returned which would be later modified while implementing the code, this method is just to draw an outline of the game status of the player.
//            }
//
//            void ReviveUsingCherries(){
//    //        Body
//            }
//
//            void end(){
//    //        Body
//            }
//        }
//
//        public static class StickHeroGame {
//            public static void main(String[] args) {
//    //        Body
//            }
//        }
}