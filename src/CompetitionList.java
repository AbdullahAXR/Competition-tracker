import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CompetitionList extends ListView<Competition<?>> {
     public Button addButton = new Button("Add");
     public Button deleteButton = new Button("Delete");
    // public Pane competitionView;
    // public Competition<?> currentCompetition;
    

    CompetitionList(){
        this(null);
    }

    CompetitionList(ObservableList<Competition<?>> comps){
        super(comps);
        this.getSelectionModel().selectedItemProperty().addListener( (observable, oldv, newv) ->
                {
                    Globals.currentCompetition = newv;
                    System.out.println(Globals.currentCompetition);
                });
                
        deleteButton.setOnAction(e -> deleteButtonClicked());
        // Should we select the first one automatically? What if the list is
        // empty
        // this.getSelectionModel().select(0);
    }

    public void addButtonClicked() {
    	
    }

    public void deleteButtonClicked() {
        // get selected competition
        Competition<?> selectedCompetition = this.getSelectionModel().getSelectedItem();
        // remove it from the list
        Globals.competitions.remove(selectedCompetition);
        
        // print out the list
        System.out.println("Competitions: ");
        for (Competition<?> c : Globals.competitions) {
            System.out.println(c);
        }
        
        // No need to remove it from the list view. The observable list and the
        // compeititonlist are connected

        // this.getItems().remove(selectedCompetition);
        
    }

    public void competitionCellClicked() {
        
    }
    
    public void layoutUI() {

    }

    public void addCompetition() {
    	
    }
    
    public HBox buttonHBox() {
    	HBox buttonsBox = new HBox();
    	buttonsBox.getChildren().addAll(addButton,deleteButton);
    	HBox.setMargin(addButton, new Insets(5, 2, 8, 5));
        HBox.setMargin(deleteButton, new Insets(0, 5, 0, 5));
        buttonsBox.setAlignment(Pos.BASELINE_CENTER);
        buttonsBox.setMaxHeight(100);
        buttonsBox.setSpacing(80);
        return buttonsBox;
    }

    public void selectCompetition(Competition<Participant> competition) {

    }

    public void updateCompeitionList() {

    }
}
