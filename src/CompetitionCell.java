import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class CompetitionCell extends ListCell<Competition<?>> {
	
    
    private BorderPane root ;
    private Label competitionLabel ;
    private Label isDueLabel ;
    
    public CompetitionCell() {    }

    @Override protected void updateItem(Competition<?> competition, boolean empty) {
        super.updateItem(competition, empty);
        
        if (empty || competition == null) {
        	setText(null) ;
        	setGraphic(null) ;
        } else {
        	competitionLabel = new Label(competition.getName()) ;
        	isDueLabel = new Label(competition.isDue() ? "DUE" : "") ;
        	isDueLabel.setFont(new Font("Arial", 12));
			// changle the color of isDueLabel
			isDueLabel.setStyle("-fx-text-fill: red;");
			
        	root = new BorderPane() ;
        	root.setLeft(competitionLabel) ;
        	root.setRight(isDueLabel) ;
        	this.setText(null);
        	this.setGraphic(root);
        }
        
     }
    
}
