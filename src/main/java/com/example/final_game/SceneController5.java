package com.example.final_game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneController5 {

    private ImageView imageView;

    public ImageView getImageView1() {
        return imageView1;
    }

    public void setImageView1(ImageView imageView1) {
        this.imageView1 = imageView1;
    }

    @FXML
    private ImageView imageView1;

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

    public ImageView getButton2ImageView1() {
        return button2ImageView1;
    }

    public void setButton2ImageView1(ImageView button2ImageView1) {
        this.button2ImageView1 = button2ImageView1;
    }

    @FXML
    private ImageView button2ImageView1;

    public ImageView getButton2ImageView2() {
        return button2ImageView2;
    }

    public void setButton2ImageView2(ImageView button2ImageView2) {
        this.button2ImageView2 = button2ImageView2;
    }

    @FXML
    private ImageView button2ImageView2;

    public ImageView getButton3ImageView1() {
        return button3ImageView1;
    }

    public void setButton3ImageView1(ImageView button3ImageView1) {
        this.button3ImageView1 = button3ImageView1;
    }

    @FXML
    private ImageView button3ImageView1;

    public ImageView getButton3ImageView2() {
        return button3ImageView2;
    }

    public void setButton3ImageView2(ImageView button3ImageView2) {
        this.button3ImageView2 = button3ImageView2;
    }

    @FXML
    private ImageView button3ImageView2;

    public ImageView getButton3ImageView3() {
        return button3ImageView3;
    }

    public void setButton3ImageView3(ImageView button3ImageView3) {
        this.button3ImageView3 = button3ImageView3;
    }

    @FXML
    private ImageView button3ImageView3;

    public SceneController5(ImageView imageView) {
        this.imageView = imageView;
    }

    @FXML
    public void initialize() {

        Image image = new Image("@../../../images/hillbg5.jpeg");
        Image image1 = new Image("@../../../images/cherry.jpeg");
        Image image2 = new Image("@../../../images/cherry.jpeg");
        Image image3 = new Image("@../../../images/cherry.jpeg");

        imageView.setImage(image);
        imageView1.setImage(image1);
        button2ImageView1.setImage(image2);
        button2ImageView2.setImage(image3);
        button3ImageView1.setImage(image1);
        button3ImageView2.setImage(image2);
        button3ImageView3.setImage(image3);

        // Set text for label
        label.setText("STICK HERO");

        // Set text for TitledPane
        titledPane.setText("SHOP");

        // Set text for buttons
        button1.setText("                     +400           Rs. 99");
        button2.setText("                     +1000         Rs. 199");
        button3.setText("                     +5000        Rs. 499");
    }

}

