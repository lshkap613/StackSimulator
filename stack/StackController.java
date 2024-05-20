package stack;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class StackController implements Initializable{
	
	private int topIndex;
	private Rectangle[] rects;
	private Label[] labs;

	@FXML private Label errorLabel;
	@FXML private Rectangle rect1, rect2, rect3, rect4, rect5;
	@FXML private Label lab1, lab2, lab3, lab4, lab5;
	@FXML private TextField pushValue;
	@FXML private Button push;

    TranslateTransition translateTransition;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// INITIALIZE INDEX OF TOP OF STACK TO -1
		topIndex = -1;
        rects = new Rectangle[]{rect1, rect2, rect3, rect4, rect5};
        labs = new Label[] {lab1, lab2, lab3, lab4, lab5};

        // SET ALL RECTANGLES TO INVISIBLE
        for(Rectangle rect : rects) {
        	rect.setVisible(false);
        }
        
        // SET ALL LABELS  TO INVISIBLE
        for(Label lab : labs) {
        	lab.setVisible(false);
        }
        
        // INSTANTIATE TT
        translateTransition = new TranslateTransition();
	}
	
	
	// METHOD TO PUSH ELEMENT ONTO STACK
	public void handlePushButtonClick() {
		// MAKE SURE ERROR LABEL EMPTY
		errorLabel.setText("");

		// GET STRING TO PUSH ONTO STACK
		String value = pushValue.getText();				
		
		// IF STACK IS NOT AT MAX HEIGHT (FOR THIS SIMULATION) AND THE VALUE TO PUSH ONTO THE STACK EXISTS
		if (!maxStack() && hasValue(value)) {
			
			// IF TOP OF STACK IS STILL LESS THAN MAX, INCREMENT TOP INDEX
			if(topIndex < rects.length - 1) {
				topIndex++;
			}
			
			// SET RECTANGLE CORRESPONDING TO TOP OF STACK TO VISIBLE
			rects[topIndex].setVisible(true);
			
			// ANIMATE STACK ELEMENT COMING DOWN ONTO STACK
			translateTransition.setDuration(Duration.seconds(2));
			translateTransition.setNode(rects[topIndex]);
			translateTransition.setByY(200);
			translateTransition.play();
			
			// AFTER ANIMATION, SHOW LABEL SET TO PUSH VALUE
			translateTransition.setOnFinished(event -> {
				labs[topIndex].setVisible(true);
				labs[topIndex].setText(value);
	        });
					
			// RESET PUSH VALUE TEXT FIELD
			pushValue.setText("");
			pushValue.requestFocus();
		}		
	}
	
	
	// DETERMINE IF REACHED MAX STACK HEIGHT FOR THIS SIMULATION
	private boolean maxStack() {
		boolean max = false;
		
		// IF TOP OF STACK, WRITE ERROR STATEMENT
		if(topIndex == rects.length - 1) {
			errorLabel.setText("This simulation does not allow a stack of more than " + rects.length + " elements");
			max = true;
		} 
		
		return max;
	}
	
	
	// DETERMINE IF TEXTFIELD FOR VALUE TO PUSH ONTO STACK IS EMPTY
	private boolean hasValue(String value) {
		boolean valid = true;
		
		// IF EMPTY, WRITE ERROR STATEMENT
		if (value == "") {
			errorLabel.setText("Stack element must contain value");
			valid = false;
		}
		
		return valid;
	}
	
	
	// METHOD TO HANDLE POP BUTTON CLICKED
	public void handlePopButtonClick() {
		// CLEAR ERROR LABEL
		errorLabel.setText("");

		// IF THERE IS WHAT TO POP OFF...
		if (topIndex >= 0) {
			// MAKE TOP RECTANGLE AND LABEL DISAPPEAR
			rects[topIndex].setVisible(false);
			labs[topIndex].setVisible(false);

			// RETURN RECTANGLE TO INITIAL POSITION (BEFORE ANIMATION)
			double currentY = rects[topIndex].getLayoutY();
			rects[topIndex].setLayoutY(currentY - 200);
			
			// DECREMENT TOP INDEX 
			topIndex--;
		}
	}
}
