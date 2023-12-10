package com.example.final_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController3 {
    private ImageView imageview;

    public SceneController3(ImageView imageview) {
        this.imageview = imageview;
    }


    private Rectangle r5;
    private Rectangle r6;
    private Rectangle r7;
    public Button getGoToHome() {
        return goToHome;
    }

    public void setGoToHome(Button goToHome) {
        this.goToHome = goToHome;
    }

    @FXML
    private Button goToHome;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    private Region region;

    public Text getText1() {
        return text1;
    }

    public void setText1(Text text1) {
        this.text1 = text1;
    }

    @FXML
    private Text text1;

    public Text getText2() {
        return text2;
    }

    public void setText2(Text text2) {
        this.text2 = text2;
    }

    @FXML
    private Text text2;

    public Text getText3() {
        return text3;
    }

    public void setText3(Text text3) {
        this.text3 = text3;
    }

    @FXML
    private Text text3;

    @FXML
    private Text text4;

    public Text getText5() {
        return text5;
    }

    public void setText5(Text text5) {
        this.text5 = text5;
    }

    @FXML
    private Text text5;


    public Button getGoRetry() {
        return goRetry;
    }

    public void setGoRetry(Button goRetry) {
        this.goRetry = goRetry;
    }

    @FXML
    private Button goRetry;


    public void initialize() {

//        openScene("hello-view.fxml");
//        openScene2("scene2.fxml");


        goRetry.setOnAction(event -> {
            try {
                openScene("hello-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        goToHome.setOnAction(event -> {
            try {
                openScene2("scene2.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Image image = new Image("@../../../images/hillbg3.jpeg");
        imageview.setImage(image);

        Rectangle r5 = new Rectangle(5, 5);
        Rectangle r6 = new Rectangle(5, 5);
        Rectangle r7 = new Rectangle(5, 5);

    }

    @FXML
    public void openScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) goToHome.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openScene2(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) goRetry.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public Text getText4() {
        return text4;
    }

    public void setText4(Text text4) {
        this.text4 = text4;
    }

//    public ImageView getImageView() {
//        return imageView;
//    }
//
//    public void setImageView(ImageView imageView) {
//        this.imageView = imageView;
//    }
}
