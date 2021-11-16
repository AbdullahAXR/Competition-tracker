import java.util.*;
public class Team {
    private String name;

    // A team is just a number of students under one name
    private ArrayList<Student> members;

    Team(){
        this.members = new ArrayList<Student>();
    }

    public String getName(){
        return name;
    }

}
