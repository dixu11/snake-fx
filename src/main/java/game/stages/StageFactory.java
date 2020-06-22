package game.stages;

import javafx.stage.Stage;
import game.snake.logic.Game;

public class StageFactory {

    public void createGameStage(int rowCount) {
        Game game = new Game(rowCount);
    }

    public void createMainMenu() {
        Menu menu = new Menu();
    }

    public void createBoardSizePicker() {
        BoardSizePicker boardSizePicker = new BoardSizePicker();
    }

    public void createRankingStage(){
        Ranking ranking = new Ranking();
    }



}
