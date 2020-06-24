package snake.game.model;

import javafx.scene.control.Label;
import snake.game.game.Game;

public class Counter extends Label {

    private Player player;
    private String prefix = "Points: ";

    public Counter(Player player) {
        this.player = player;
        setTranslateX(Game.boardSize * 0.8);
        setTranslateY(Game.boardSize * 0.05);
        setText(prefix + 0);
    }

    public void update() {
       setText(prefix + player.getPoints());
    }



    /*
    * TODO  dostosować kolor
    * TODO wstawić na pierwszy plan
    * */
}
