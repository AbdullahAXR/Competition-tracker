import com.sun.source.tree.Tree;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EditableLabel {
    Label label = new Label();
    Button button = new Button();
    TextField textField = new TextField();
    HBox hBox = new HBox(label,textField);
    EditableLabel(String title,String contacts) {
        label.setText(title);
        textField.setText(contacts);
        setDisable(true);
        textField.setStyle(contacts);
        textField.setStyle("-fx-opacity: 1.0;");
        HBox.setMargin(label, new Insets(10, 10, 10, 10));
        HBox.setMargin(textField, new Insets(10, 10, 10, 10));

    }
    public void setDisable(boolean trueOr){
        textField.setDisable(trueOr);
    }
    public void mouseClicked() {
    textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	 
        @Override
        public void handle(MouseEvent click) {
     
            if (click.getClickCount() == 2) {
            	if (textField.isDisable()) {
					setDisable(false);
				}
            	else {
					setDisable(true);
				}
              
            }
        }
    });
    }
    
    public HBox getHBox() {
    	return hBox;
    }
}


