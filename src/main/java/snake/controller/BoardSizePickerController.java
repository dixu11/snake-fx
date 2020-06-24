package snake.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import snake.view.BoardSizePicker;
import snake.view.ViewFactory;

public class BoardSizePickerController {

    private BoardSizePicker boardPicker;

    public BoardSizePickerController(BoardSizePicker menu) {
        this.boardPicker = menu;
        addButtonActions();
    }

    public void addButtonActions() {
        boardPicker.addStartAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               startAction();
            }
        });
    }

    public void startAction() {
        ViewFactory viewFactory = new ViewFactory();
        String numberString = boardPicker.getBoardSize().getText();
        int number = parseAndValidateNumber(numberString);
        if (number == -1) {
            boardPicker.getMessage().setText("Range: 1 to 20!");
            return;
        }
        viewFactory.createGameStage(number);
        boardPicker.close();
    }

    private int parseAndValidateNumber(String numberText) {
        int number;
        try {
            number = Integer.parseInt(numberText);
        } catch (Exception e) {
            return -1;
        }

        if (number > 0 && number <= 20) {
            return number;
        }
        return -1;
    }

}

