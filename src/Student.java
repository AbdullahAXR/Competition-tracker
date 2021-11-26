public class Student extends Participant {
    private String id;
    private String major;

    Student(String id, String name, String major) {
        super(name);
        this.id = id;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString(){
        return "ID: "+this.getId()+"\n"+
               "Name: "+this.getName()+"\n"+
               "Major: "+this.getMajor()+"\n";
    }

}
