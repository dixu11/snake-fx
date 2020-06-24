package snake.game.model;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;


    public Direction getReverseDirection(){
        switch (this){
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                return this;
        }
    }
}
