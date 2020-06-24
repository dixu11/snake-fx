package snake.view;

import snake.controller.BoardSizePickerController;
import snake.controller.MenuController;
import snake.game.game.Game;

public class ViewFactory {

    public void createGameStage(int rowCount) {
        Game game = new Game(rowCount);
    }

    public void createMainMenu() {
        Menu menu = new Menu();
        MenuController menuController = new MenuController(menu);
    }

    public void createBoardSizePicker() {
        BoardSizePicker boardSizePicker = new BoardSizePicker();
        BoardSizePickerController controller = new BoardSizePickerController(boardSizePicker);
    }

    public void createRankingStage(){
        Ranking ranking = new Ranking();
    }



}
