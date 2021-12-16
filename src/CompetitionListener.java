public interface CompetitionListener {
    void onChange(Competition<?> c);
    void dateChanged(Competition<?> oldc ,Competition<?> newc);
}
