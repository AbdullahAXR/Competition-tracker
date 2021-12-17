import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.apache.poi.hwpf.model.types.LVLFAbstractType;

import javafx.scene.control.ListView;

public class CompetitionView extends VBox {
    // Competition<?> currentCompetition;
	private CompetitionList lv;
	private Button addComptitionButton = new Button("Add");
	private BorderPane topPane = new BorderPane();
	private EditableLabel CompetitionName = new EditableLabel(""," "); // will be editableLabel  
	private Label infoLbl = new Label("Info: ");
    private Pane infoPane = new VBox();
    private EditableLabel linkLbl = new EditableLabel("Link:"," ");
    private EditableLabel dateLbl = new EditableLabel("Date:"," ");
    private EditableRadioButton typeRadioButton = new EditableRadioButton("Type:","Team base Competition");
	private Button browseBtn = new Button("Browse");
    private Button editBtn = new Button("Edit");
	private ParticipantTablePane particpantPane = new ParticipantTablePane();
	
    CompetitionView(ListView<Competition<?>> lv){
        super();
        setup();
        update();
        this.lv = (CompetitionList) lv;
        lv.getSelectionModel().selectedItemProperty().addListener( (observable, oldv, newv) ->
                {
                    update();
                });

    }

    private void infoPane() {
    	browseBtn();
    	linkLbl.getChildren().add(browseBtn);
		infoPane.getChildren().add(linkLbl);
		infoPane.getChildren().add(dateLbl);
		infoPane.getChildren().add(typeRadioButton);
		infoPane.setStyle("-fx-border-color: black");
		infoPane.setPadding(new Insets(5,5,5,5));
		VBox.setMargin(infoPane,new Insets(0,50,0,50));
	}
	
    public void browseBtn() {
        browseBtn.setOnAction((e) -> browseButtonClicked());
    }
    
    public void editBtn() {
    	editBtn.setOnAction((e) -> {
    		if (editBtn.getText().equals("Edit")) {
	    		editBtn.setText("Save");
	    		typeRadioButton.editButtonClicked("Individual base Competition","Team base Competition");
    		}
    		else if (editBtn.getText().equals("Save")) {
                saveModifications();
                editBtn.setText("Edit");
    			typeRadioButton.saveButtonClicked();
    		}
    		CompetitionName.buttonClicked();
    		linkLbl.buttonClicked();
    		dateLbl.buttonClicked();
    		particpantPane.participantTableView.setEditable(!particpantPane.participantTableView.isEditable());
    		particpantPane.btnsSetDisabled(!particpantPane.btnsIsDisabled());
    		particpantPane.fill();
    		particpantPane.participantTableView.refresh();
    	});
    }
	
	private void saveModifications() {
		System.out.println("Saving Modifications");
		Globals.currentCompetition.setName(CompetitionName.getTextFieldText());
		Globals.currentCompetition.setLink(linkLbl.getTextFieldText());
		
        try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Globals.currentCompetition.setDate(sdf.parse(dateLbl.getTextFieldText()));
        	dateLbl.setTextFieldText(Globals.currentCompetition.getDateString());
        }
        catch(ParseException e){
            // ignore user's input
        }
        finally {
            // set the dataLbl value to the value of the (possibly new) date string of the competitoin.
            dateLbl.setTextFieldText(Globals.currentCompetition.getDateString());
        }
	}

    public void browseButtonClicked() {
        CompetitionBrowser.browse(Globals.currentCompetition);
    }
	
	public void displayBlank() {
		
	}

	private boolean isValid() {
		return true;
	}
	
	private boolean displayError() {
		return true;
	}
	
	private boolean displaySuccess() {
		return false;
	}

    // maybe you should fill this instead
    private void setup(){
        this.setStyle("-fx-border-color: black");
        CompetitionName.getTextField().setMaxWidth(500);
        CompetitionName.getTextField().setAlignment(Pos.CENTER);
        topPane.setCenter(CompetitionName.getTextField());
        topPane.setPadding(new Insets(10));
        topPane.setRight(editBtn);
        this.getChildren().add(topPane);
        infoLbl.setPadding(new Insets(10,50,10,50));
    	this.getChildren().add(infoLbl);
    	infoPane();
    	this.getChildren().add(infoPane);
    	this.getChildren().add(particpantPane);  
        editBtn();
        addButtonClicked();
    }

    private void update(){
    	particpantPane.fill();
        if(Globals.currentCompetition != null){
        	CompetitionName.setTextFieldText(Globals.currentCompetition.getName());
        	dateLbl.setTextFieldText(Globals.currentCompetition.getDateString());
        	linkLbl.setTextFieldText(Globals.currentCompetition.getLink());
        	if (Globals.currentCompetition instanceof TeamCompetition) {
            	typeRadioButton.setValue("Team base Competition");
            	typeRadioButton.updateValueInTypelabel();
        	} else {
            	typeRadioButton.setValue("Individual base Competition");
            	typeRadioButton.updateValueInTypelabel();
        	}
        }
    }
    private void addButtonClicked() {
    	addComptitionButton.setOnAction((e) -> {
    		CompetitionName.setTextFieldText("Name");
    		dateLbl.setTextFieldText("dd/MM/yyyy");
    		linkLbl.setTextFieldText("Compeition link");
    		editBtn.setText("Save");
    		typeRadioButton.editButtonClicked("Individual base Competition","Team base Competition");
    		typeRadioButton.enableButtons();
    		CompetitionName.buttonClicked();
    		linkLbl.buttonClicked();
    		dateLbl.buttonClicked();
    		editBtn();
    		typeRadioButton.enableButtons();
    		if (typeRadioButton.getValue().equals("Individual base Competition")) {
    			Competition<Student> newCompetition = new StudentCompetition(CompetitionName.getTextFieldText(),linkLbl.getTextFieldText(), null);
        		Globals.competitions.add(newCompetition);
				Globals.currentCompetition = newCompetition;
				newCompetition.setDate(null);
        	}else {
        		Competition<Team> newCompetition = new TeamCompetition(CompetitionName.getTextFieldText(),linkLbl.getTextFieldText(), null);
        		Globals.competitions.add(newCompetition);
				Globals.currentCompetition = newCompetition;
				newCompetition.setDate(null);
        	}
			
				
        	
    		
    	});
    		
    	if (typeRadioButton.getValue().equals("Individual base Competition")) {
    		System.out.println("Indiv");//Compeition<StudentCompetition> newCompetition = new Competition<StudentCompetition>();
    	}else {
    		System.out.println("Team Boy");
    	}
    	
    	
    }
    public Button getAddButton() {
    	return addComptitionButton;
    }
    
    public HBox getHBoxButtons() {
    	HBox buttonsBox = new HBox();
    	Button deleteButton = lv.getDeleteButton();
    	buttonsBox.getChildren().addAll(addComptitionButton,deleteButton);
    	HBox.setMargin(addComptitionButton, new Insets(5, 2, 8, 5));
        HBox.setMargin(deleteButton, new Insets(0, 5, 0, 5));
        buttonsBox.setAlignment(Pos.BASELINE_CENTER);
        buttonsBox.setMaxHeight(100);
        buttonsBox.setSpacing(80);
        return buttonsBox;
    }

    
}
