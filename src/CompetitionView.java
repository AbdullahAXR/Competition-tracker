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
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class CompetitionView extends VBox {
    // Competition<?> currentCompetition;
	private EditableLabel CompetitionName = new EditableLabel("","Name"); // will be editableLabel  
	private Label infoLbl = new Label("Info: ");
    private Pane infoPane = new VBox();
    private BorderPane topPane = new BorderPane();
    private EditableLabel linkLbl = new EditableLabel("Link:","www.google.com");
    private EditableLabel dateLbl = new EditableLabel("Date:","5/10/2019");
    private EditableLabel typeLbl = new EditableLabel("Type:","Team");
    private EditableRadioButton typeRadioButton = new EditableRadioButton("Type:","Team base Competition");
    private Button addParticipantBtn;
	private Button browseBtn;
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
	private TableColumn<Map, Void> emailColumn = new TableColumn<>("Email");
	
	// We need to create a class of InfoViewLabeled & editableLabel

    CompetitionView(ListView<Competition<?>> lv){
        super();
        setup();
        update();


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
		VBox.setMargin(infoPane,new Insets(0,50,10,50));
	}
	public void homeScene(Pane pane) {
		Scene homeScene = new Scene(pane);
	}

    public void browseBtn() {
    	
		browseBtn = new Button("Browse");
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
        	participantTableView.setEditable(!participantTableView.isEditable());
        	participantTableView.refresh();
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
	private void emailColumn() {
        

        Callback<TableColumn<Map, Void>, TableCell<Map, Void>> cellFactory = new Callback<TableColumn<Map, Void>, TableCell<Map, Void>>() {
            @Override
            public TableCell<Map, Void> call(final TableColumn<Map, Void> param) {
                final TableCell<Map, Void> cell = new TableCell<Map, Void>() {
                	byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x93, (byte)0xA7};
                	private final Button emailBtn = new Button(new String(emojiByteCode, Charset.forName("UTF-8")));

                    {
                    	setAlignment(Pos.CENTER);
                    	// sendEmail() trasnfered to here 
                    	emailBtn.setOnAction(a -> {
                    		new Thread(() -> {
                              try {
                            	  
                            	  HashMap row = (HashMap) participantTableView.getItems().get(getIndex());
                            	  
                                  // example 1
                            	  if (Globals.currentCompetition instanceof TeamCompetition) {
	                            	  int teamNumber = Integer.parseInt((String) row.get(teams.getText())) - 1;
	                            	  TeamCompetition competition = (TeamCompetition) Globals.currentCompetition;
	                            	  Team t = (Team) competition.getTeams().toArray()[teamNumber];
	                                  Emailer.email((TeamCompetition) Globals.currentCompetition, t);
                            	  }
                            	  // example 2
                            	  else {
                            		  String studentID = (String) row.get(ids.getText());
                            		  System.out.println(studentID);
                            		  Student s = new Student();
                            		  Iterator<Student> students = Globals.currentCompetition.getStudents().iterator();
                            		  while (students.hasNext()) {
                            			  Student sTmp = students.next();
                            			  if (sTmp.getId().equals(studentID))       
                            				  s = sTmp;
                            		  }
	                                  Emailer.email((StudentCompetition) Globals.currentCompetition, s);
                            	  }
                              }
                              catch(Exception e){
                                  System.out.println(e);
                              }
                          }).start();
                    	});
                    }
                	
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(emailBtn);
                        }
                    }
                };
                return cell;
            }
        };
        emailColumn.setCellFactory(cellFactory);
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
    	emailColumn.setMinWidth(cellWidth);
    	
    	ids.setCellValueFactory(new MapValueFactory<>(ids.getText()));
    	names.setCellValueFactory(new MapValueFactory<>(names.getText()));
    	majors.setCellValueFactory(new MapValueFactory<>(majors.getText()));
    	teams.setCellValueFactory(new MapValueFactory<>(teams.getText()));
    	teamsNames.setCellValueFactory(new MapValueFactory<>(teamsNames.getText()));
    	ranks.setCellValueFactory(new MapValueFactory<>(ranks.getText()));
    	emailColumn();
    	participantTableView.getColumns().addAll(ids, names, majors, teams, teamsNames,ranks,emailColumn);
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
        	dateLbl.setTextFieldText(Globals.currentCompetition.getDateString());
        	linkLbl.setTextFieldText(Globals.currentCompetition.getLink());
        	if (Globals.currentCompetition instanceof TeamCompetition) {
        		teams.setVisible(true);
            	teamsNames.setVisible(true);
            	participantTableView.setMaxWidth(80 * 9);
            	typeRadioButton.setValue("Team base Competition");
            	typeRadioButton.updateValueInTypelabel();
        	} else {
        		teams.setVisible(false);
            	teamsNames.setVisible(false);
            	participantTableView.setMaxWidth(80 * 6);
            	typeRadioButton.setValue("Individual base Competition");
            	typeRadioButton.updateValueInTypelabel();

        	}
        	
        }
    }
    private ObservableList<Map> generateDataInMap() {
    	ObservableList<Map> alldata = FXCollections.observableArrayList();
    	//TODO Solve the null value Exception
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
