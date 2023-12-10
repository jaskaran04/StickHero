package com.example.final_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        //fxmlLoader.setController(new SceneController1("com.example.final_game.hello-view.fxml"));
        Pane mainPane = fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static class MultithreadedJavaFX extends Application {

        @Override
        public void start(Stage primaryStage) {
            Button btn = new Button("Click me!");
            btn.setOnAction(e -> {
                new Thread(() -> {
                    String result = performBackgroundWork();
                    Platform.runLater(() -> {
                        btn.setText(result);
                    });
                }).start();
            });

            StackPane root = new StackPane();
            root.getChildren().add(btn);

            Scene scene = new Scene(root, 300, 250);

            primaryStage.setTitle("Multithreaded JavaFX");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private String performBackgroundWork() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Work Done!";
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}