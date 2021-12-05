import java.util.Date;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Globals {
    public static CompetitionManager MANAGER = new CompetitionManager();
    public static ObservableList<Competition<?>> competitions = FXCollections.observableArrayList(MANAGER.readCompetitions());
    public static Competition<?> currentCompetition = null; // CompetitionList will update this
    public final static int SPACING = 5; // used by [HV]Box
    public final static Date NOW = new Date();
}

