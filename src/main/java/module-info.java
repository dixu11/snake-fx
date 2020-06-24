module snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens snake.game.model;
    opens snake.view;
    opens snake;
}