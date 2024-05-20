package stack;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("stack.fxml"));
	        loader.setController(new StackController());
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.setTitle("Stack Simulator");
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
