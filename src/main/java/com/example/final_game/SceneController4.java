package com.example.final_game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneController4 {

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @FXML
    private ImageView imageView;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @FXML
    private Label label;

    public TitledPane getTitledPane() {
        return titledPane;
    }

    public void setTitledPane(TitledPane titledPane) {
        this.titledPane = titledPane;
    }

    @FXML
    private TitledPane titledPane;

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    @FXML
    private Button button1;

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }

    @FXML
    private Button button2;

    public Button getButton3() {
        return button3;
    }

    public void setButton3(Button button3) {
        this.button3 = button3;
    }

    @FXML
    private Button button3;

    public Button getButton4() {
        return button4;
    }

    public void setButton4(Button button4) {
        this.button4 = button4;
    }

    @FXML
    private Button button4;

    public Button getButton5() {
        return button5;
    }

    public void setButton5(Button button5) {
        this.button5 = button5;
    }

    @FXML
    private Button button5;

    public Button getButton6() {
        return button6;
    }

    public void setButton6(Button button6) {
        this.button6 = button6;
    }

    @FXML
    private Button button6;

    public ImageView getButton1ImageView() {
        return button1ImageView;
    }

    public void setButton1ImageView(ImageView button1ImageView) {
        this.button1ImageView = button1ImageView;
    }

    @FXML
    private ImageView button1ImageView;

    public ImageView getButton2ImageView() {
        return button2ImageView;
    }

    public void setButton2ImageView(ImageView button2ImageView) {
        this.button2ImageView = button2ImageView;
    }

    @FXML
    private ImageView button2ImageView;

    public ImageView getButton3ImageView() {
        return button3ImageView;
    }

    public void setButton3ImageView(ImageView button3ImageView) {
        this.button3ImageView = button3ImageView;
    }

    @FXML
    private ImageView button3ImageView;

    public ImageView getButton4ImageView() {
        return button4ImageView;
    }

    public void setButton4ImageView(ImageView button4ImageView) {
        this.button4ImageView = button4ImageView;
    }

    @FXML
    private ImageView button4ImageView;

    public ImageView getButton5ImageView() {
        return button5ImageView;
    }

    public void setButton5ImageView(ImageView button5ImageView) {
        this.button5ImageView = button5ImageView;
    }

    @FXML
    private ImageView button5ImageView;

    public ImageView getButton6ImageView() {
        return button6ImageView;
    }

    public void setButton6ImageView(ImageView button6ImageView) {
        this.button6ImageView = button6ImageView;
    }

    @FXML
    private ImageView button6ImageView;

    @FXML
    public void initialize() {

        Image image1 = new Image("@../../../images/hillg4.jpeg");
        Image image2 = new Image("@../../../images/cherry.jpeg");
        Image image3 = new Image("@../../../images/cherry.jpeg");
        Image image4 = new Image("@../../../images/cherry.jpeg");
        Image image5 = new Image("@../../../images/cherry.jpeg");
        Image image6 = new Image("@../../../images/cherry.jpeg");
        Image image7 = new Image("@../../../images/cherry.jpeg");


        imageView.setImage(image1);
        button1ImageView.setImage(image2);
        button2ImageView.setImage(image3);
        button3ImageView.setImage(image4);
        button4ImageView.setImage(image5);
        button5ImageView.setImage(image6);
        button6ImageView.setImage(image7);


        label.setText("Hello, this is SceneController4!");


        titledPane.setText("HEROES");

        button1.setText("Button 1");
        button2.setText("        25");
        button3.setText("      75");
        button4.setText("        150");
        button5.setText("       250");
        button6.setText("         300");
    }


}

