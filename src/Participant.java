public abstract class Participant {
    protected String name;
    // empty class

    Participant(String name) {
        this.name = name;
    }

    Participant() {
        super();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString(){
        return this.getName();
    }

}
