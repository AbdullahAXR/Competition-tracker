import java.util.Date;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Globals {
    public static CompetitionManager MANAGER = new CompetitionManager();
    public static ObservableList<Competition<?>> competitions = FXCollections.observableArrayList(MANAGER.readCompetitions());
    public static Competition<?> currentCompetition = competitions.stream().findFirst().orElse(null);
    public final static int SPACING = 5; // used by [HV]Box
    public final static Date NOW = new Date();   
    
    
    // public static void updateCompetitionsList() {
    //     // create new competitin
    //     Competition<?> newCompetition = new StudentCompetition("new competition", "www.new.com", new Date());
    //     competitions.add(newCompetition) ;
    //     competitions.remove(newCompetition);
    // }
    
}

