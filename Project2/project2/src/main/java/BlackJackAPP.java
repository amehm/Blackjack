import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BlackJackAPP extends Application {
	private Button playButton, rulesButton, dealButton, hitButton, stayButton;
	private HBox frontPageBox;

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to BlackJack");
		WelcomePage(primaryStage);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
	private void WelcomePage(Stage primaryStage){
		// title on welcome page
		Text title = new Text("BlackJack");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
		title.setFill(Color.rgb(247, 231, 231));

		rulesButton = new Button("Rules");
		playButton = new Button("Play");
		rulesButton.setStyle("-fx-font-family: 'Verdana';-fx-background-color: #F7E7E7; -fx-font-size: 40;-fx-background-radius: 5em;-fx-font-style: italic; -fx-font-weight: bold;");
		playButton.setStyle("-fx-font-family: 'Verdana';-fx-background-color: #F7E7E7; -fx-font-size: 40;-fx-background-radius: 5em;-fx-font-style: italic; -fx-font-weight: bold;");

		frontPageBox = new HBox(20, rulesButton, playButton);
		frontPageBox.setPadding(new Insets(50));
		frontPageBox.setAlignment(javafx.geometry.Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(700, 700);
		borderPane.setPadding(new Insets(100));
		borderPane.setTop(title);
		BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);
		borderPane.setBottom(frontPageBox);
		borderPane.setStyle("-fx-background-color: #1B3E12;");
		Scene scene = new Scene(borderPane, 1000, 650);

		primaryStage.setScene(scene);

		// rules page
		rulesButton.setOnAction(event -> {
            try {
                RulesPage(primaryStage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

		// play page
		playButton.setOnAction(event -> {
            try {
                ChooseStartingValuePage(primaryStage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
	}
	private ImageView goBackHomeButton(Stage primaryStage) throws FileNotFoundException {
		InputStream stream = new FileInputStream("src/main/resources/blackjack-icon-3.png");
		Image homeImage = new Image(stream);

		ImageView goBackHomeImage = new ImageView(homeImage);
		goBackHomeImage.setOnMouseClicked(event -> WelcomePage(primaryStage));
		goBackHomeImage.setFitWidth(50);
		goBackHomeImage.setFitHeight(50);
		return goBackHomeImage;
	}
	private void ChooseStartingValuePage(Stage primaryStage) throws FileNotFoundException {
		BorderPane startingValPage = new BorderPane();
		startingValPage.setPrefSize(700, 700);
		startingValPage.setPadding(new Insets(10));
		startingValPage.setStyle("-fx-background-color: #1B3E12;");

		InputStream streamBackground = new FileInputStream("src/main/resources/startingvalbackground.png");
		Image backgroundImage = new Image(streamBackground);
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.fitWidthProperty().bind(startingValPage.widthProperty());
		backgroundImageView.fitHeightProperty().bind(startingValPage.heightProperty());
		startingValPage.getChildren().add(backgroundImageView);

		ImageView homeImageView = goBackHomeButton(primaryStage);
		startingValPage.setTop(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

//		Button goBackButton = new Button("Homepage");
//		goBackButton.setStyle("-fx-font-family: 'Verdana';-fx-background-color: #F7E7E7; -fx-font-size: 20;-fx-background-radius: 5em;-fx-font-style: italic; -fx-font-weight: bold;");
//		goBackButton.setOnAction(event -> {
//			WelcomePage(primaryStage);
//		});
//		startingValPage.setTop(goBackButton);
//		BorderPane.setAlignment(goBackButton, Pos.TOP_LEFT);

		Text title = new Text("Please Choose Starting Money Amount");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35));
		title.setFill(Color.rgb(247, 231, 231));
		startingValPage.setCenter(title);
		BorderPane.setAlignment(title, Pos.TOP_CENTER);

		Scene rulesScene = new Scene(startingValPage, 1000, 650);
		primaryStage.setScene(rulesScene);
	}

	private void RulesPage(Stage primaryStage) throws FileNotFoundException {
		BorderPane rulesPane = new BorderPane();
		rulesPane.setPrefSize(1000, 650);
		rulesPane.setPadding(new Insets(10));
		rulesPane.setStyle("-fx-background-color: #1B3E12;");

//		Button goBackButton = new Button("Homepage");
//		goBackButton.setStyle("-fx-font-family: 'Verdana';-fx-background-color: #F7E7E7; -fx-font-size: 20;-fx-background-radius: 5em;-fx-font-style: italic; -fx-font-weight: bold;");
//		goBackButton.setOnAction(event -> {
//			WelcomePage(primaryStage);
//		});
//
//		rulesPane.setTop(goBackButton);
//		BorderPane.setAlignment(goBackButton, Pos.TOP_LEFT);
		ImageView homeImageView = goBackHomeButton(primaryStage);
		rulesPane.setTop(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

		Text rulesText = new Text("Rules of BlackJack");
		rulesText.setFill(Color.rgb(247, 231, 231));
		rulesPane.setCenter(rulesText);
		Scene rulesScene = new Scene(rulesPane, 1000, 650);
		primaryStage.setScene(rulesScene);
	}


//		//creating the image object
//		InputStream stream = new FileInputStream("src/main/resources/2_of_clubs.png");
//		Image image = new Image(stream);
//		//Creating the image view
//		ImageView imageView = new ImageView();
//		//Setting image to the image view
//		imageView.setImage(image);
//		//Setting the image view parameters
//		imageView.setX(10);
//		imageView.setY(10);
//		imageView.setFitWidth(575);
//		imageView.setPreserveRatio(true);
//		Group root = new Group(imageView);
//
//		Scene scene = new Scene(root, 700,700);
//		primaryStage.setScene(scene);
//		primaryStage.show();

}
