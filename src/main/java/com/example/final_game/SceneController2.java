package com.example.final_game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController2 {
    public SceneController2(ImageView imagebg) {
        this.imagebg2 = imagebg;
    }
    @FXML
    private Rectangle r1;

    @FXML
    private Rectangle r2;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Text text1;

    @FXML
    private ImageView imagebg2;

    //@FXML
    //private ImageView imagebg3;

    public void initialize() {

//        Image image = new Image("@../../../images/hillbg2.jpeg");
//        imagebg2.setImage(image);
//        image = new Image("@../../../images/stickhero-removebg-preview.png");
//        imagebg2.setImage(image);
//        clickPlay.setOnAction(event -> openScene("scene2.fxml"));
//
//        // Initialize rectangles (adjust the dimensions as needed)
//        Rectangle r1 = new Rectangle(5, 5);
//        Rectangle r2 = new Rectangle(5, 5);
    }

    public void openScene2(String fxmlFile) {
//        try {
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scene2.fxml")));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) clickPlay.getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public Rectangle getR1() {
        return r1;
    }

    public void setR1(Rectangle r1) {
        this.r1 = r1;
    }

    public Rectangle getR2() {
        return r2;
    }

    public void setR2(Rectangle r2) {
        this.r2 = r2;
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label label1) {
        this.label1 = label1;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label label2) {
        this.label2 = label2;
    }

    public Text getText1() {
        return text1;
    }

    public void setText1(Text text1) {
        this.text1 = text1;
    }
}
