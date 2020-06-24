package snake.game.game;

import snake.game.model.*;

import java.util.Random;

public class FoodFactory {

    private Game game;

    public FoodFactory(Game game) {
        this.game = game;
    }

    public void spawnFood() {
        if (game.getMoves() % 6 == 0) {
            executeSpawnFood();
        }
    }

    private void executeSpawnFood() {
        if (hasNoFreeSpace()) {
            return;
        }
        Random random = new Random();
        int x = random.nextInt(Game.rowCount);
        int y = random.nextInt(Game.rowCount);
        if ( isSnakeOnField(x,y) || isFoodOnField(x,y)) {
            executeSpawnFood();
            return;
        }
        Food food = buildRandomFood(x, y);
        game.addFood(food);
    }

    private boolean isFoodOnField(int x, int y) {
        return game.isFoodOnField(x,y);
    }

    private Food buildRandomFood(int x, int y) {
        if (Math.random() < 0.9) {
            return new GrowingFood(x,y);
        } else {
            return new ShorteningFood(x, y);
        }
    }

    private boolean isSnakeOnField(int x, int y) {
      Segment snake =  game.getSnake();
        return snake.isOnField(x, y);
    }

    private boolean hasNoFreeSpace(){
        Segment snake = game.getSnake();
        for (int y = 0; y < Game.rowCount; y++) {
            for (int x = 0; x < Game.rowCount; x++) {
                if (!snake.isOnField(x,y) && !game.isFoodOnField(x,y)) {
                    return false;
                }
            }
        }
        return true;
    }

}
