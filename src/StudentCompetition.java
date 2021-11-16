import java.util.*;
public class StudentCompetition extends Competition {

    // The results of the competition.
    // We use <Student, String> instead of <Student, Integer> because the
    // competition might use Strings like "gold" or "silver".
    HashMap<Student, String> results;

    StudentCompetition(){
        this.results = new HashMap<Student,String>();
    }
    
}
