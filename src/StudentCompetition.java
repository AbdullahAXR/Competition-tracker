import java.util.*;
public class StudentCompetition extends Competition {

    // The results of the competition.
    // We use <Student, String> instead of <Student, Integer> because the
    // competition might use Strings like "gold" or "silver".
    HashMap<Student, String> results;

    StudentCompetition(String name, String link, Date date){
        this.results = new HashMap<Student,String>();
        this.name = name;
        this.link = link;
        this.date = date;
        this.type = CompetitionType.INDIVISUAL;
    }
    
}
