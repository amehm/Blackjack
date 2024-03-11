import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class BlackJackAPP extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to BlackJack");

		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(1000,200);
		borderPane.setPadding(new Insets(70));

		//creating the image object
		InputStream stream = new FileInputStream("src/main/resources/2_of_clubs.png");
		Image image = new Image(stream);
		//Creating the image view
		ImageView imageView = new ImageView();
		//Setting image to the image view
		imageView.setImage(image);
		//Setting the image view parameters
		imageView.setX(10);
		imageView.setY(10);
		imageView.setFitWidth(575);
		imageView.setPreserveRatio(true);
		Group root = new Group(imageView);

		Scene scene = new Scene(root, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
