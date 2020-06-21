package game.snake.model;

public class Player {

    private String name;
    private int points;

    public Player() {
        name = "";
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }


    public void setName(String name) {
        this.name = name;
    }
}
