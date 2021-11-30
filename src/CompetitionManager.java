import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.*;

// TODO: dont forget to set the sheet name 
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
        // TeamCompetition tc = (TeamCompetition) cm.competitions.get(0);
        // cm.writeCompetitions();
    }

    public void add(Competition<?> c){
        competitions.add(c);
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

        // TODO change this to this.size()
        for(int i = 0; i < competitions.size(); i++){
            c = competitions.get(i);
            String sheetName = c.getName();
            sheet = dataWorkbook.createSheet(sheetName);
            dataWorkbook.setSheetName(i, sheetName);
            CompetitionSheetBuilder csb = new CompetitionSheetBuilder(sheet, c);
            csb.buildSheet();
        }

        dataFile.delete();
        dataFile.createNewFile();
        dataWorkbook.write(new FileOutputStream(dataFile));
    }

}
