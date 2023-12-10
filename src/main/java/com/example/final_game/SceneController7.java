package com.example.final_game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class SceneController7 {

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle2;

    @FXML
    private Rectangle rectangle3;

    @FXML
    private Rectangle rectangle4;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private Label label;

    @FXML
    public void initialize() {

        Image image1 = new Image("@../../../images/watermelon Small.jpeg");
        Image image2 = new Image("@../../../images/istockphoto-1293449386-612x612.jpg");
        Image image3 = new Image("@../../../images/stickhero.jpg");
        Image image4 = new Image("@../../../images/cherry.jpeg");

        imageView1.setImage(image1);
        imageView2.setImage(image2);
        imageView3.setImage(image3);
        imageView4.setImage(image4);

        rectangle1.setWidth(19);
        rectangle1.setHeight(133);
        rectangle2.setWidth(30);
        rectangle2.setHeight(133);
        rectangle3.setWidth(6);
        rectangle3.setHeight(115);
        rectangle4.setWidth(529);
        rectangle4.setHeight(301);

        rectangle4.setStyle("-fx-color: white;");
        rectangle2.setStyle("-fx-color: black;");
        rectangle3.setStyle("-fx-color: black;");
        rectangle1.setStyle("-fx-color: black;");


        label.setText("HOW TO PLAY?");
    }

}

