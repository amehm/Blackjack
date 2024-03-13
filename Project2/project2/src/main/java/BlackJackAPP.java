import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BlackJackAPP extends Application {
	private Button playButton, rulesButton, dealButton, hitButton, stayButton;
	private HBox frontPageBox;
	private double startingMoneyAmount;


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
		BorderPane startingValPane = new BorderPane();
		startingValPane.setPrefSize(700, 700);
		startingValPane.setPadding(new Insets(10));
		startingValPane.setStyle("-fx-background-color: #1B3E12;");

		InputStream streamBackground = new FileInputStream("src/main/resources/startingvalbackground.png");
		Image backgroundImage = new Image(streamBackground);
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.fitWidthProperty().bind(startingValPane.widthProperty());
		backgroundImageView.fitHeightProperty().bind(startingValPane.heightProperty());
		startingValPane.getChildren().add(backgroundImageView);

		ImageView homeImageView = goBackHomeButton(primaryStage);
		startingValPane.setTop(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

		Text title = new Text("Please Choose Starting Money Amount");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35));
		title.setFill(Color.rgb(247, 231, 231));

		Text prompt = new Text("Enter dollar amount then press enter:");
		prompt.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		prompt.setFill(Color.rgb(247, 231, 231));

		TextField enterMoney = new TextField();
		enterMoney.setPromptText("Enter amount here then press enter");
		HBox hbox = new HBox(10, prompt, enterMoney);
		hbox.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(100, title,hbox);
		vbox.setAlignment(Pos.CENTER);
		startingValPane.setCenter(vbox);

		enterMoney.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				try {
					startingMoneyAmount = Double.parseDouble(enterMoney.getText());
					BlackjackGame game = new BlackjackGame();
					game.setCurrentValue(startingMoneyAmount);
					MakeYourBetsPage(primaryStage, game);
				} catch (NumberFormatException e) {
					System.err.println("Please enter a valid number.");
				} catch (FileNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		});

		Scene scene = new Scene(startingValPane, 1000, 650);
		primaryStage.setScene(scene);
	}
	private void MakeYourBetsPage(Stage primaryStage, BlackjackGame game) throws FileNotFoundException {
		BorderPane betsPane = new BorderPane();
		betsPane.setPrefSize(1000, 650);
		betsPane.setPadding(new Insets(10));
		betsPane.setStyle("-fx-background-color: #1B3E12;");

		ImageView homeImageView = goBackHomeButton(primaryStage);
		betsPane.setLeft(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

		Text money = new Text("Balance: $"+String.valueOf(game.currentValue));
		money.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		money.setFill(Color.rgb(247, 231, 231));

		Text bet = new Text("Bet: $"+String.valueOf(game.currentBet));
		bet.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		bet.setFill(Color.rgb(247, 231, 231));

		VBox topRightVals = new VBox(10,money,bet);
		topRightVals.setMaxWidth(50);

		betsPane.setRight(topRightVals);
		BorderPane.setAlignment(topRightVals, Pos.TOP_RIGHT);

		Text title = new Text("Make your Bets");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
		title.setFill(Color.rgb(247, 231, 231));

		StackPane titlePane = new StackPane(title);
		betsPane.setCenter(titlePane);


		ImageView chipGrey = importChips(primaryStage, "grey");
		ImageView chipRed = importChips(primaryStage, "red");
		ImageView chipGreen = importChips(primaryStage, "green");
		ImageView chipBlue = importChips(primaryStage, "blue");
		ImageView chipBlack = importChips(primaryStage, "black");
		ImageView chipPurple = importChips(primaryStage, "purple");

		HBox firstThreeChips = new HBox(30,chipGrey, chipRed, chipGreen);
		HBox secondThreeChips = new HBox(30,chipBlue, chipBlack, chipPurple);
		VBox allChips = new VBox(15,firstThreeChips,secondThreeChips);



		chipRed.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 5, money);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e){
				e.printStackTrace(); // Handle the exception appropriately
			}
		});


		chipGrey.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 1, money);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e){
				e.printStackTrace(); // Handle the exception appropriately
			}
		});


		chipGreen.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 25, money);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e){
				e.printStackTrace(); // Handle the exception appropriately
			}
		});


		chipBlue.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 50, money);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e){
				e.printStackTrace(); // Handle the exception appropriately
			}
		});


		chipBlack.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 100, money);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e){
				e.printStackTrace(); // Handle the exception appropriately
			}
		});


		chipPurple.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 500, money);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e){
				e.printStackTrace(); // Handle the exception appropriately
			}
		});


		Button finalizeBet = new Button("Make Bet");
		finalizeBet.setStyle("-fx-font-family: 'Verdana';-fx-background-color: #F7E7E7; -fx-font-size: 40;-fx-background-radius: 5em;-fx-font-style: italic; -fx-font-weight: bold;");
		Text emptySpace = new Text("       ");
		emptySpace.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 80));

		VBox betButtomAlignment = new VBox(10, emptySpace, finalizeBet);

		HBox chipsandButton = new HBox(20, allChips, betButtomAlignment);
		betsPane.setBottom(chipsandButton);
		chipsandButton.setAlignment(Pos.CENTER);

		Scene betScene = new Scene(betsPane, 1000, 650);
		primaryStage.setScene(betScene);

		// proceed to next page
		finalizeBet.setOnAction(event -> {
			try {
				HitOrStayPage(primaryStage, game);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void updateBetAmountLabel(Text betLabel, double betAmount) {
		betLabel.setText("Bet: $" + String.format("%.2f", betAmount));
	}

	private void updateBalanceLabel(Text balanceLabel, double balanceAmount) {
		balanceLabel.setText("Balance: $" + String.format("%.2f", balanceAmount));
	}

	private void betCalculation(Stage primaryStage, BlackjackGame game, double amount, Text balanceLabel) throws FileNotFoundException {
		if (amount > game.getCurrentValue()) {
			System.out.println("Insuffient funds");
			return;
		}
		game.setCurrentBet(game.getCurrentBet() + amount);
		game.setCurrentValue(game.getCurrentValue() - amount);
		updateBalanceLabel(balanceLabel, game.getCurrentValue());
	}

	private ImageView importChips(Stage primaryStage, String chip) throws FileNotFoundException {
		InputStream stream = new FileInputStream("src/main/resources/" + chip + ".png");
		Image chipImage = new Image(stream);

		ImageView addChipImage = new ImageView(chipImage);
		addChipImage.setOnMouseClicked(event -> WelcomePage(primaryStage));
		addChipImage.setFitWidth(150);
		addChipImage.setFitHeight(150);
		return addChipImage;
	}

	private void HitOrStayPage(Stage primaryStage, BlackjackGame game) throws FileNotFoundException {
		BorderPane playPane = new BorderPane();
		playPane.setPrefSize(1000, 650);
		playPane.setPadding(new Insets(10));
		playPane.setStyle("-fx-background-color: #1B3E12;");

		ImageView homeImageView = goBackHomeButton(primaryStage);
		playPane.setTop(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

		Button hitButton = new Button("Hit");
		Button stayButton = new Button("Stay");

		HBox buttonBox = new HBox(20, hitButton, stayButton);
		buttonBox.setAlignment(Pos.CENTER);
		playPane.setBottom(buttonBox);
		BorderPane.setAlignment(buttonBox, Pos.BOTTOM_CENTER);
		Scene rulesScene = new Scene(playPane, 1000, 650);
		primaryStage.setScene(rulesScene);



		hitButton.setOnAction(event -> {
			game.hitOrStay(true);
			// Add code to flip another card and update total
		});

		stayButton.setOnAction(event -> {
			game.hitOrStay(false);
			// Implement logic for dealer's turn
		});

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
