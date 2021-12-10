import java.sql.Date;

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
    private Label label = new Label();
    private TextField textField = new TextField();
    private HBox hBox = new HBox(label,textField);
    
    EditableLabel(String title,String contacts) {
        label.setText(title);
        textField.setText(contacts);
        textField.setDisable(true);
        textField.setPrefWidth(500);
        setDisableStyle();
        HBox.setMargin(label, new Insets(5, 2, 8, 5));
        HBox.setMargin(textField, new Insets(0, 5, 0, 5));

    }
    public void setTextFieldText(String text) {
    	textField.setText(text);
    }
    public void setTextFieldText(java.util.Date date) {
    	textField.setText(date.toString());
    }
    public void setLabel(String text) {
    	label.setText(text);
    }
    public String getLabel() {
    	return label.getText();
    }
    public String getTextFieldText() {
    	return textField.getText();
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
    }
    public void setDisableStyle() {
    	textField.setStyle("-fx-opacity: 1.0;"+"-fx-background-color: #F4F4F4;"+"-fx-border: gone;");
    	
    }
    public HBox getHBox() {
    	if(label.getText().equals(""))
    		return null;
    	return hBox;
    }
    public TextField getTextField() {
    	return textField;
    }
	
	
}


