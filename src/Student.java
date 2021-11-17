public class Student extends Participant {
    private String id;
    private String major;

    Student(String name, String id, String major){
        super(name);
        this.id = id;
        this.major = major;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getMajor(){
        return major;
    }


}
