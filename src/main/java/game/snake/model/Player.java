package game.snake.model;

import game.snake.logic.Game;

import java.io.Serializable;

public class Player implements Comparable<Player> , Serializable {

    private String name;
    private int points;
    private int finalScore;

    public Player() {
        name = "";
        points = 0;
        finalScore = 0;
    }

    public Player(String name, int points, int finalScore) {
        this.name = name;
        this.points = points;
        this.finalScore = finalScore;
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

    public void setFinalScore(double timeInSeconds) {
        finalScore = (int) (points + ((timeInSeconds / Game.rowCount) * 2));
    }

    @Override
    public int compareTo(Player o) {
        return o.finalScore - finalScore;
    }

    @Override
    public String toString() {
        return name + " " + finalScore;
    }
}
