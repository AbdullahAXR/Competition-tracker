import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

// TODO: dont forget to set the sheet name 
public class CompetitionManager {

    public ArrayList<Competition<?>> competitions = new ArrayList<>();
    private final File dataFile = new File("data.xlsx");
    private XSSFWorkbook dataWorkbook; 

    CompetitionManager() throws InvalidFormatException, IOException {
        this.dataWorkbook = new XSSFWorkbook(dataFile);
        this.readCompetitions();
	}

    public static void main(String[] args) throws Exception {
        CompetitionManager cm = new CompetitionManager();
        System.out.println(cm.competitions.size());
        // TeamCompetition tc = (TeamCompetition) cm.competitions.get(0);
    }

    private void readCompetitions() throws IOException, InvalidFormatException {
        XSSFSheet sheet;

        for(int i = 0; i < dataWorkbook.getNumberOfSheets(); i++){
            sheet = dataWorkbook.getSheetAt(i);
            Competition<?> c = CompetitionSheetParser.getCompetition(sheet);
            competitions.add(c);
        }
    }

    // private void writeCompetitions() {
    //     XSSFSheet sheet;

    //     for(int i = 0; i < competitions.size(); i++){
    //     }
    // }

}
