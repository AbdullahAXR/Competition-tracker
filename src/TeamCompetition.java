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

    public Set<Student> getStudents(){
        Set<Student> students = new LinkedHashSet<Student>();
        Set<Team> teams = results.keySet();

        for(Team t: teams){
            for(Student s : t){
                students.add(s);
            }
        }

        return students;

    }
}
