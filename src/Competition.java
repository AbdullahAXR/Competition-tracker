import java.util.*;

public abstract class Competition {
    protected String name;
    protected String link;
    protected Date date;
    public LinkedHashMap<Participant, String> results;

    protected Type type; // TODO: Is this really necessary? You can always just use the instanceof keywrod

    public enum Type {
        INDIVIDUAL, TEAM,
    }

    public String getName() {
        return name;
    }
    // a Setters so we can edit the name and link
    public void setName(String name) {
	 this.name = name;
    }

    public void setLink(String link) {
	 this.link = link;
    }

    public String getLink() {
        return link;
    }

    // return a *clone* of the date object instead of the actual date.
    // If you want to change the actual date, then use setDate(Date newDate)
    public Date getDate() {
        return (Date) date.clone();
    }

    public Competition.Type getType() {
        return type;
    }

    public void setDate(Date newdate) {
        this.date = newdate;
    }

    public String getResult(Participant p) {
        return results.get(p);
    }

    public boolean containsParticipant(Participant p) {
        return results.containsKey(p);
    }

    public boolean containsResult(String rank) {
        return results.containsValue(rank);
    }

    public void put(Participant participant, String rank) {
        results.put(participant, rank);
    }

    public void putIfAbsent(Participant p, String rank) {
        results.putIfAbsent(p, rank);
    }

    public int size() {
        return results.size();
    }

	public boolean isEmpty() {
		return results.isEmpty();
	}

	public void remove(Participant p) {
		results.remove(p);
	}

    @Override
    public String toString(){
        String s = "Name: "+this.getName()+"\n"+
                   "Link: "+this.getLink()+"\n"+
                   "Date: "+this.getDate()+"\n"; // you probably want to change the date format

        for(Participant p : results.keySet()){
            s += p.toString()+"\n";
        }

        return s;
    }

}
