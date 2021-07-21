package application;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.animation.*;


public class Main extends Application {
	char direction = ' ';
	Apple apple;
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Snake Game WIP");
			Group root = new Group();
			for (int i = 10; i <= 410; i += 20)
			{
				Line line = new Line();
				line.setStartX(i);
				line.setStartY(50);
				line.setEndX(i);
				line.setEndY(450);
				line.setFill(Color.BLACK);
				root.getChildren().add(line);
			}
			for (int j = 50; j <= 450; j += 20)
			{
				Line line = new Line();
				line.setStartX(10);
				line.setStartY(j);
				line.setEndX(410);
				line.setEndY(j);
				line.setFill(Color.BLACK);
				root.getChildren().add(line);
			}
			Group root2 = new Group();
			Scene game = new Scene(root, 400, 400);
			game.setFill(Color.LIGHTGREEN);
			Scene gameOver = new Scene(root2, 400, 400);
			gameOver.setFill(Color.BLACK);
			TranslateTransition transition = new TranslateTransition();
			Rectangle border1 = new Rectangle(410, 50, 10, 407.5);
			Rectangle border2 = new Rectangle(0, 450, 420, 10);
			Rectangle border3 = new Rectangle(0, 50, 10, 400);
			Rectangle border4 = new Rectangle(0, 40, 420, 10);
			Label score = new Label("0");
			Label label = new Label("Score:");
			Label record = new Label("0");
			Label label2 = new Label("Record:");
			Circle test = new Circle();
			test.setRadius(10);
			test.setCenterX(200);
			test.setCenterY(200);
			test.setFill(Color.BLUE);
			label.setLayoutX(50);
			label.setLayoutY(15);
			score.setLayoutX(85);
			score.setLayoutY(15);
			label2.setLayoutX(300);
			label2.setLayoutY(15);
			record.setLayoutX(345);
			record.setLayoutY(15);
			apple = new Apple();
			apple.setCenterX(300);
			apple.setCenterY(200);
			apple.setFill(Color.RED);
			Snake snake = new Snake();
			snake.setWidth(20);
			snake.setHeight(20);
			snake.setX(190);
			snake.setY(190);
			snake.setFill(Color.GREEN);
			transition.setNode(snake);
			primaryStage.setHeight(497);
			primaryStage.setWidth(434);
			Label endGame = new Label("Game Over!");
			endGame.setFont(new Font("Ink Free", 40));
			endGame.setLayoutX(100);
			endGame.setLayoutY(50);
			endGame.setTextFill(Color.RED);
			Label gameEnd = new Label("Your final score was: " + score.getText() + "!");
			gameEnd.setTextFill(Color.RED);
			gameEnd.setFont(new Font("Ink Free", 25));
			Button playAgain = new Button("Play Again");
			playAgain.setLayoutX(170);
			playAgain.setLayoutY(250);
			playAgain.setOnAction(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent arg0) {
							direction = ' ';
							snake.setTranslateX(0);
							snake.setTranslateY(0);
							apple.isGolden = false;
							apple.setFill(Color.RED);
							apple.setCenterX(300);
							apple.setCenterY(200);
							score.setText("0");
							primaryStage.setScene(game);
							gameEnd.setText("Your final score was: " + score.getText() + "!");
						}
					});
			gameEnd.setLayoutX(90);
			gameEnd.setLayoutY(150);
			root2.getChildren().addAll(endGame, gameEnd, playAgain);
			String test2 = "Hi";
			primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
					{

						@Override
						public void handle(KeyEvent key) {
							
							if (key.getText().equals("w"))
							{
								if (direction != 'W' && direction != 'S')
								{
									if ((snake.getTranslateX() + 10) % 20 == 0 && (snake.getTranslateY() + 10) % 20 == 0)
									{
									direction = 'W';
									transition.stop();
									transition.setDuration(Duration.millis(4000));
									transition.setByX(0);
									transition.setByY(-400);
									transition.play();
								}
								}
							}
							else if (key.getText().equals("a"))
							{
								if (direction != 'A' && direction != 'D')
								{
									if ((snake.getTranslateX() + 10) % 20 == 0 && (snake.getTranslateY() + 10) % 20 == 0)
									{
									direction = 'A';
									transition.stop();
									transition.setDuration(Duration.millis(4000));
									transition.setByY(0);
									transition.setByX(-400);
									transition.play();
									}
								}
							}
							else if (key.getText().equals("s"))
							{
								if (direction != 'S' && direction != 'W')
								{
									if (snake.getTranslateX() % 20 == 0 && snake.getTranslateY() % 20 == 0)
									{
									direction = 'S';
									transition.stop();
									transition.setDuration(Duration.millis(4000));
									transition.setByX(0);
									transition.setByY(400);
									transition.play();
									}
								}
							}
							else if (key.getText().equals("d"))
							{
								if (direction != 'D' && direction != 'A')
								{
									if (snake.getTranslateX() % 20 == 0 && snake.getTranslateY() % 20 == 0)
									{
									direction = 'D';
									transition.stop();
									transition.setDuration(Duration.millis(4000));
									transition.setByY(0);
									transition.setByX(400);
									transition.play();
									}
								}
							}
						}
					});
			// (snake.getX() + 10) % 20 == 0 && (snake.getY() + 10) % 20 == 0
			
			ObservableBooleanValue changeDirection = Bindings.createBooleanBinding(() ->
			(snake.getX() + 10) % 20 == 0 && (snake.getY() + 10) % 20 == 0, snake.boundsInParentProperty());
			changeDirection.addListener(new ChangeListener<Object>()
					{
						@Override
						public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
							if (test2.equals("Hi"))
							{
							switch (direction)
							{
							case 'W':
								
								break;
							case 'A':
								
								break;
							case 'S':
								
								break;
							case 'D':
								
								break;
							}
						}
						}
					});
			
				
			ObservableBooleanValue eating = Bindings.createBooleanBinding(() ->       
	        snake.getBoundsInParent().intersects(apple.getBoundsInParent()), snake.boundsInParentProperty(), 
	        apple.boundsInParentProperty());
			eating.addListener(new ChangeListener<Object>()
					{
						@Override
						public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
					if (snake.getBoundsInParent().intersects(apple.getBoundsInParent())) {
						score.setText(Integer.toString(Integer.parseInt(score.getText()) + apple.pointValue()));
						gameEnd.setText("Your final score was: " + score.getText() + "!");
						root.getChildren().remove(apple);
						apple = new Apple();
						root.getChildren().add(apple);
					}
					if (Integer.parseInt(score.getText()) > Integer.parseInt(record.getText()))
							{
								record.setText(score.getText());
							}
							
						}
					});
			ObservableBooleanValue colliding = Bindings.createBooleanBinding(() ->       
	        snake.getBoundsInParent().intersects(border1.getBoundsInParent()), snake.boundsInParentProperty(), 
	        border1.boundsInParentProperty());
			colliding.addListener(new ChangeListener<Object>()
					{

						@Override
						public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
							transition.stop();
							Apple.setCount(0);
							primaryStage.setScene(gameOver);
						}
					});
			ObservableBooleanValue colliding2 = Bindings.createBooleanBinding(() ->       
	        snake.getBoundsInParent().intersects(border2.getBoundsInParent()), snake.boundsInParentProperty(), 
	        border2.boundsInParentProperty());
			colliding2.addListener(new ChangeListener<Object>()
					{

						@Override
						public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
							transition.stop();
							Apple.setCount(0);
							primaryStage.setScene(gameOver);
						}
					});
			ObservableBooleanValue colliding3 = Bindings.createBooleanBinding(() ->       
	        snake.getBoundsInParent().intersects(border3.getBoundsInParent()), snake.boundsInParentProperty(), 
	        border3.boundsInParentProperty());
			colliding3.addListener(new ChangeListener<Object>()
					{

						@Override
						public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
							transition.stop();
							Apple.setCount(0);
							primaryStage.setScene(gameOver);
						}
					});
			ObservableBooleanValue colliding4 = Bindings.createBooleanBinding(() ->       
	        snake.getBoundsInParent().intersects(border4.getBoundsInParent()), snake.boundsInParentProperty(), 
	        border4.boundsInParentProperty());
			colliding4.addListener(new ChangeListener<Object>()
					{

						@Override
						public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
							transition.stop();
							Apple.setCount(0);
							primaryStage.setScene(gameOver);
						}
					});
			root.getChildren().addAll(snake, label, score, apple, border1, border2, border3, border4, label2, record);
			primaryStage.setScene(game);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
