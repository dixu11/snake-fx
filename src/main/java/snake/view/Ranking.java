package snake.view;

import snake.game.game.RankingManager;
import snake.game.model.Player;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;

public class Ranking extends Stage {
    private VBox root;
    private Scene scene;
    private ListView<Player> list;

    public Ranking() {
        root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        scene = new Scene(root);
        initComponents();
        setTitle("Best scores: ");
        setScene(scene);
        setResizable(false);
        show();
    }

    private void initComponents() {
        list = new ListView<>();
        ScrollPane scrollPane = new ScrollPane(list);
        RankingManager rankingManager = new RankingManager();
        ObservableList<Player> items = list.getItems();
        items.addAll(rankingManager.getRanking());
        Collections.sort(items);
        root.getChildren().add(scrollPane);
    }
}
