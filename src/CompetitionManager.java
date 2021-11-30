import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Date;
import java.lang.IllegalArgumentException;

public class CompetitionManager {

    public ArrayList<Competition<?>> competitions = new ArrayList<>();
    private final File dataFile = new File("temp.xlsx");
    private XSSFWorkbook dataWorkbook; 

    CompetitionManager() throws InvalidFormatException, IOException {
        this.dataWorkbook = new XSSFWorkbook(dataFile);
        this.readCompetitions();
	}
    public static void main(String[] args) throws Exception {
        // CompetitionManager cm = new CompetitionManager();
        // System.out.println(cm.competitions.size());

        // LinkedHashMap<Student, String> lhm =  new LinkedHashMap<Student, String>();
        // lhm.put(new Student("20238531", "saher's friend", "MATH"), "1");
        // lhm.put(new Student("20238531", "saher's other friend", "swe"), "2");
        // lhm.put(new Student("20238531", "saher himself", "swe"), "-");
        // cm.add(new StudentCompetition("saher's cool competition", "www.saher.com", new Date(), lhm));
        // // TeamCompetition tc = (TeamCompetition) cm.competitions.get(0);
        // cm.writeCompetitions();

    }

    private void readCompetitions() throws IOException, InvalidFormatException {
        XSSFSheet sheet;

        for(int i = 0; i < dataWorkbook.getNumberOfSheets(); i++){
            sheet = dataWorkbook.getSheetAt(i);
            Competition<?> c = CompetitionSheetParser.getCompetition(sheet);
            competitions.add(c);
        }
        dataWorkbook.close();
    }

    public void writeCompetitions() throws Exception {
        dataWorkbook = new XSSFWorkbook();
        XSSFSheet sheet;
        Competition<?> c;

        for(int i = 0; i < this.size(); i++){
            c = competitions.get(i);
            String sheetName = c.getName();

            try {
            sheet = dataWorkbook.createSheet(sheetName);
            dataWorkbook.setSheetName(i, sheetName);
            CompetitionSheetBuilder csb = new CompetitionSheetBuilder(sheet, c);
            csb.buildSheet();
            }
            catch(IllegalArgumentException e) {
                // ignore competition with the same title 
                // TODO: perhaps we should let the GUI classes handle duplicate
                // competition entry
            }

        }

        dataWorkbook.write(new FileOutputStream(dataFile));
    }

	// Replaces a question at index
	public void set(int i, Competition<?> c) {
		competitions.set(i, c);
	}

	// Adds a question to the list
	public void add(Competition<?> c) {
		competitions.add(c);
	}

	public boolean isEmpty() {
		return competitions.isEmpty();
	}

	public int size() {
		return competitions.size();
	}

	public Competition<?> get(int i) {
		return competitions.get(i);
	}

	public void remove(int i) {
		competitions.remove(i);
	}

	public void remove(Competition<?> c) {
		competitions.remove(c);
	}
	
	public int indexOf(Competition<?> c) {
		return competitions.indexOf(c);
	}
}

