package game.snake.model;


import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import game.snake.logic.Game;

public class Segment extends GameObject {
    private int lastX;
    private int lastY;

    private Direction direction;
    private Direction lastDirection;
    private Segment nextSegment = null;


    public Segment(int cordX, int cordY, Color color) {
        super(cordX, cordY, color);
        direction = Direction.RIGHT;

        updateLastPosition();
        lastDirection = direction;
    }

    private void updateLastPosition() {
        lastX = cordX;
        lastY = cordY;
    }


    public void move() {
        updateLastPosition();

        switch (direction) {
            case UP:
                cordY--;
                break;
            case DOWN:
                cordY++;
                break;
            case LEFT:
                cordX--;
                break;
            case RIGHT:
                cordX++;
                break;
        }
        moveTail();
        updateView();
        lastDirection = direction;

    }

    private void moveTail() {
        if (nextSegment == null) {
            return;
        }
        nextSegment.direction = lastDirection;
        nextSegment.move();
    }

    public void updateDirection(KeyCode code) {
        Direction newDirection;
        switch (code) {
            case A:
                newDirection = Direction.LEFT;
                break;
            case W:
                newDirection = Direction.UP;
                break;
            case S:
                newDirection = Direction.DOWN;
                break;
            case D:
                newDirection = Direction.RIGHT;
                break;
            default:
                newDirection = direction;
        }
        if (nextSegment != null && newDirection.getReverseDirection() == lastDirection) {
            return;
        }
        direction = newDirection;
    }

    public boolean eat(Game game) {
        Food food = game.findFood(cordX, cordY);
        if (food == null) {
            return false;
        }
        if (food.isGrowing()) {
            addSegment(game.getRoot());
        } else {
            removeSegment(game.getRoot());
        }
        game.removeFood(food);
        return true;
    }


    public void addSegment(Pane root) {
        if (nextSegment == null) {
            nextSegment = new Segment(lastX, lastY, Color.BLACK);
            root.getChildren().add(nextSegment);
        } else {
            nextSegment.addSegment(root);
        }
    }

    // [][][]

    private void removeSegment(Pane root) {
        if (nextSegment == null) {
            return;
        }else {
            Segment beforeLastSegment = findBeforeLastSegment(this);
            root.getChildren().remove(beforeLastSegment.nextSegment);
            beforeLastSegment.nextSegment = null;
        }
    }

    private Segment findBeforeLastSegment(Segment askingSegment) {
        if (nextSegment != null) {
           return nextSegment.findBeforeLastSegment(this);
        } else {
            return askingSegment;
        }
    }

    public boolean hasCollisionWithTail() {
        return hasTailOnFieldOf(cordX, cordY);
    }

    public boolean hasCollisionWithWall() {
        return cordX < 0 ||
                cordX >= Game.rowCount ||
                cordY < 0 ||
                cordY >= Game.rowCount;
    }
    //todo -> przerobić na przechodzenie przez ściane?

    @Override
    public boolean isOnField(int x, int y) {
        if (cordX == x && cordY == y) {
            return true;
        } else if (nextSegment != null) {
            return nextSegment.isOnField(x, y);
        }
        return false;
    }

    private boolean hasTailOnFieldOf(int x, int y) {
        if (nextSegment != null) {
            return nextSegment.isOnField(x, y);
        }
        return false;
    }


}
