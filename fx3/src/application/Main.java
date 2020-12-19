package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {		
		primaryStage.setTitle("JavaFX");

		VBox vbox = FXMLLoader.load(getClass().getResource("index.fxml"));
		Font.loadFont(getClass().getResourceAsStream("SansitaSwashed-Regular.ttf"), 30);
		
		Controller controller = new Controller(vbox);

		controller.embed();

		Scene scene = new Scene(vbox, 800, 600);
		primaryStage.setScene(scene);

		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());

		primaryStage.show();
		vbox.requestFocus();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
