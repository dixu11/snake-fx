package game.snake.model;

import javafx.scene.paint.Color;

public class GrowingFood extends Food {
    public GrowingFood(int cordX, int cordY) {
        super(cordX, cordY);
    }

    @Override
    public boolean isGrowing() {
        return true;
    }

    @Override
    public Color getFoodColor() {
        return Color.YELLOW;
    }
}
