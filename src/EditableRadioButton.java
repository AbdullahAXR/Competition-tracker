import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;



public class EditableRadioButton extends HBox {
	private Label label = new Label();
	private RadioButton leftRadioButton = new RadioButton();
	private RadioButton rightRadioButton = new RadioButton();
	private ToggleGroup toggleGroup = new ToggleGroup();
	private String value;
	public EditableRadioButton(String Title, String Choise1, String Choise2) {
		label.setText(Title);
		leftRadioButton.setText(Choise1);
		rightRadioButton.setText(Choise2);
        this.getChildren().add(label);
        this.getChildren().add(leftRadioButton);
        this.getChildren().add(rightRadioButton);
        HBox.setMargin(label, new Insets(5, 2, 8, 5));
        HBox.setMargin(leftRadioButton, new Insets(5, 5, 0, 5));
        HBox.setMargin(rightRadioButton, new Insets(5, 5, 0, 5));
       leftRadioButton.setToggleGroup(toggleGroup);
       rightRadioButton.setToggleGroup(toggleGroup);
       value = Choise1;

       toggleGroup.selectedToggleProperty().addListener( (ov, t1, t2) -> changed(ov, t1, t2));
	}
       
       public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
			RadioButton rb = (RadioButton)toggleGroup.getSelectedToggle();
			value = rb.getText();
		}
       
       public String getValue() {
    	   return value;
       }
	
	
	
    

}
