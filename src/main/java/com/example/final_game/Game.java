package com.example.final_game;

import javafx.scene.layout.BorderPane;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.shape.Rectangle;

class Controller implements EventHandler<MouseEvent> {
    public static final int FPS = 60;
    private Engine engine;
    private Panel panel;
    private boolean increasing;
    private boolean running;
    private boolean upside_down;
    private MouseEventHandler mouseEventHandler;

    public void initialize(Engine engine, Panel panel) {
        this.engine = engine;
        this.panel = panel;
        mouseEventHandler = new MouseEventHandler(this);
        panel.setOnMouseClicked(mouseEventHandler);
        panel.setOnMousePressed(mouseEventHandler);
        panel.setOnMouseReleased(mouseEventHandler);
    }

    public void actionPerformed(ActionEvent actionEvent){
        if (actionEvent == null){
            panel.goToGame();
            engine.initialize();
            engine.startGameLoop();
        }
    }

    public void start() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                panel.requestLayout();
            }
        };

        gameLoop.start();
    }

    public void nextPillar(boolean isCherryEaten) {
        if (isCherryEaten)
            engine.CherryConsumed();
        engine.nextPillar();
    }

    public void gameOver() {
        panel.gameOver();
    }

    public void replay() {
        engine.initialize();
        panel.replay(engine, this);
    }

    public boolean isUpsideDown() {
        return upside_down;
    }

    @Override
    public void handle(MouseEvent e) {
        upside_down = !upside_down;
        panel.goToGame();
    }

    public void handleMousePressed(MouseEvent event) {
        if (engine.getMoving()) {
            return;
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                engine.increaseStickLength();
            }
        }.start();
    }

    public void handleMouseReleased(MouseEvent event) {
        increasing = false;
        engine.setMoving(true);
        engine.checkForGameOver();
    }

    public void handleAction(ActionEvent e) {
        panel.goToGame();
    }
}

class MouseEventHandler implements EventHandler<MouseEvent>{
    private Controller controller;
    public MouseEventHandler (Controller controller){
        this.controller = controller;
    }
    @Override
    public void handle(MouseEvent event){
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED){
            controller.handle(event);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            controller.handleMousePressed(event);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            controller.handleMouseReleased(event);
        }
    }
}
abstract class GameOverPanelTemplate extends Pane {
    protected abstract void drawContent(Engine engine);

    public void draw(Engine engine) {
        ImageView background = new ImageView(new Image("C:\\Users\\vibhuti22567\\Downloads\\R.jfif"));
        background.setX(-450);
        getChildren().add(background);

        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Trattatello", 85));
        gameOverText.setFill(Color.BLACK);
        gameOverText.setX(35);
        gameOverText.setY(120);
        getChildren().add(gameOverText);

        drawContent(engine);
    }
}

class GameOver_Panel extends GameOverPanelTemplate {
    private Engine engine;

    public GameOver_Panel(Engine engine, Controller controller) {
        this.engine = engine;

        Button replayButton = new Button();
        replayButton.setGraphic(new ImageView(new Image("C:\\Users\\vibhuti22567\\Downloads\\R.jfif")));
        replayButton.setOnAction(event -> controller.replay());
        replayButton.setLayoutX(Screens.background_width / 2 - 180);
        replayButton.setLayoutY(Screens.background_height / 2 + 100);
        replayButton.setPrefSize(360, 120);
        getChildren().add(replayButton);

        setPrefSize(Screens.background_width, Screens.background_height);
        draw(engine);
    }

    @Override
    protected void drawContent(Engine engine) {
        Text scoreText = new Text("SCORE : " + engine.getCherriesCount());
        scoreText.setFont(Font.font("Trattatello", 60));
        scoreText.setFill(Color.BLACK);
        scoreText.setX(130);
        scoreText.setY(250);
        getChildren().add(scoreText);

        Text cherrytxt = new Text("Cherries : " + engine.getCherriesNumber());
        cherrytxt.setFont(Font.font("Trattatello", 60));
        cherrytxt.setFill(Color.BLACK);
        cherrytxt.setX(40);
        cherrytxt.setY(330);
        getChildren().add(cherrytxt);
    }
}

class Engine {
    public static final int MIN_DISTANCE = 100;
    public static final int MAX_DISTANCE = 250;
    private static final int cherry_height = 20;
    private static final int cherry_width = 15;
    private String name;
    private boolean game_over;
    private boolean movement;
    private Pillar first, second;
    private int distance;
    private int stick_length;
    private int cherries_count;

    private int cherries_num;
    private int cherries_pos;

    private Timeline game_loop;

    public Engine(String name) {
        this.name = name;
        initializeGameLoop();
    }

    public void initializeGameLoop() {
        game_loop = new Timeline(new KeyFrame(Duration.millis(1000 / Controller.FPS), event -> {
            if (isRunning()) {
                updateGameLogic();
            }
        }));
        game_loop.setCycleCount(Animation.INDEFINITE);
    }

