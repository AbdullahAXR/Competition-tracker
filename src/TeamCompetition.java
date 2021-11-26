import java.util.*;
public class TeamCompetition extends Competition {

    // The results of the competition.
    // We use <Team, String> instead of <Team, Integer> because the
    // competition might use Strings like "gold" or "silver".
    LinkedHashMap<Team, String> results;

    TeamCompetition() {
        this.results = new LinkedHashMap<Team,String>();
        this.type = Type.TEAM;
    }
}
