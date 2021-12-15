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
	private Label type = new Label();
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
        //setDisableStyle();// if we tring to create a competition we need to delete this method
        disableButtons();// this one also
        HBox.setMargin(label, new Insets(5, 2, 8, 5));
        HBox.setMargin(leftRadioButton, new Insets(5, 5, 0, 5));
        HBox.setMargin(rightRadioButton, new Insets(5, 5, 0, 5));
        leftRadioButton.setToggleGroup(toggleGroup);
        rightRadioButton.setToggleGroup(toggleGroup);
        leftRadioButton.setSelected(true);// i selected the first choice by default to check if the disable work or not
        value = Choise1;

        toggleGroup.selectedToggleProperty().addListener( (ov, t1, t2) -> changed(ov, t1, t2));
	}
	public EditableRadioButton(String Title, String Type) {
		label.setText(Title);	
		type.setText(Type);
		this.value = Type;
		this.getChildren().add(label);
		this.getChildren().add(type);
		HBox.setMargin(label, new Insets(5, 2, 8, 5));
        HBox.setMargin(type, new Insets(5, 5, 0, 5));
	}
       
       public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
			RadioButton rb = (RadioButton)toggleGroup.getSelectedToggle();
			value = rb.getText();
		}
       
       public String getValue() {
    	   return value;
       }
       public void disableButtons() {
    	   leftRadioButton.setDisable(true);
    	   rightRadioButton.setDisable(true);
       }
       public void enableButtons() {
    	   leftRadioButton.setDisable(false);
    	   rightRadioButton.setDisable(false);
       }
       public void setEnableStyle() {
       	leftRadioButton.setStyle("-fx-font-color: BLACK");
       	rightRadioButton.setStyle("-fx-font-color: BLACK");

       }
       public void setDisableStyle() {
       	leftRadioButton.setStyle("-fx-opacity: 1.0;"+"-fx-background-color: #F4F4F4;"+"-fx-border: gone;");
       	rightRadioButton.setStyle("-fx-opacity: 1.0;"+"-fx-background-color: #F4F4F4;"+"-fx-border: gone;");
       	
       }
       public void editButtonClicked(String Choise1, String Choise2) {
    	   this.getChildren().remove(type);
    	   this.getChildren().add(leftRadioButton);
           this.getChildren().add(rightRadioButton);
           leftRadioButton.setText(Choise1);
   		   rightRadioButton.setText(Choise2);
           HBox.setMargin(leftRadioButton, new Insets(5, 5, 0, 5));
           HBox.setMargin(rightRadioButton, new Insets(5, 5, 0, 5));
           leftRadioButton.setToggleGroup(toggleGroup);
           rightRadioButton.setToggleGroup(toggleGroup);
           if(value.equals(leftRadioButton.getText())) {
        	   toggleGroup.selectToggle(leftRadioButton);
           }else {
			toggleGroup.selectToggle(rightRadioButton);
		}
           disableButtons();
       }
       
       public void saveButtonClicked() {
    	   this.getChildren().remove(leftRadioButton);
    	   this.getChildren().remove(rightRadioButton);
    	   this.getChildren().add(type);
    	   type.setText(value);
    	   HBox.setMargin(type, new Insets(5, 5, 0, 5));
       }
	
	
	
    

}
