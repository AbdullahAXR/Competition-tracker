import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Competition<T extends Participant> implements Comparable<Competition<? extends Participant>>{
    protected String name;
    protected String link;
    protected Date date;
    
    ArrayList<CompetitionListener> listeners = new ArrayList<CompetitionListener>();
    
    // The results of the competition. We use <Student, String> instead of
    // <Student, Integer> because the competition might use Strings like "gold"
    // or "silver".
    protected LinkedHashMap<T, String> results;

    Competition(String name, String link, Date date, LinkedHashMap<T,String> results) {
        this.name = name;
        this.link = link;
        this.date = date;
        this.results = results;
    }

    Competition(){
        this("","",new Date(), new LinkedHashMap<T, String>());
    }

    public String getName() {
        return name;
    }
    
    
    public void addListener(CompetitionListener listener) {
        listeners.add(listener) ;
    }
    
    public void noitfyListeners() {
        for (CompetitionListener listener : listeners) {
            listener.onChange(this);
        }
    }
    
    // a Setters so we can edit the name and link
    public void setName(String name) {
	    this.name = name;
        
        noitfyListeners() ;
    }

    public void setLink(String link) {
	 this.link = link;
     
     noitfyListeners() ;
    }

    public String getLink() {
        return link;
    }

    // return a *clone* of the date object instead of the actual date.
    // If you want to change the actual date, then use setDate(Date newDate)
    public Date getDate() {
        return (Date) date.clone();
    }
    
    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public void setDate(Date newd) {
        Date oldd = (Date) this.getDate().clone(); // don't forget to clone
        this.date = newd;
        
        noitfyListeners() ;
        for (CompetitionListener listener : listeners) {
            listener.dateChanged(oldd, newd);
        }
    }

    @Override
    public int compareTo(Competition<? extends Participant> anotherCompetition){
        Date thisDate = this.getDate();
        Date anotherDate = anotherCompetition.getDate();

        return thisDate.compareTo(anotherDate);
    }

    public String getResult(T p) {
        return results.get(p);
    }

    public boolean containsParticipant(T p) {
        return results.containsKey(p);
    }

    public boolean containsResult(String rank) {
        return results.containsValue(rank);
    }

    public void put(T participant, String rank) {
        results.put(participant, rank);
    }

    public void putIfAbsent(T p, String rank) {
        results.putIfAbsent(p, rank);
    }

    public int size() {
        return results.size();
    }

	public boolean isEmpty() {
		return results.isEmpty();
	}

	public void remove(T p) {
		results.remove(p);
	}

    public Set<T> getParticipants(){

        return results.keySet();
    }

    public String toStringFull(){
        String s = "Name: "+this.getName()+"\n"+
                   "Link: "+this.getLink()+"\n"+
                   "Date: "+this.getDate()+"\n"; // you probably want to change the date format

        for(T p : results.keySet()){
            s += p.toString()+"\n";
        }

        return s;
    }

    public abstract Set<Student> getStudents();

    @Override
    public String toString(){
        return this.getName();
    }

    public boolean isDue(){
        return Competition.isDue(this.getDate());
    }

    public static boolean isDue(Date date){
        return date.compareTo(Globals.NOW) < 0; // should we use Globals.NOW here?
    }
}
