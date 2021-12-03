import java.awt.Button;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class CompetitionInfo {
	private Button addParticipantButton;
	private Button browseButton;
	private Pane infoPane;
	private Button sendEmailButton;
	private Button editButton;
	private Button exitButton;
	private Button sumbitButton;
	private TableView particioantTableView;
	
	// We naed to create a class of InfoViewLabeld & editableLabel

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
	public void sendEmaill(Competition competitions) {
		
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
	
}
