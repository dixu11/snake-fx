package game.snake.model;

import javafx.scene.paint.Color;

public abstract class Food extends GameObject {
    public Food(int cordX, int cordY) {
        super(cordX, cordY, Color.WHITE);
        setFill(getFoodColor());
    }


    public abstract boolean isGrowing();

    public abstract Color getFoodColor();

}
