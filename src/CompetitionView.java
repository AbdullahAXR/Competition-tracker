import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class CompetitionView extends VBox {
    // Competition<?> currentCompetition;
	private EditableLabel CompetitionName = new EditableLabel(" ","Name"); // will be editableLabel  
	private Label infoLbl = new Label("Info: ");
    private Pane infoPane = new VBox();
    private BorderPane topPane = new BorderPane();
    private EditableLabel linkLbl = new EditableLabel("Link:","www.google.com");
    private EditableLabel dateLbl = new EditableLabel("Date:","5/10/2019");
    private EditableLabel typeLbl = new EditableLabel("Type:","Team");
    private Button addParticipantBtn;
	private Button browseBtn;
	private Button emailBtn = new Button("Email");
    private Button editBtn = new Button("Edit");
	private Button exitBtn;
	private Button submitBtn;
	private VBox particpantPane = new VBox(Globals.SPACING);
	private TableView participantTableView = new TableView();
	private TableColumn<Map, String> ids = new TableColumn<>("Student ID");
	private TableColumn<Map, String> names = new TableColumn<>("Student Name");
	private TableColumn<Map, String> majors = new TableColumn<>("Major");
	private TableColumn<Map, String> teams = new TableColumn<>("Team");
	private TableColumn<Map, String> teamsNames = new TableColumn<>("Team Name");
	private TableColumn<Map, String> ranks = new TableColumn<>("Rank");
	
	// We need to create a class of InfoViewLabeled & editableLabel

    CompetitionView(ListView<Competition<?>> lv){
        super();
        setup();
        update();

        emailBtn.setOnAction(e -> sendEmail());

        lv.getSelectionModel().selectedItemProperty().addListener( (observable, oldv, newv) ->
                {
                    update();
                });

    }

    private void infoPane() {
		infoPane.getChildren().add(linkLbl.getHBox());
		infoPane.getChildren().add(dateLbl.getHBox());
		infoPane.getChildren().add(typeLbl.getHBox());
		infoPane.setStyle("-fx-border-color: black");
		infoPane.setPadding(new Insets(5,5,5,5));
		VBox.setMargin(infoPane,new Insets(0,50,10,50));
	}
	public void homeScene(Pane pane) {
		Scene homeScene = new Scene(pane);
	}

    public void browseBtn() {
    	
		browseBtn = new Button("Browse");
        browseBtn.setOnAction((e) -> browseButtonClicked());
        this.getChildren().add(browseBtn);
    }
    
    public void editBtn() {
    	editBtn.setOnAction((e) -> {
    		CompetitionName.buttonClicked();
    		linkLbl.buttonClicked();
    		dateLbl.buttonClicked();
    	});
    }

	public void addButtonClicked() {
		
	}
	public void removeButtonClicked() {
		
	}
	public void CompetitionCellClicked() {
	
	}	

    public void browseButtonClicked() {
        CompetitionBrowser.browse(Globals.currentCompetition);
    }
	
	public void layoutUI() {
		
	}
	public void displayCompetition(Competition<Participant> competition) {
		
	}
	public void displayBlank() {
		
	}

    public void sendEmail(){
        new Thread(() -> {
            try {

                // example 1
                Team t = new Team("swe-group 5");
                t.add(new Student("2010101", "saher", "cs"));
                t.add(new Student("2020202", "mod", "cs"));
                t.add(new Student("2030303", "abdullah", "swe"));
                t.add(new Student("2030303", "mohammad", "swe"));
                Emailer.email((TeamCompetition) Globals.currentCompetition, t);

                // example 2
                // Emailer.email((StudentCompetition) Globals.currentCompetition, new Student("201914330", "saher", "cs"));
            }
            catch(Exception e){
                System.out.println(e);
            }
        }).start();
    }
    // private URI getURI(){
    //     currentCompetition.getStudents().stream().filter(
    // }
	private boolean isValid() {
		return true;
	}
	private boolean displayError() {
		return true;
	}
	private boolean displaySuccess() {
		return false;
	}
	private void particpantPane() {
		VBox.setMargin(participantTableView, new Insets(10,50,10,50));
		int cellWidth = 80;
		ids.setMinWidth(cellWidth);
    	names.setMinWidth(cellWidth * 2);
    	majors.setMinWidth(cellWidth);
    	teams.setMinWidth(cellWidth);
    	teamsNames.setMinWidth(cellWidth*2);
    	ranks.setMinWidth(cellWidth);
    	
    	ids.setCellValueFactory(new MapValueFactory<>(ids.getText()));
    	names.setCellValueFactory(new MapValueFactory<>(names.getText()));
    	majors.setCellValueFactory(new MapValueFactory<>(majors.getText()));
    	teams.setCellValueFactory(new MapValueFactory<>(teams.getText()));
    	teamsNames.setCellValueFactory(new MapValueFactory<>(teamsNames.getText()));
    	ranks.setCellValueFactory(new MapValueFactory<>(ranks.getText()));
    	
    	participantTableView.setEditable(true);
    	participantTableView.getColumns().addAll(ids, names, majors, teams, teamsNames,ranks);
    	particpantPane.getChildren().add(participantTableView);
    	particpantPane.setAlignment(Pos.CENTER);
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
    	particpantPane();
    	this.getChildren().add(particpantPane);  
        this.getChildren().add(emailBtn);
        browseBtn();
        editBtn();
    }


    private void update(){
    	participantTableView.setItems(generateDataInMap());
    	Callback<TableColumn<Map, String>, TableCell<Map, String>>
    	cellFactoryForMap = (TableColumn<Map, String> p) ->
    	new TextFieldTableCell<>(new StringConverter() {
    		@Override
    		public String toString(Object t) {
    			return t.toString();
    		}
    		@Override 
    		public Object fromString(String string) {
    			return string;
    		}
    	});
    	ids.setCellFactory(cellFactoryForMap);
    	names.setCellFactory(cellFactoryForMap);
    	majors.setCellFactory(cellFactoryForMap);
    	teams.setCellFactory(cellFactoryForMap);
    	teamsNames.setCellFactory(cellFactoryForMap);
    	ranks.setCellFactory(cellFactoryForMap);

        if(Globals.currentCompetition != null){
        	CompetitionName.setTextFieldText(Globals.currentCompetition.getName());
        	dateLbl.setTextFieldText(Globals.currentCompetition.getDate());
        	linkLbl.setTextFieldText(Globals.currentCompetition.getLink());
        	if (Globals.currentCompetition instanceof TeamCompetition) {
        		teams.setVisible(true);
            	teamsNames.setVisible(true);
            	participantTableView.setMaxWidth(80 * 8);
            	typeLbl.setTextFieldText("Team base Competitiongit ");
        	} else {
        		teams.setVisible(false);
            	teamsNames.setVisible(false);
            	participantTableView.setMaxWidth(80 * 5);
            	typeLbl.setTextFieldText("Indivitoal base Competition");
        	}
        }
    }
    private ObservableList<Map> generateDataInMap() {
    	ObservableList<Map> alldata = FXCollections.observableArrayList();
    	Iterator<?> particpants = Globals.currentCompetition.getParticipants().iterator();
    	int teamNum = 1;
    	while (particpants.hasNext()) {
    		Map<String,String> dataRow = new HashMap<>();
    		Student currentStudent;
    		if (Globals.currentCompetition instanceof TeamCompetition) {
    			Team currentTeam = (Team) particpants.next();
    			Iterator<Student> students = currentTeam.getStudents().iterator();
    			currentStudent = new Student();
    			while (students.hasNext()) {
    				dataRow = new HashMap<>();
    				currentStudent = students.next();
	    			dataRow.put(ids.getText(), currentStudent.getId());
		    		dataRow.put(names.getText(), currentStudent.getName());
		    		dataRow.put(majors.getText(), currentStudent.getMajor());
	    			dataRow.put(teams.getText(), Integer.toString(teamNum));
		    		dataRow.put(teamsNames.getText(), currentTeam.getName());
		    		dataRow.put(ranks.getText(), ((Competition<Participant>) Globals.currentCompetition).getResult(currentTeam));
		    		alldata.add(dataRow);
    			}
    			
    		} 
    		else {
    			currentStudent = ((Student) particpants.next());
	    		dataRow.put(ids.getText(), currentStudent.getId());
	    		dataRow.put(names.getText(), currentStudent.getName());
	    		dataRow.put(majors.getText(), currentStudent.getMajor());
	    		dataRow.put(teams.getText(), "-");
	    		dataRow.put(teamsNames.getText(), "-");
	    		dataRow.put(ranks.getText(), ((Competition<Participant>) Globals.currentCompetition).getResult(currentStudent));
	    		alldata.add(dataRow);
    		}
    		teamNum++;	
    	}

    	return alldata;
    }
}
