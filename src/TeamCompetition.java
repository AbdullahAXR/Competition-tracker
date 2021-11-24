import java.util.*;
public class TeamCompetition extends Competition {

    // The results of the competition.
    // We use <Team, String> instead of <Team, Integer> because the
    // competition might use Strings like "gold" or "silver".
    HashMap<Team, String> results;

    TeamCompetition() {
        this.results = new HashMap<Team,String>();
        this.type = Type.TEAM;
    }
}
