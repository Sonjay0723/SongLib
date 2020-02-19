package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.*;
import Display.SongController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Display/SongDisplay.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			
			SongController libraryController = loader.getController();
			libraryController.start(primaryStage);
			
			//AnchorPane root = (FXMLLoader.load(getClass().getResource("/SongLib/Display/SongDisplay.fxml")));
			
			Scene scene = new Scene(root,620,380);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Song Library");
			primaryStage.setResizable(false);  
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
