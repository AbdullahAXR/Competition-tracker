import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Pos;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.*;


public class StatusBar extends HBox {
    
    Label numberOfstudentsCompetitionsLabel = new Label("Student Competitions: ");
    Label numberOfTeamComptitonsLabel = new Label("Team Competitions: ");
    Label numberOfDueComptitionsLabel = new Label("Due Competitions: ");

    int numberOfTeamCompetitions;
    int numberOfStudentCompetitions;
    int numberOfDue;
    int totalCompetition;

    //StatusBar(int spacing){
    //    super(spacing);

    //    // updateStatusBar(); // we might tun this whenever there's a change
    //    //
    //}

    StatusBar(){
        super(Globals.SPACING);
        this.getChildren().add(numberOfstudentsCompetitionsLabel) ;
        this.getChildren().add(numberOfTeamComptitonsLabel) ;
        this.getChildren().add(numberOfDueComptitionsLabel) ;
        this.setAlignment(Pos.CENTER); // should we really hardcode this?

        setupLabels();
        // untested. I can't test this until we can edit and add competitions
        Globals.competitions.addListener(new ListChangeListener<Competition<?>>() {
            public void onChanged(Change<? extends Competition<?>> c) {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            System.out.println("premutated");
                            //permutate
                        }
                    } else if (c.wasUpdated()) {
                        System.out.println("updated!"); //update item
                    } else { 
                        for (Competition<?> remitem : c.getRemoved()) {
                            System.out.println("removed");
                            setupLabels();
                        }
                        for (Competition<?> additem : c.getAddedSubList()) {
                            System.out.println("added");
                            setupLabels();
                            // additem.add(Outer.this);
                        }
                    }
                }
            }
        });
    }
        
    public void setupLabels(){
        
        numberOfTeamCompetitions = 0;
        numberOfStudentCompetitions = 0;
        numberOfDue = 0;
        for(Competition<?> c : Globals.competitions){
            if(c instanceof TeamCompetition)
                numberOfTeamCompetitions++;
            else
                numberOfStudentCompetitions++;

            if(c.isDue())
                numberOfDue++;
        }

        numberOfstudentsCompetitionsLabel.setText("Student Competitions: "+numberOfStudentCompetitions) ;
        numberOfTeamComptitonsLabel.setText("Team Competitions: "+numberOfTeamCompetitions);
        numberOfDueComptitionsLabel.setText("Due Competitions: "+numberOfDue);
    }

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
