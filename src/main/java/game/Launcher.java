package game;

import game.stages.StageFactory;
import javafx.application.Application;
import javafx.stage.Stage;


public class Launcher  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageFactory stageFactory = new StageFactory();
        stageFactory.createMainMenu();
    }
}
