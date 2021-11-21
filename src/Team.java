import java.util.*;
public class Team extends Participant {

    // A team is just a number of students under one name
    private ArrayList<Student> students;

    Team(String name){
        super(name);
        this.students = new ArrayList<Student>();
    }

    Team(String name, ArrayList<Student> students){
        super(name);
        this.students = students;
    }

    // i see it more convenent to have a setter for Team name
    public void setName(String name){
	this.name = name;
    }
}
