import java.util.Date;

public interface CompetitionListener {
    void onChange(Competition<?> c);
    void dateChanged(Date oldd, Date newd);
}
