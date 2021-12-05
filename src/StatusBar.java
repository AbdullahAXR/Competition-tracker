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
        // updateStatusBar(); // we might tun this whenever there's a change
    }

    StatusBar(){
        this(0);
    }

    // This method sucks, find a way to listen to changes
    // Start from here https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ListChangeListener.Change.html
    // Basically, create a loop that groes through the changes and updates three
    // varaibles numOfDue, numOfTeam, and numOfStudent. It shouldn't be too
    // hard, follow the example in the link and **READ** the documentation

    // observableArrayList
    // public void updateStatusBar() {
    //     numberOfTeamCompetitions = Globals.MANAGER.getNumberOfTeamCompetition();
    //     numberOfStudentCompetitions = Globals.MANAGER.size() - Globals.MANAGER.getNumberOfTeamCompetition();
    //     numberOfDue = Globals.MANAGER.getNumberOfDue();
    //     // this.getChildren().add(new Label(MANAGER.get(0).toString()));
    //     this.getChildren().add(new Label("Student Competitions: "+numberOfStudentCompetitions));
    //     this.getChildren().add(new Label("Team Competitions: "+numberOfTeamCompetitions));
    //     this.getChildren().add(new Label("Due Competitions: "+numberOfDue));
    // }

    // public stat

}