    private void updateGameLogic() {
        increaseStickLength();

        checkForGameOver();

        checkForCherryCollision();
    }

    private void checkForCherryCollision() {
        int cherriesPosition = getCherriesPosition();
        int stickEndPosition = getStickLength();

        if (stickEndPosition >= cherriesPosition && stickEndPosition <= cherriesPosition + cherry_width) {
            CherryConsumed();
        }
    }

    public void initialize() {
        first = new Pillar();
        second = new Pillar();

        assignDistance();
        stick_length = 0;
        cherries_count = 0;
        cherries_num = 0;

        game_over = false;
        movement = false;
    }

    private void assignDistance() {
        Random rand = new Random();

        distance = 0;
        while (distance < Engine.MIN_DISTANCE)
            distance = rand.nextInt(Engine.MAX_DISTANCE);

        cherries_pos = 0;
        while (cherries_pos < 50) {
            cherries_pos = rand.nextInt(distance - 50);
        }
    }

    public void increaseStickLength() {
        stick_length += 3;
    }

    public void checkForGameOver() {
        if (stick_length < distance + 5 || distance + second.getWidth() + 5 < stick_length)
            game_over = true;
    }

    public void nextPillar() {
        first = second;
        second = new Pillar();
        assignDistance();

        movement = false;
        stick_length = 0;
        cherries_count++;
    }

    public boolean getGameOver() {
        return game_over;
    }

    public void setGameOver(boolean gameOver) {
        this.game_over = gameOver;
    }

    public boolean getMoving() {
        return movement;
    }

    public void setMoving(boolean movement) {
        this.movement = movement;
    }

    public Pillar getFirstPillar() {
        return first;
    }

    public Pillar getSecondPillar() {
        return second;
    }

    public int getDistance() {
        return distance;
    }

    public int getStickLength() {
        return stick_length;
    }

    public String getName() {
        return name;
    }

    public int getCherriesCount() {
        return cherries_count;
    }

    public int getCherriesPosition() {
        return cherries_pos;
    }

    public void CherryConsumed() {
        cherries_num++;
    }

    public int getCherriesNumber() {
        return cherries_num;
    }

    public Object startGameLoop() {
        game_loop.play();
        return null;
    }

    public void stopGameLoop() {
        game_loop.stop();
    }

    public boolean isRunning() {
        return game_loop.getStatus() == Animation.Status.RUNNING;
    }
}

class Panel extends BorderPane {
    private Play_Panel play_panel;
    private Start_Panel start_panel;
    private Name_Panel name_panel;
    private GameOver_Panel gameover_panel;
    public int panel_height = 500;
    public int panel_width = 600;
    private Engine engine;
    private Controller controller;

    public void initialize(Engine engine, Controller controller) {
        this.engine = engine;
        this.controller = controller;

        gameover_panel = new GameOver_Panel(engine, controller);
        name_panel = new Name_Panel(engine);
        start_panel = new Start_Panel(controller);
        play_panel = new Play_Panel(engine, controller);

        play_panel.setOnMouseClicked(controller::handle);
        play_panel.setOnMousePressed(controller::handleMousePressed);
        play_panel.setOnMouseReleased(controller::handleMouseReleased);

        setCenter(start_panel);
        setBottom(name_panel);
    }

    public void goToGame() {
        setCenter(play_panel);
    }

    public void gameOver() {
        setCenter(gameover_panel);
    }

    public void replay(Engine engine, Controller controller) {
        getChildren().clear();
        initialize(engine, controller);
    }

    public int getPanel_height(){
        return panel_height;
    }

    public void setPanel_height(int panel_height) {
        this.panel_height = panel_height;
    }

    public int getPanel_width() {
        return panel_width;
    }

    public void setPanel_width(int panel_width) {
        this.panel_width = panel_width;
    }
}

class Pillar {
    public static final int MIN_SIZE = 50;
    public static final int MAX_SIZE = 120;

    Panel panel = new Panel();

    private Rectangle pillar;

    public Pillar() {
        Random rand = new Random();

        int width = 0;
        while (width < Pillar.MIN_SIZE)
            width = rand.nextInt(Pillar.MAX_SIZE);

        pillar = new Rectangle(width, panel.panel_height);
    }

    public Rectangle getPillar() {
        return pillar;
    }

    public int getWidth() {
        return (int) pillar.getWidth();
    }
}

class Name_Panel extends HBox {
    private static final int BORDERS_LENGTH = 2;
    private static final int MAX_SIZE = 100;
    private static Name_Panel instance;

    private Label nameLabel;

