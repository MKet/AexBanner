package AexBanner.Domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AEXBanner extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("AexBanner.fxml"));
		primaryStage.setTitle("AEXBanner");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

	public void setKoersen(String koersen) {
		// TODO - implement AEXBanner.setKoersen
		throw new UnsupportedOperationException();
	}


}