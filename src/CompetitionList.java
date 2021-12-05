import java.awt.Button;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class CompetitionList extends ListView<Competition<?>> {
    // public Button addButton;
    // public Button deleteButton;
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
    }

    public void addButtonClicked() {

    }

    public void deleteButtonClicked() {

    }

    public void competitionCellClicked() {

    }
    public void layoutUI() {

    }

    public void addCompetition() {

    }

    public void selectCompetition(Competition<Participant> competition) {

    }

    public void updateCompeitionList() {

    }
}
