package game.snake.model;

import game.snake.logic.Game;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GameObject extends Rectangle {
    int cordX;
    int cordY;
   final int size = Game.FIELD_SIZE;

    public GameObject(int cordX, int cordY, Color color) {
        this.cordX = cordX;
        this.cordY = cordY;
        setHeight(size);
        setWidth(size);
        setFill(color);
      /*  setStrokeWidth(5); //todo borders
        setStroke(color.brighter().brighter());*/
        updateView();
    }


     void updateView() {
        setX(cordX * size);
        setY(cordY * size);
    }

    public boolean isOnField(int x, int y) {
        return cordX ==x && cordY == y;
    }

}
