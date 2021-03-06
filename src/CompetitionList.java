import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import java.util.Date;

public class CompetitionList extends ListView<Competition<?>> {
     public Button addButton = new Button("Add");
     public Button deleteButton = new Button("Delete");
     public Button exitButton = new Button("Exit");
    // public Pane competitionView;
    // public Competition<?> currentCompetition;
    

    CompetitionList(){
        this(null);
    }

    CompetitionList(ObservableList<Competition<?>> comps){
        super(comps);
        Globals.competitions.addListener(new ListChangeListener<Competition<?>>() {

            @Override
            public void onChanged(Change<? extends Competition<?>> c) {
                // TODO Auto-generated method stub
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (Competition<?> additem : c.getAddedSubList()) {
                            additem.addListener(new CompetitionListener() {
                                @Override
                                public void onChange(Competition<?> c) {
                                    updateCompeitionList();
                                }
                                
                                @Override
                                public void dateChanged(Date oldd, Date newd) {}
                            });
                            // setupLabels();
                            // additem.add(Outer.this);
                        }
                    }
                }
            }
            
        });
        for (Competition<?> c : comps) {
            c.addListener(new CompetitionListener() {
                @Override
                public void onChange(Competition<?> c) {
                    updateCompeitionList();
                }
                
                @Override
                public void dateChanged(Date oldd, Date newd) {}
            });
        }
        
        this.setCellFactory(cell -> new CompetitionCell());
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
    	Competition<?> newCompetition;
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
        
    }

    public void exitButtonClicked() {
        
    }

    public void competitionCellClicked() {
        
    }
    
    public void layoutUI() {

    }

    public void addCompetition() {
    	
    }
    
    public HBox buttonHBox() {
    	HBox buttonsBox = new HBox();
    	buttonsBox.getChildren().addAll(addButton, deleteButton, exitButton);
    	HBox.setMargin(addButton, new Insets(5, 2, 8, 5));
        HBox.setMargin(deleteButton, new Insets(0, 5, 0, 5));
        buttonsBox.setAlignment(Pos.BASELINE_CENTER);
        buttonsBox.setMaxHeight(100);
        buttonsBox.setSpacing(80);
        return buttonsBox;
    }

    public Button getDeleteButton() {
    	return deleteButton;
    }

    public Button getExitButton() {
    	return exitButton;
    }


    public void selectCompetition(Competition<Participant> competition) {

    }

    public void updateCompeitionList() {
        // update the list view by removing all the items and adding them all
        // again
        
        // ObservableList<Competition<?>> oldList = this.getItems();
        // this.getItems().clear();
        // Globals.competitions = oldList ;
        // this.setItems(Globals.competitions);
        
        Competition<?> c = Globals.currentCompetition; 
        this.setItems(null) ;
        this.setItems(Globals.competitions);
        Globals.currentCompetition = c ;
    }
}
