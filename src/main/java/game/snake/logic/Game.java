package game.snake.logic;

import game.stages.StageFactory;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import game.snake.model.Counter;
import game.snake.model.Food;
import game.snake.model.Player;
import game.snake.model.Segment;

import java.util.ArrayList;
import java.util.List;

public class Game extends Stage {

    public static final int FIELD_SIZE = 50;
    public static  int rowCount = 8;
    public static  int boardSize;

    //helper objects
    private FoodFactory foodFactory;

    //window
    private Pane root = new Pane();
    private Scene scene = new Scene(root);

    //game elements
    private Segment snake;
    private List<Food> foodList = new ArrayList<>();
    private Player player = new Player();
    private Counter counter;
    private AnimationTimer timer;

    private long moves = 0;

    private boolean[] keys = new boolean[500];


    public Game(int rowCount) {
        Game.rowCount = rowCount;
        Game.boardSize = rowCount * FIELD_SIZE;

        initStage();
        initHelperObjects();
        initComponents();
        startTimer();
        initKeysUpdater();
    }

    private void initStage() {
        setScene(scene);
        setWidth(boardSize + 15);
        setHeight(boardSize + 40);
        setTitle("Snake");
        show();
    }

    private void initHelperObjects() {
        foodFactory = new FoodFactory(this);
    }

    private void initComponents() {
        snake = new Segment(0, 0, Color.BROWN);
        root.getChildren().add(snake);
        counter = new Counter(player);
        root.getChildren().add(counter);
    }


    private void startTimer() {
        timer = new AnimationTimer() {
            long lastTime;

            @Override
            public void handle(long now) {
                if (now - lastTime > 500_000_000) {
                    lastTime = now;
                    updateGame();
                }
            }

        };
        timer.start();
    }

    private void initKeysUpdater() { //jak działa uaktualnianie przycisków
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                snake.updateDirection(event.getCode());
                keys[event.getCode().ordinal()] = true;
                checkBackToMenuShortcut();
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keys[event.getCode().ordinal()] = false;
            }
        });
    }

    private void checkBackToMenuShortcut() {
        int ctrlCode = KeyCode.CONTROL.ordinal();
        int altCode = KeyCode.ALT.ordinal();
        int qCode = KeyCode.Q.ordinal();
        if (keys[ctrlCode] && keys[altCode] && keys[qCode]) {
            timer.stop();
            close();
            StageFactory stageFactory = new StageFactory();
            stageFactory.createMainMenu();
        }
    }



    private void updateGame() {
        snake.move();
        if ( snake.hasCollisionWithTail() || snake.hasCollisionWithWall()) {
            System.out.println("End!");
            endGame();
            return;
        }
        boolean foodEaten = snake.eat(this);
        if (foodEaten) {
            player.addPoint();
        }
        moves++;
        foodFactory.spawnFood();
        counter.update();
    }

    public Pane getRoot() {
        return root;
    }

    public Segment getSnake() {
        return snake;
    }

    public long getMoves() {
        return moves;
    }

    public void addFood(Food food) {
        root.getChildren().add(food);
        foodList.add(food);
    }

    public Food findFood(int cordX, int cordY) {
        for (Food food : foodList) {
            if (food.isOnField(cordX, cordY)) {
                return food;
            }
        }
        return null; //TODO -> można zastować Optional lub wyjatki
    }

    public void removeFood(Food food) {
        root.getChildren().remove(food);
        foodList.remove(food);
    }

    public boolean isFoodOnField(int x, int y) {
        return findFood(x,y) != null;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    private void endGame() {
        timer.stop();
        close();
        TextInputDialog window = new TextInputDialog();
        window.setTitle("Game over!");
        window.setHeaderText("Game ended! You got: " + player.getPoints() + " points!");
        window.setContentText("Type your name:");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                window.show();
            }
        });

        window.setOnHidden(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
               player.setName(window.resultProperty().get());
               player.setFinalScore(moves/2.0); //1 move is 0.5 sec
                savePlayer();
                StageFactory stageFactory = new StageFactory();
                stageFactory.createMainMenu();
            }
        });
    }

    private void savePlayer() {
        RankingManager rankingManager = new RankingManager();
        rankingManager.savePlayer(player);
    }
}
