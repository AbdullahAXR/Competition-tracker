import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CompetitionView extends Pane {
    Competition<?> currentCompetition;
    private Button addParticipantButton;
	private Button browseButton;
	private Pane infoPane;
	private Button emailBtn = new Button("Email");
    private Button editButton;
	private Button exitButton;
	private Button sumbitButton;
	private TableView particioantTableView;
	
	// We need to create a class of InfoViewLabeled & editableLabel

    CompetitionView(){
        super();
        if(!Globals.MANAGER.isEmpty()){
        this.getChildren().add(new Label(Globals.MANAGER.get(0).toString()));
        this.getChildren().add(emailBtn);
        }

        emailBtn.setOnAction(e -> sendEmail());
    }

	public void infoPane() {
		Pane infoPane = new Pane();
		
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
                Emailer.emailStudent(new Student("201914330", "saher", "cs"), (StudentCompetition) Globals.MANAGER.get(0));
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
}
