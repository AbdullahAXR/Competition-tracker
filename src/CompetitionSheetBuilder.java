import java.io.File;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;
import java.io.IOException;

public class CompetitionWorkbookBuilder {
    XSSFWorkbook wb = new XSSFWorkbook();

    CompetitionSheetBuilder(){
    }

    public XSSFWorkbook getWorkbook(){
        return wb;
    }

    public void addCompetition(Competition<?> c){
        XSSFSheet sheet = wb.createSheet();

    }

    // inner class
    public CompetitionSheetBuilder {

    }

    private static void addCompetitionInfo(XSSFSheet sheet, Competition<?> c){
        sheet.createRow(0);
        sheet.getRow(0).getCell(0).setCellValue(c.getName());

        sheet.createRow(1);
        sheet.getRow(1).getCell(1).setCellValue(c.getLink());

        sheet.createRow(2);
        sheet.getRow(2).getCell(2).setCellValue(c.getDate());
    }

    private static void addCompetitionPreRow
}
