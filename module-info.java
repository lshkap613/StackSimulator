module stackSimulator {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	
	opens stack to javafx.graphics, javafx.fxml;
}