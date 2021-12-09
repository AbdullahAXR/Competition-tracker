public class Student extends Participant {
    private String id;
    private String major;

    Student(String id, String name, String major) {
        super(name);
        this.id = id;
        this.major = major;
    }

    Student() {
        super();
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

    public String getEmail(){
        return "s"+this.getId()+"@"+DOMAIN;
    }

    // an array with one element
    @Override
    public String[] getEmails(){
        return new String[] {this.getEmail()};
    }
}
