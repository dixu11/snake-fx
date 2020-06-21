module snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens game.snake.model;
    opens game.stages;
    opens game;
}