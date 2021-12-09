import java.util.*;
public class Team extends Participant implements Iterable<Student> {

    // A team is just a number of students under one name
    private ArrayList<Student> students;

    Team(String name) {
        super(name);
        this.students = new ArrayList<Student>();
    }

    Team(String name, ArrayList<Student> students) {
        super(name);
        this.students = students;
    }

	// Replaces a question at index
	public void set(int i, Student s) {
		students.set(i, s);
	}

	// Adds a student to the list
	public void add(Student s) {
		students.add(s);
	}

	public boolean isEmpty() {
		return students.isEmpty();
	}

	public int size() {
		return students.size();
	}

	public Student get(int i) {
		return students.get(i);
	}

	public void remove(int i) {
		students.remove(i);
	}

	public void remove(Student s) {
		students.remove(s);
	}

	public int indexOf(Student s) {
		return students.indexOf(s);
	}

    @Override
    public String toString(){
        String string;
        string = "Team: "+this.getName()+"\n";

        for(Student s: students)
            string += s.toString();

        return string;
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    public HashSet<Student> getStudents(){
        return new LinkedHashSet<Student>(students);
    }

    @Override
    public String[] getEmails(){
        return students.stream()
            .map(s -> s.getEmail())
            .toArray(String[]::new);
    }
}
