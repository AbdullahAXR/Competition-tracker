import java.util.*;

public abstract class Competition {
    String name;
    String link;
    Date date;
    HashMap<Participant, String> results;

    public String getName(){
        return name;
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

    public String getRank(Participant participant){
        return participant;
    }

    public 

}
