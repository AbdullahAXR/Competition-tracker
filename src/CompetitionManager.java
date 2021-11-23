import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

public class CompetitionManager {

    private ArrayList<Competition> competitions = new ArrayList<Competition>();
    private final File dataFile = new File("data.xslx");
    private XSSFWorkbook dataWorkbook; 

    CompetitionManager(File dataFile) throws InvalidFormatException, IOException{
        // TODO: basically we want to load the avaialbe competition from
        // readCompetition().
	}

    private void intitilizeData() throws InvalidFormatException, IOException {

        if (!dataFile.exists()) {
            dataFile.createNewFile(); // create an empty file
            dataWorkbook = new XSSFWorkbook(); // create an empty workbook
        } else {
            dataWorkbook = new XSSFWorkbook(dataFile); // intitilize the dataWorkbook by reading the dataFile
        }
    }


    /* 2021-11-18 Thu 15:04: Saher
    /* TODO: Not complete. This can be completed by nesting method (through
     * inner classes), but that's not readable, so I will create a new class
    private Competition readCompetitionFromSheet(XSSFSheet sheet) {

        //   This is how the basic info of a competition in the  excel sheet looks like
        //
        //   |-----------------|--------------------------------------------------------------------
        // 1 | Competition name: some name
        //   |-----------------|--------------------------------------------------------------------
        // 2 | Competition link: some link
        //   |-----------------|--------------------------------------------------------------------
        // 3 | Competition date: some date
        //   |-----------------|--------------------------------------------------------------------


        // Therefore, the index of the basic info is:
        final int NAME_ROW = 0;
        final int LINK_ROW = 1;
        final int DATE_ROW = 2;
        final int CELL = 1;

        // and they can be extracted through:
        String name = Sheet.getRow(NAME_ROW).getCell(CELL).getStringCellValue();
        String link = Sheet.getRow(LINK_ROW).getCell(CELL).getStringCellValue();
        String date = Sheet.getRow(DATE_ROW).getCell(CELL).getStringCellValue();

        // now we need to determine the competition type
    }
    */

    // TODO: incomplete
    private void readCompetitions() {

        int numberOfCompetitions = dataWorkbook.getNumberOfSheets();

        // extract the basic info
        for(int i = 0; i < numberOfCompetitions; i++) {
            XSSFSheet competitionSheet = dataWorkbook.getSheetAt(i);

        }

    }

}
