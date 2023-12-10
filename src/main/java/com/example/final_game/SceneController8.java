package com.example.final_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController8{
    public SceneController8(ImageView imagebg) {
        this.imagebg = imagebg;
    }

    public Button getClickPlay() {
        return clickPlay;
    }

    public void setClickPlay(Button clickPlay) {
        this.clickPlay = clickPlay;
    }

    @FXML
    private Button clickPlay;

    public Button getClickTutorial() {
        return clickTutorial;
    }

    public void setClickTutorial(Button clickTutorial) {
        this.clickTutorial = clickTutorial;
    }

    @FXML
    private Button clickTutorial;

    public Button getClickSlash() {
        return clickSlash;
    }

    public void setClickSlash(Button clickSlash) {
        this.clickSlash = clickSlash;
    }

    @FXML
    private Button clickSlash;

    public Button getClickVolume() {
        return clickVolume;
    }

    public void setClickVolume(Button clickVolume) {
        this.clickVolume = clickVolume;
    }

    @FXML
    private Button clickVolume;

    public Button getClickNoAds() {
        return clickNoAds;
    }

    public void setClickNoAds(Button clickNoAds) {
        this.clickNoAds = clickNoAds;
    }

    @FXML
    private Button clickNoAds;

    public Button getClickHeroes() {
        return clickHeroes;
    }

    public void setClickHeroes(Button clickHeroes) {
        this.clickHeroes = clickHeroes;
    }

    @FXML
    private Button clickHeroes;

    public Button getClickShop() {
        return clickShop;
    }

    public void setClickShop(Button clickShop) {
        this.clickShop = clickShop;
    }

    @FXML
    private Button clickShop;
    @FXML
    private ImageView imagebg;
    @FXML
    private Rectangle pillar;
    @FXML
    private Rectangle pillar1;
    @FXML
    private Rectangle pillar2;
    @FXML
    private Rectangle pillar3;
    @FXML
    private Rectangle pillar4;
    @FXML
    private Rectangle pillar5;
    @FXML
    private Rectangle pillar6;

    public Rectangle getPillar() {
        return pillar;
    }

    public void setPillar(Rectangle pillar) {
        this.pillar = pillar;
    }

    public Rectangle getPillar1() {
        return pillar1;
    }

    public void setPillar1(Rectangle pillar1) {
        this.pillar1 = pillar1;
    }

    public Rectangle getPillar2() {
        return pillar2;
    }

    public void setPillar2(Rectangle pillar2) {
        this.pillar2 = pillar2;
    }

    public Rectangle getPillar3() {
        return pillar3;
    }

    public void setPillar3(Rectangle pillar3) {
        this.pillar3 = pillar3;
    }

    public Rectangle getPillar4() {
        return pillar4;
    }

    public void setPillar4(Rectangle pillar4) {
        this.pillar4 = pillar4;
    }

    public Rectangle getPillar5() {
        return pillar5;
    }

    public void setPillar5(Rectangle pillar5) {
        this.pillar5 = pillar5;
    }

    public Rectangle getPillar6() {
        return pillar6;
    }

    public void setPillar6(Rectangle pillar6) {
        this.pillar6 = pillar6;
    }

    @FXML
    public void initialize() {
        clickPlay.setOnAction(event -> openScene("scene2.fxml"));
        clickTutorial.setOnAction(event -> openScene("scene7.fxml"));
        clickSlash.setOnAction(event -> openScene("scene2.fxml"));
        clickVolume.setOnAction(event -> openScene("scene8.fxml"));
        clickHeroes.setOnAction(event -> openScene("scene4.fxml"));
        clickShop.setOnAction(event -> openScene("scene5.fxml"));


        Image image = new Image("@../../../images/hillbg.jpeg");
        imagebg.setImage(image);
        Image image2 = new Image("@../../../images/mute.jpeg");
        imagebg.setImage(image);

        Rectangle pillar = new Rectangle(5, 5);
        Rectangle pillar1 = new Rectangle(5, 5);
        Rectangle pillar2 = new Rectangle(5, 5);
        Rectangle pillar3 = new Rectangle(5, 5);
        Rectangle pillar4 = new Rectangle(5, 5);
        Rectangle pillar5 = new Rectangle(5, 5);
        Rectangle pillar6 = new Rectangle(5, 5);
    }

    public void openScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) clickPlay.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openScene2(ActionEvent event) throws IOException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        Scene scene = new Scene(scene2);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void openScene7(ActionEvent event) throws IOException {
        Parent scene6 = FXMLLoader.load(getClass().getResource("scene6.fxml"));
        Scene scene = new Scene(scene6);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void openScene8(ActionEvent event) throws IOException {
        Parent scene8 = FXMLLoader.load(getClass().getResource("scene8.fxml"));
        Scene scene = new Scene(scene8);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void openScene4(ActionEvent event) throws IOException {
        Parent scene8 = FXMLLoader.load(getClass().getResource("scene7.fxml"));
        Scene scene = new Scene(scene8);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void openScene5(ActionEvent event) throws IOException {
        Parent scene8 = FXMLLoader.load(getClass().getResource("scene7.fxml"));
        Scene scene = new Scene(scene8);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}





