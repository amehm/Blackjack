import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class BlackJackAPP extends Application {
	private Button dealButton;
	private Button hitButton;
	private Button stayButton;
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

	private void WelcomePage(Stage primaryStage) {
		// Title on welcome page
		Text title = new Text("BlackJack");
		title.setFont(Font.font("Lucida Calligraphy",150));
		title.setFill(Color.rgb(247, 231, 231));
		title.setTextAlignment(TextAlignment.CENTER);

		Button rulesButton = new Button("Rules");
		Button playButton = new Button("Play");
		rulesButton.setStyle("-fx-font-family: 'Garamond';-fx-font-size: 25px; -fx-background-color: #8fbc8f; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-radius: 20px;-fx-font-weight: bold");
		playButton.setStyle("-fx-font-family: 'Garamond';-fx-font-size: 25px; -fx-background-color: #8fbc8f; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-radius: 20px;-fx-font-weight: bold");

//		rulesButton.setPrefWidth(200);
//		playButton.setPrefWidth(200);
//		rulesButton.setPrefHeight(40);
//		playButton.setPrefHeight(40);
//		rulesButton.setStyle("-fx-font-family: 'Garamond';-fx-background-color: #2f4f4f; -fx-font-size: 40;-fx-background-radius: 5em;-fx-font-style: italic; -fx-font-weight: bold;-fx-text-fill: WHITE;");
//		playButton.setStyle("-fx-font-family: 'Garamond';-fx-background-color: #2f4f4f; -fx-font-size: 40;-fx-background-radius: 5em;-fx-font-style: italic; -fx-font-weight: bold;-fx-text-fill: WHITE;");

		HBox frontPageBox = new HBox(20, rulesButton, playButton);
		frontPageBox.setAlignment(Pos.CENTER);

		VBox allElements = new VBox(70, title, frontPageBox);
		allElements.setAlignment(Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(1000, 650);
		borderPane.setPadding(new Insets(100));

		borderPane.setCenter(allElements);
		BorderPane.setAlignment(allElements, Pos.CENTER);

		borderPane.setStyle("-fx-background-color: #1B3E12;");
		Scene scene = new Scene(borderPane);

		primaryStage.setScene(scene);

		// Rules page
		rulesButton.setOnAction(event -> {
			try {
				RulesPage(primaryStage);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		});

		// Play page
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

	// "Choose Starting Value" page
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

		VBox vbox = new VBox(100, title, hbox);
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

	// "Make Your Bets" page
	private void MakeYourBetsPage(Stage primaryStage, BlackjackGame game) throws FileNotFoundException {
		BorderPane betsPane = new BorderPane();
		betsPane.setPrefSize(1000, 650);
		betsPane.setPadding(new Insets(10));
		betsPane.setStyle("-fx-background-color: #1B3E12;");

		ImageView homeImageView = goBackHomeButton(primaryStage);
		betsPane.setLeft(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

		Text money = new Text("Balance: $" + String.valueOf(game.totalWinnings) + "0");
		money.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		money.setFill(Color.rgb(247, 231, 231));

		Text bet = new Text("Bet: $" + String.valueOf(game.currentBet));
		bet.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		bet.setFill(Color.rgb(247, 231, 231));

		VBox topRightVals = new VBox(10, money, bet);
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

		HBox firstThreeChips = new HBox(30, chipGrey, chipRed, chipGreen);
		HBox secondThreeChips = new HBox(30, chipBlue, chipBlack, chipPurple);
		VBox allChips = new VBox(15, firstThreeChips, secondThreeChips);


		chipRed.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 5, money, betsPane, titlePane);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});


		chipGrey.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 1, money, betsPane, titlePane);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});


		chipGreen.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 25, money, betsPane, titlePane);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});


		chipBlue.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 50, money, betsPane, titlePane);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});


		chipBlack.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 100, money, betsPane, titlePane);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});


		chipPurple.setOnMouseClicked(event -> {
			try {
				betCalculation(primaryStage, game, 500, money, betsPane, titlePane);
				updateBetAmountLabel(bet, game.getCurrentBet());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});


		Button finalizeBet = new Button("Place Bet");
		finalizeBet.setStyle("-fx-font-family: 'Arial';-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-pref-width: 180px; -fx-pref-height: 60px; -fx-background-radius: 5px;");
		Text emptySpace = new Text("       ");
		emptySpace.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 80));

		VBox betButtomAlignment = new VBox(10, emptySpace, finalizeBet);

		HBox chipsandButton = new HBox(30, allChips, betButtomAlignment);
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

	private void betCalculation(Stage primaryStage, BlackjackGame game, double amount, Text balanceLabel, BorderPane betsPane, Pane titlePane) throws FileNotFoundException {
		if (amount > game.getCurrentValue()) {
			Text insufficientFunds = new Text("Not Enough Money!");
			insufficientFunds.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
			insufficientFunds.setFill(Color.RED);
			VBox vbox = new VBox(insufficientFunds);
			vbox.setAlignment(Pos.CENTER);
			betsPane.setCenter(vbox);
			return;
		}
		game.setCurrentBet(game.getCurrentBet() + amount);
		game.setCurrentValue(game.getCurrentValue() - amount);
		updateBalanceLabel(balanceLabel, game.getCurrentValue());
		betsPane.setCenter(titlePane);
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
		playPane.setLeft(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

		Scene playScene = new Scene(playPane, 1000, 650);
		primaryStage.setScene(playScene);

		Text money = new Text("Balance: $" + String.valueOf(game.totalWinnings));
		money.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		money.setFill(Color.rgb(247, 231, 231));
		Text bet = new Text("Bet: $" + String.valueOf(game.currentBet));
		bet.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		bet.setFill(Color.rgb(247, 231, 231));
		VBox topRightVals = new VBox(10, money, bet);
		playPane.setRight(topRightVals);
		BorderPane.setAlignment(topRightVals, Pos.TOP_RIGHT);

		Button hitButton = new Button("Hit");
		Button stayButton = new Button("Stay");
		hitButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: white; -fx-background-color: linear-gradient(#61a2b1, #2A5058); -fx-background-radius: 5em; -fx-padding: 8px 16px;-fx-pref-width: 120px; -fx-pref-height: 40px;");
		stayButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: white; -fx-background-color: linear-gradient(#61a2b1, #2A5058); -fx-background-radius: 5em; -fx-padding: 8px 16px;-fx-pref-width: 120px; -fx-pref-height: 40px;");

		HBox buttonBox = new HBox(20, hitButton, stayButton);
		buttonBox.setAlignment(Pos.CENTER);
		playPane.setBottom(buttonBox);
		BorderPane.setAlignment(buttonBox, Pos.BOTTOM_CENTER);

		// start game:
		game.playerHand.clear();
		game.bankerHand.clear();
		game.theDealer.shuffleDeck();
		// Deal two cards to each player
		game.playerHand.addAll(game.theDealer.dealHand());
		game.bankerHand.addAll(game.theDealer.dealHand());

		Text playerTotal = new Text("Player: " + String.valueOf(game.gameLogic.handTotal(game.playerHand)));
		playerTotal.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		playerTotal.setFill(Color.rgb(247, 231, 231));
		Text dealerTotal = new Text("Dealer:   ");
		dealerTotal.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		dealerTotal.setFill(Color.rgb(247, 231, 231));

		Text emptySpace = new Text("       ");
		Text emptySpace2 = new Text("       ");
		emptySpace.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
		emptySpace2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
		VBox playerTotalAligned = new VBox(emptySpace, playerTotal);
		VBox dealerTotalAligned = new VBox(emptySpace2, dealerTotal);

		HBox playerHandBox = new HBox(10, playerTotalAligned);
		HBox dealerHandBox = new HBox(10, dealerTotalAligned);
		VBox bothHandsBox = new VBox(30, dealerHandBox, playerHandBox);
		playPane.setCenter(bothHandsBox);
		bothHandsBox.setAlignment(Pos.CENTER);
		ImageView hiddenCard = new ImageView();

		// display player's hand
		for (Card card : game.playerHand) {
			String imagePath = "src/main/resources/" + card.CardPathAsString() + ".png";
			InputStream stream1 = new FileInputStream(imagePath);
			Image cardImage = new Image(stream1);
			ImageView imageCard = new ImageView(cardImage);
			imageCard.setFitWidth(110);
			imageCard.setFitHeight(154);
			playerHandBox.getChildren().add(imageCard);
		}

		boolean firstCard = true;
		for (Card card : game.bankerHand) {
			if (firstCard) {
				hiddenCard = new ImageView(new Image(new FileInputStream("src/main/resources/hidden.png")));
				hiddenCard.setFitHeight(154);
				hiddenCard.setFitWidth(110);
				dealerHandBox.getChildren().add(hiddenCard);
				firstCard = false;
			} else {
				String imagePath = "src/main/resources/" + card.CardPathAsString() + ".png";
				ImageView shownCard = new ImageView(new Image(new FileInputStream(imagePath)));
				shownCard.setFitHeight(154);
				shownCard.setFitWidth(110);
				dealerHandBox.getChildren().add(shownCard);
			}
		}

		hitButton.setOnAction(event -> {
			game.playerHand.add(game.theDealer.drawOne());
			String imagePath = "src/main/resources/" + game.playerHand.get(game.playerHand.size() - 1).CardPathAsString() + ".png";
			ImageView newCard;
			try {
				newCard = new ImageView(new Image(new FileInputStream(imagePath)));
				newCard.setFitWidth(110);
				newCard.setFitHeight(154);
				playerHandBox.getChildren().add(newCard);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if (game.gameLogic.handTotal(game.playerHand) >= 22) {
				hitButton.setDisable(true);
				showOutcomeMessage(primaryStage, playPane, "Sorry, you lost!", game.totalWinnings, game);
			}
			// update strings:
			playerTotal.setText("Player: " + String.valueOf(game.gameLogic.handTotal(game.playerHand)));
		});

		ImageView finalHiddenCard = hiddenCard;
		stayButton.setOnAction(event -> {
			stayButton.setDisable(true);
			hitButton.setDisable(true);
			dealerHandBox.getChildren().remove(finalHiddenCard);
			Card hiddenCardValue = game.bankerHand.get(0); //get value of hidden card
			String hiddenCardPath = "src/main/resources/" + hiddenCardValue.CardPathAsString() + ".png";
			ImageView revealedCard = null;
			try {
				revealedCard = new ImageView(new Image(new FileInputStream(hiddenCardPath)));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			revealedCard.setFitWidth(110);
			revealedCard.setFitHeight(154);
			dealerHandBox.getChildren().add(1, revealedCard);

			// evaluate if banker needs to draw again:
			while (game.gameLogic.evaluateBankerDraw(game.bankerHand)) {
				game.bankerHand.add(game.theDealer.drawOne());
				ImageView newDealerCard = getImageView(game);
				dealerHandBox.getChildren().add(newDealerCard);
			}

			// update strings:
			playerTotal.setText("Player: " + String.valueOf(game.gameLogic.handTotal(game.playerHand)));
			dealerTotal.setText("Dealer: " + String.valueOf(game.gameLogic.handTotal(game.bankerHand)));

			String winner = game.gameLogic.whoWon(game.playerHand, game.bankerHand);
			double winnings = game.evaluateWinnings();
			double balance = game.totalWinnings + game.currentBet + winnings;

			// restart if negative balance
			if (game.totalWinnings < 1) {
				showOutcomeMessage(primaryStage, playPane, "You've reached a balance of 0, restart?", game.totalWinnings, game);
			} else if (game.gameLogic.handTotal(game.playerHand) == 21 && game.playerHand.size() == 2) {
				game.totalWinnings *= 1.5;
				showOutcomeMessage(primaryStage, playPane, "Blackjack! You won!", game.totalWinnings, game);
			} else if (Objects.equals(winner, "player")) {
				showOutcomeMessage(primaryStage, playPane, "Congratulations! You won!", game.totalWinnings, game);
			} else if (Objects.equals(winner, "dealer")) {
				showOutcomeMessage(primaryStage, playPane, "Sorry, you lost!", game.totalWinnings, game);
			} else {
				showOutcomeMessage(primaryStage, playPane, "Draw!", game.totalWinnings, game);
			}

		});
	}
	private void showOutcomeMessage(Stage primaryStage, BorderPane playPane, String message, Double balance, BlackjackGame game) {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.7);
		playPane.setEffect(colorAdjust);

		Text messageText = new Text(message);
		messageText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		messageText.setFill(Color.WHITE);
		messageText.setTextAlignment(TextAlignment.CENTER);

		Text balanceText = new Text("New Balance: $" + balance);
		balanceText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		balanceText.setFill(Color.WHITE);

		Button playAgain = new Button("Play Again?");
		playAgain.setOnAction(event -> {
			try {
				game.currentBet = 0;
				MakeYourBetsPage(primaryStage, game);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});

		Button backHome = new Button("Go Back To Home");
		backHome.setOnAction(event -> {
			WelcomePage(primaryStage);
		});

		HBox buttonBox = new HBox(20, playAgain, backHome);
		buttonBox.setAlignment(Pos.CENTER);

		VBox messageBox = new VBox(20, messageText, balanceText, buttonBox);
		messageBox.setAlignment(Pos.CENTER);

		if (Objects.equals(message, "You've reached a balance of 0, restart?")) {
			playAgain.setDisable(true);
		}

		StackPane stackPane = new StackPane(playPane, messageBox);
		StackPane.setAlignment(messageBox, Pos.CENTER);

		Scene scene = new Scene(stackPane, 1000, 650);
		primaryStage.setScene(scene);
	}
	private static ImageView getImageView(BlackjackGame game) {
		Card drawnCard = game.bankerHand.get(game.bankerHand.size() - 1);
		String drawnCardPath = "src/main/resources/" + drawnCard.CardPathAsString() + ".png";
		ImageView newDealerCard = null;
		try {
			newDealerCard = new ImageView(new Image(new FileInputStream(drawnCardPath)));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		newDealerCard.setFitWidth(110);
		newDealerCard.setFitHeight(154);
		return newDealerCard;
	}

	private void RulesPage(Stage primaryStage) throws FileNotFoundException {
		BorderPane rulesPane = new BorderPane();
		rulesPane.setPrefSize(1000, 650);
		rulesPane.setPadding(new Insets(10));
		rulesPane.setStyle("-fx-background-color: #1B3E12;");

		ImageView homeImageView = goBackHomeButton(primaryStage);
		rulesPane.setTop(homeImageView);
		BorderPane.setAlignment(homeImageView, Pos.TOP_LEFT);

		VBox rulesVBox = new VBox(10); // Space between each Text node is 10 pixels
		rulesVBox.setAlignment(Pos.TOP_LEFT);
		rulesVBox.setPadding(new Insets(20, 20, 20, 20)); // Adjust padding as needed

		String[] rulesArray = {
				"Rules of BlackJack",
				"* Players: You, the user, play against a banker controlled by the computer.\n",
				"* Initial Deal: Each player is dealt two cards. You can see both your cards, but only one of the banker's cards is visible.\n",
				"* Card Values: Face cards are worth 10 points, and Aces can be worth 1 or 11 points, depending on what benefits you the most.\n",
				"  * Player Options: After receiving your initial two cards, you can choose to:\n    * \"Stay\": Keep your current total.\n    * \"Hit\": Receive another card. You can continue hitting until your total exceeds 21, resulting in a \"bust\" and an automatic loss.\n",
				"* Banker's Turn: Once you decide to stay, it's the banker's turn to play. The banker must hit on any hand totaling 16 or lower and must stay on any hand totaling 17 or higher.\n",
				"* Winning: The player with the higher hand total wins. If both hands are equal, it's a draw, and no money changes hands.\n",
				"* Blackjack: If you get a total of 21 with just two cards (an Ace and a card with a value of 10), you achieve \"blackjack.\" You win 150% of your bet unless the banker also achieves blackjack, resulting in a draw.\n"
		};

		for (String rule : rulesArray) {
			Text ruleText = new Text(rule);
			ruleText.setFill(Color.rgb(247, 231, 231));
			ruleText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
			ruleText.setWrappingWidth(960); // Ensure text wraps to avoid going offscreen
			rulesVBox.getChildren().add(ruleText);
		}

		rulesPane.setCenter(rulesVBox);
		Scene rulesScene = new Scene(rulesPane, 1000, 650);
		primaryStage.setScene(rulesScene);
	}
}

