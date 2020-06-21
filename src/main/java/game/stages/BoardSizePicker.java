package game.stages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardSizePicker extends Stage {

    private VBox layout;
    private Scene scene;

    private Label text;
    private TextField boardSize;
    private Button start;


    public BoardSizePicker() {
        layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        scene = new Scene(layout);
        setScene(scene);

        text = new Label("Type board size: ");
        boardSize = new TextField();

        start = new Button("Start");
        layout.getChildren().addAll(text, boardSize, start);

        addButtonsActions();

        show();
    }

    public void addButtonsActions() {
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageFactory stageFactory = new StageFactory();
                String numberString = boardSize.getText();
                int number = parseAndValidateNumber(numberString);
                if (number == -1) {
                    text.setText("Range: 1 to 20!");
                    return;
                }
                stageFactory.createGameStage(number);
                close();
            }
        });
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
