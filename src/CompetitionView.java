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
	private Label CompetitionName = new Label(); // will be editableLabel  
	private Label infoLbl = new Label("Info: ");
    private Pane infoPane = new VBox();
    private BorderPane topPane = new BorderPane();
    private Label linkLbl = new Label("Link: ");
    private Label dateLbl = new Label("Date: ");
    private Label typeLbl = new Label("Type: ");
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
		infoPane.getChildren().add(linkLbl);
		infoPane.getChildren().add(dateLbl);
		infoPane.getChildren().add(typeLbl);
		infoPane.setStyle("-fx-border-color: black");
		infoPane.setPadding(new Insets(5,5,5,5));
		VBox.setMargin(infoPane,new Insets(0,50,10,50));
	}
	public void homeScene(Pane pane) {
		Scene homeScene = new Scene(pane);
	}
	public void browseBtn() {
		browseBtn = new Button("Browse");
		
		}
	public void addButtonClicked() {
		
	}
	public void removeButtonClicked() {
		
	}
	public void CompetitionCellClicked() {
	
	}	
	
	public void layoutUI() {
		
	}
	public void displayCompetition(Competition<Participant> competition) {
		
	}
	public void displayBlank() {
		
	}
    // public String[] getEmails(Participant p){
    //     String[] emails;
    //     Team t;
    //     if(p instanceof Team)
    //         Team t = (Team) p;
    //         emails = new String[t.size()];
    //         email = ((Team) p).getEmails();
    //     else
    //         emails[0] = ((Student) p).getEmail();

    //     return emails;
    // }
    public void sendEmail(){
        new Thread(() -> {
            String[] emails;
            try {
                Emailer.emailStudent(new Student("201914330", "saher", "cs"), (StudentCompetition) Globals.currentCompetition);
                // Emailer.sendEmail(new URI("mailto:s201914330@kfupm.edu.sa"));
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
        CompetitionName.setMinSize(325, 30);
    	CompetitionName.setAlignment(Pos.CENTER);
        topPane.setCenter(CompetitionName);
        topPane.setPadding(new Insets(10));
        topPane.setRight(editBtn);
        this.getChildren().add(topPane);
        infoLbl.setPadding(new Insets(10,50,10,50));
    	this.getChildren().add(infoLbl);
    	infoPane();
    	this.getChildren().add(infoPane);
    	particpantPane();
    	this.getChildren().add(particpantPane);  
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
        	CompetitionName.setText(Globals.currentCompetition.getName());
        	if (Globals.currentCompetition instanceof TeamCompetition) {
        		teams.setVisible(true);
            	teamsNames.setVisible(true);
            	participantTableView.setMaxWidth(80 * 8);
        	} else {
        		teams.setVisible(false);
            	teamsNames.setVisible(false);
            	participantTableView.setMaxWidth(80 * 5);
            	
        	}
        }
    }
    private ObservableList<Map> generateDataInMap() {
    	ObservableList<Map> alldata = FXCollections.observableArrayList();
    	Iterator<Student> students = Globals.currentCompetition.getStudents().iterator();
    	while (students.hasNext()) {
    		Map<String,String> dataRow = new HashMap<>();
    		Student currentStudent = students.next();
    		dataRow.put(ids.getText(), currentStudent.getId());
    		dataRow.put(names.getText(), currentStudent.getName());
    		dataRow.put(majors.getText(), currentStudent.getMajor());
    		// if statement needed for team type 
    		dataRow.put(teams.getText(), "-");
    		dataRow.put(teamsNames.getText(), "-");
    		dataRow.put(ranks.getText(), "-");
    		// 
//    		dataRow.put(ranks.getText(), Globals.currentCompetition.getResult(currentStudent));
    		alldata.add(dataRow);
    	}
    	return alldata;
    }
}
