//Dhrishti Hazari and Jayson Pitta
package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.*;
import Display.SongController;


public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Display/SongDisplay.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			SongController libraryController = loader.getController();
			libraryController.start(primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Song List View");
			primaryStage.setResizable(false);  
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}