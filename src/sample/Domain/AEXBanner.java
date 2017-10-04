package sample;

public class AEXBanner extends Application {

	/**
	 * 
	 * @param koersen
	 */

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle("AEXBanner");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

	public void setKoersen(string koersen) {
		// TODO - implement AEXBanner.setKoersen
		throw new UnsupportedOperationException();
	}


}