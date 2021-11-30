import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StatusBar extends HBox {

    int numberOfTeamCompetitions;
    int numberOfStudentCompetitions;
    int numberOfDue;

    StatusBar(int spacing){
        super(spacing);
        updateStatusBar();
    }

    StatusBar(){
        this(0);
    }

    public void updateStatusBar() {
        numberOfTeamCompetitions = Globals.MANAGER.getNumberOfTeamCompetition();
        numberOfStudentCompetitions = Globals.MANAGER.size() - Globals.MANAGER.getNumberOfTeamCompetition();
        numberOfDue = Globals.MANAGER.getNumberOfDue();
        // this.getChildren().add(new Label(MANAGER.get(0).toString()));
        this.getChildren().add(new Label("Student Competitions: "+numberOfStudentCompetitions));
        this.getChildren().add(new Label("Team Competitions: "+numberOfTeamCompetitions));
        this.getChildren().add(new Label("Due Competitions: "+numberOfDue));
    }

}
