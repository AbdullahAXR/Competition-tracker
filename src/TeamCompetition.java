import java.util.*;
public class TeamCompetition extends Competition<Team> {

    TeamCompetition(String name, String link, Date date, LinkedHashMap<Team,String> results) {
        super(name, link, date, results);
    }

    TeamCompetition(String name, String link, Date date) {
        this(name, link, date, new LinkedHashMap<Team, String>());
    }

    // just an alias
    public Set<Team> getTeams(){
        return this.getParticipants();
    }
}
