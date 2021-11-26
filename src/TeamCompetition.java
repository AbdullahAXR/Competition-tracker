import java.util.*;
public class TeamCompetition extends Competition {

    // The results of the competition.
    // We use <Team, String> instead of <Team, Integer> because the
    // competition might use Strings like "gold" or "silver".
    LinkedHashMap<Team, String> results;

    TeamCompetition() {
    }

    TeamCompetition(String name, String link, Date date, LinkedHashMap<Team, String> results){
        this.name = name;
        this.date = date;
        this.link = link;
        this.results = results;
    }
}
