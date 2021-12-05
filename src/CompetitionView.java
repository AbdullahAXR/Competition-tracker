import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.control.ListView;

public class CompetitionView extends VBox {
    // Competition<?> currentCompetition;
    Label CompetitionName = new Label(); // will be editableLabel  
    Label infoLbl = new Label("Info:");
    private Pane infoPane = new VBox();
    Label linkLbl = new Label("Link: ");
    Label dateLbl = new Label("Date: ");
    Label typeLbl = new Label("Type: ");
    private Button addParticipantButton;
	private Button browseButton;
	private Button emailBtn = new Button("Email");
    private Button editButton;
	private Button exitButton;
	private Button sumbitButton;
	private TableView particioantTableView;
	
	// We need to create a class of InfoViewLabeled & editableLabel

    CompetitionView(ListView<Competition<?>> lv){
        super();
        this.getChildren().add(CompetitionName);

        update();

        emailBtn.setOnAction(e -> sendEmail());

        lv.getSelectionModel().selectedItemProperty().addListener( (observable, oldv, newv) ->
                {
                    update();
                });

    }

	public void infoPane() {
		infoPane.getChildren().add(linkLbl);
		infoPane.getChildren().add(dateLbl);
		infoPane.getChildren().add(typeLbl);
		infoPane.setStyle("-fx-border-color: black");
	}
	public void homeScene(Pane pane) {
		Scene homeScene = new Scene(pane);
	}
	public void browseBotton() {
		Button browseButton = new Button("Browse");
		
		}
	public void addButtonClicked() {
		
	}
	public void removeButtonClicked() {
		
	}
	public void CompetitionCellClicked() {
	
	}	
	
	public void displayCompetition() {
		
	}
	
	public void layoutUI() {
		
	}
	public void displayCometitions(Competition competitions) {
		
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

    // maybe you should fill this instad
    private void setup(){
    }


    private void update(){
        if(Globals.currentCompetition != null){
        	CompetitionName.setText(Globals.currentCompetition.getName());
        	CompetitionName.setPrefSize(325, 30);
        	CompetitionName.setAlignment(Pos.CENTER);
        	// this.getChildren().add(CompetitionName);
        	// this.getChildren().add(infoLbl);
        	// infoPane();	
        	// this.getChildren().add(infoPane);
	        // this.getChildren().add(new Label(Globals.currentCompetition.toString()));
	        // this.getChildren().add(emailBtn);
        }
    }
}
