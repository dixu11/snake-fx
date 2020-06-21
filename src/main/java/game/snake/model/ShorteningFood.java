package game.snake.model;

import javafx.scene.paint.Color;

public class ShorteningFood extends Food {
    public ShorteningFood(int cordX, int cordY) {
        super(cordX, cordY);
    }

    @Override
    public boolean isGrowing() {
        return false;
    }

    @Override
    public Color getFoodColor() {
        return Color.GREEN;
    }
}
