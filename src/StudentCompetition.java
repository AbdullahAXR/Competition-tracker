import java.util.*;
public class StudentCompetition extends Competition {

    // The results of the competition.
    // We use <Student, String> instead of <Student, Integer> because the
    // competition might use Strings like "gold" or "silver".
    LinkedHashMap<Student, String> results;

    StudentCompetition(String name, String link, Date date, LinkedHashMap<Student,String> results) {
        this.name = name;
        this.link = link;
        this.date = date;
        this.results = results;
        this.type = Type.INDIVIDUAL;
    }
    

    StudentCompetition(String name, String link, Date date) {
        this(name, link, date, new LinkedHashMap<Student, String>());
    }


    StudentCompetition() {
    }

}
