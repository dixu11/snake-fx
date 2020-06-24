package snake.view;

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

    private Label message;
    private TextField boardSize;
    private Button start;


    public BoardSizePicker() {
        layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        scene = new Scene(layout);
        setScene(scene);

        message = new Label("Type board size: ");
        boardSize = new TextField();

        start = new Button("Start");
        layout.getChildren().addAll(message, boardSize, start);

        show();
    }

    public void addStartAction(EventHandler<ActionEvent> action) {
        start.setOnAction(action);
    }

    public Label getMessage() {
        return message;
    }

    public TextField getBoardSize() {
        return boardSize;
    }
}
