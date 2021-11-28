import java.util.*;

// the only good reason we define this class is just to be able to use the
// isinstanceof keyword (instead of enums)
public class StudentCompetition extends Competition<Student> {

    StudentCompetition(String name, String link, Date date, LinkedHashMap<Student,String> results) {
        super(name, link, date, results);
    }
    

    StudentCompetition(String name, String link, Date date) {
        this(name, link, date, new LinkedHashMap<Student, String>());
    }

    // just an alias
    @Override
    public Set<Student> getStudents(){
        return this.getParticipants();
    }
}
