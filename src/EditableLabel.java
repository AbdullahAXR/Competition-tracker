import com.sun.source.tree.Tree;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EditableLabel extends HBox{
    Label label = new Label();
    Button button = new Button();
    TextField textField = new TextField();
    HBox hBox = new HBox(label,textField);
    EditableLabel(String title,String contacts) {
        label.setText(title);
        textField.setText(contacts);
        textField.setDisable(true);
        textField.setPrefWidth(500);
        setDisableStyle();
        HBox.setMargin(label, new Insets(10, 10, 10, 10));
        HBox.setMargin(textField, new Insets(10, 10, 10, 10));

    }
    public void buttonClicked() {
    	if(textField.isDisabled()) {
    		textField.setDisable(false);	
    		setEnableStyle();
    	}
    	else {
    		textField.setDisable(true);
    		setDisableStyle();
    	}
    }
    public void setEnableStyle() {
    	textField.setStyle("-fx-font-color: BLACK");
    	textField.setAlignment(Pos.CENTER_LEFT);
    }
    public void setDisableStyle() {
    	textField.setStyle("-fx-opacity: 1.0;"+"-fx-background-color: #F4F4F4;"+"-fx-border: gone;");
    	textField.setAlignment(Pos.CENTER);
    	
    }
    public HBox getHBox() {
    	return hBox;
    }
}


