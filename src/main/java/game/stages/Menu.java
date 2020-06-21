package game.stages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Stage {

    private VBox layout;
    private Scene scene;

    private Button start;
    private Button scores;
    private Button exit;


    public Menu() {
        layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        scene = new Scene(layout);
        setScene(scene);

        start = new Button("Start");
        scores = new Button("Scores");
        exit = new Button("Exit");

        layout.getChildren().addAll(start, scores, exit);

        addButtonsActions();

        show();
    }

    public void addButtonsActions() {
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageFactory stageFactory = new StageFactory();
                stageFactory.createBoardSizePicker();
                close();
            }
        });

        scores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //tworznie okna rankingu
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
}