    public Name_Panel(final Engine engine) {
        super();
        setPadding(new Insets(BORDERS_LENGTH));
        setAlignment(Pos.CENTER);

        nameLabel = new Label(engine.getName());
        nameLabel.setMaxSize(MAX_SIZE, MAX_SIZE);
        nameLabel.setFont(Font.font("Helvetica", 22));

        getChildren().add(nameLabel);
    }

    public static synchronized Name_Panel getInstance(Engine engine){
        if (instance == null){
            instance = new Name_Panel(engine);
        }
        return instance;
    }
}

class Screens {
    public static Image stickhero;
    public static Image start_button;
    public static Image hand;
    public static Image volume;
    public static Image cherries2;
    public static Image cherries3;
    public static Image watermelon;
    public static Image replay_button;
    public static Image cherries;

    public static final int background_width = 500;
    public static final int background_height = 600;
    public static final int hero_width = 30;
    public static final int hero_height = 60;

    static {

        try {

            stickhero = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\stickhero.png\""));
            start_button = new Image(new FileInputStream("images/StartButton.png"));
            hand = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\hand.png\""));
            volume = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\volumr.png\""));
            cherries2 = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\cherries2.jpg\""));
            cherries3 = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\cherries3.jpg\""));
            watermelon = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\watermelon.png\""));
            replay_button = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\replay.png\""));
            cherries = new Image(new FileInputStream("\"C:\\Users\\vibhuti22567\\Downloads\\cherries-Cluster.png\""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Play_Panel extends Pane {
    private static final int stick_width = 3;
    private static final int pillar_height = 220;
    private static final int pillar_start = 50;

    private Engine engine;
    private Controller controller;

    private int backgroundMoveValue = 0;

    private int firstWidth;
    private int secondWidth;
    private int secondPillarPosition;
    private int moveValue;

    private int rotateDegree;
    private int rotateSpeed;

    private int dest;
    private int heroX;
    private int heroY;
    private int imageCycle;
    private int cycleCnt;

    private boolean isCherryEaten;

    private Canvas canvas;
    private GraphicsContext gc;

    private Timeline timeline;

    public Play_Panel(Engine engine, Controller controller) {
        this.engine = engine;
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        canvas = new Canvas(Screens.background_width, Screens.background_height);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);

        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / Controller.FPS), event -> {
            update();
            render();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        isCherryEaten = false;
        reset();
        timeline.play();
    }

    private void reset() {
        backgroundMoveValue++;
        moveValue = 0;
        secondPillarPosition = 600;

        rotateDegree = 0;
        rotateSpeed = 1;

        firstWidth = engine.getFirstPillar().getWidth();
        secondWidth = engine.getSecondPillar().getWidth();

        heroX = pillar_start + firstWidth - 5 - Screens.hero_width;
        heroY = Screens.background_height - pillar_height - Screens.hero_height;

        imageCycle = 0;
        cycleCnt = 0;
        dest = 0;
    }

    private void update() {
        moveBackground();

        calcRectMove();

        calcDegree();

        calcDest();
        moveHero();

        checkForCherryEaten();

        checkForGameOver();
    }

    private void render() {
        gc.clearRect(0, 0, Screens.background_width, Screens.background_height);

        drawPillars();

        drawStick();

        drawHero();

        drawCherries();

        drawScore();
    }

    private void moveBackground() {
        if (rotateDegree == 90 && heroX == dest && backgroundMoveValue % 20 != 0 && !engine.getGameOver()) {
            backgroundMoveValue++;
        }

        gc.save();
        gc.translate(-backgroundMoveValue, 0);
        gc.drawImage(Screens.start_button, 0, 0);
        gc.restore();
    }

    private void drawPillars() {
        gc.save();
        gc.translate(-moveValue, 0);
        gc.fillRect(pillar_start, Screens.background_height - pillar_height, firstWidth, pillar_height);

        if (!engine.getMoving() && rotateDegree == 0 && secondPillarPosition != pillar_start + firstWidth + engine.getDistance()) {
            secondPillarPosition -= 20;
        }
        if (secondPillarPosition < pillar_start + firstWidth + engine.getDistance()) {
            secondPillarPosition = pillar_start + firstWidth + engine.getDistance();
        }

        gc.fillRect(secondPillarPosition,
                Screens.background_height - pillar_height, secondWidth, pillar_height);
        gc.restore();
    }

    private void drawStick() {
        if (engine.getMoving()) {
            gc.save();
            gc.rotate(Math.toRadians(rotateDegree));
            gc.fillRect(pillar_start + firstWidth - stick_width - 2, Screens.background_height - pillar_height - engine.getStickLength(),
                    stick_width, engine.getStickLength());
            gc.restore();
        }
    }

    private void calcRectMove() {
        if (moveValue >= engine.getDistance() + firstWidth) {
            controller.nextPillar(isCherryEaten);
            reset();
        }

        if (rotateDegree == 90 && heroX == dest && !engine.getGameOver()) {
            moveValue += 4;
        }
    }

    private void calcDegree() {
        if (!engine.getMoving()) {
            return;
        }

        if (rotateDegree < 90) {
            rotateDegree += rotateSpeed / 5;
            rotateSpeed++;
        } else {
            rotateDegree = 90;
        }
    }

    private void drawHero() {
        gc.save();
        gc.translate(heroX, heroY +   Screens.hero_height);
        if (engine.getMoving() && controller.isUpsideDown()) {
            gc.scale(1, -1);
        }
        if (rotateDegree == 90 && heroX < dest) {
            switch (imageCycle) {
                case 0:
                    gc.drawImage(Screens.stickhero, 0, -Screens.hero_height);
                    break;
                case 1:
                    gc.drawImage(Screens.stickhero, 0, -Screens.hero_height);
                    break;
                case 2:
                    gc.drawImage(Screens.stickhero, 0, -Screens.hero_height);
                    break;
                case 3:
                    gc.drawImage(Screens.stickhero, 0, -Screens.hero_height);
                    break;
            }

            cycleCnt++;
            cycleCnt %= 8;
            if (cycleCnt % 8 == 0) {
                imageCycle++;
                imageCycle %= 4;
            }

        } else {
            gc.drawImage(Screens.start_button, 0, -Screens.hero_height);
        }

        gc.restore();
    }

    private void calcDest() {
        if (engine.getGameOver() && controller.isUpsideDown()) {
            return;
        }

        if (engine.getGameOver()) {
            dest = pillar_start + firstWidth - Screens.hero_width + engine.getStickLength();
        } else {
            dest = pillar_start + firstWidth + engine.getDistance() + secondWidth - 5 - Screens.hero_width;
        }
    }

    private void moveHero() {
        if (rotateDegree == 90 && heroX < dest) {
            heroX += 2;
        }

        if (heroX > dest) {
            heroX = dest;
        }

        if (heroX == dest && engine.getGameOver()) {
            heroY += 20;
        }

        if (heroY > Screens.background_height) {
            controller.gameOver();
        }
    }

    private void drawCherries() {
        if (!isCherryEaten) {
            gc.drawImage(Screens.cherries, pillar_start + firstWidth + engine.getCherriesPosition(),
                    Screens.background_height - pillar_height + 5);
        }
    }

    private void checkForCherryEaten() {
        if (controller.isUpsideDown() && heroX + Screens.hero_width >= pillar_start + firstWidth + engine.getCherriesPosition()
                && heroX <= pillar_start + firstWidth + engine.getCherriesPosition() + 25) {
            isCherryEaten = true;
        }
    }

    private void checkForGameOver() {
        if (controller.isUpsideDown() && heroX + Screens.hero_width >= pillar_start + firstWidth + engine.getDistance()) {
            engine.setGameOver(true);
            dest = heroX;
        }
    }

    private void drawScore() {
        gc.setFont(Font.font("Trattatello", 40));
        gc.setFill(Color.BLACK);
        gc.fillText("Score : " + engine.getCherriesCount(), 30, 70);
        gc.fillText("Cherries : " + engine.getCherriesNumber(), 270, 70);
    }
}

class Start_Panel extends StackPane {
    private Controller controller;
    private Button button;

    public Start_Panel(Controller controller) {
        this.controller = controller;

        button = new Button();
        ImageView imageView = new ImageView(Screens.start_button);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        button.setGraphic(imageView);

        button.setOnAction(event -> controller.actionPerformed(null));

        getChildren().add(button);

        setPrefSize(Screens.background_width, Screens.background_height);
        createContent();
    }

    private void createContent() {
        Text title1 = new Text("STICK");
        title1.setFont(Font.font("Trattatello", 100));
        title1.setFill(Color.DARKGRAY);

        Text title2 = new Text("HERO");
        title2.setFont(Font.font("Trattatello", 100));
        title2.setFill(Color.DARKGRAY);

        getChildren().addAll(title1, title2);

        StackPane.setAlignment(title1, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(title1, new javafx.geometry.Insets(120, 0, 0, 0));

        StackPane.setAlignment(title2, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(title2, new javafx.geometry.Insets(200, 0, 0, 0));
    }
}

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Welcome!");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your name: ");

        Optional<String> result = dialog.showAndWait();
        String name = null;
        if (result.isPresent()) {
            name = result.get();

            System.out.println("Hello " + name + ",Welcome to the game!");

            Engine engine = new Engine("EngineX");
        }


        Engine engine = new Engine(name);
        Controller controller = new Controller();
        Panel panel = new Panel();

        engine.initialize();
        controller.initialize(engine, panel);
        panel.initialize(engine, controller);

        controller.start();

        primaryStage.setTitle("Stick Hero");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setScene(new Scene(panel, panel.getPanel_width(), panel.getPanel_height()));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
