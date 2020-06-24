package snake.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import snake.view.Menu;
import snake.view.ViewFactory;

public class MenuController {

    private Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
       addButtonsActions();
    }

    public void addButtonsActions() {
        menu.addStartButtonAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startAction();
            }
        });

        menu.addScoresButtonAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rankingAction();
            }
        });

        menu.addExitButtonAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exitAction();
            }
        });
    }

    public void startAction() {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.createBoardSizePicker();
        menu.close();
    }

    public void rankingAction() {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.createRankingStage();
    }

    public void exitAction() {
        System.exit(0);
    }
}
