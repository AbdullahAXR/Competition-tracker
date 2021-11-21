import java.util.*;

public abstract class Competition {
    private String name;
    private String link;
    private Date date;
    private HashMap<Participant, String> results;
    private Type type;

    public enum Type {
        INDIVIDUAL, TEAM,
    }

    public String getName(){
        return name;
    }
    // a Setters so we can edit the name and link
    public void setName(String name){
	 this.name = name;
    }

    public void setLink(String link){
	 this.link = link;
    }
    public String getLink(){
        return link;
    }

    // return a *clone* of the date object instead of the actual date.
    // If you want to change the actual date, then use setDate(Date newDate)
    public Date getDate(){
        return (Date) date.clone();
    }

    public void setDate(Date newdate){
        this.date = newdate;
    }

    public void put(Participant participant, String rank){
        results.put(participant, rank);
    }


}
