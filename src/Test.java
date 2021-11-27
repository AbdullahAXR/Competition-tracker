import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.*;

public class Test {
	public static void main(String[] args) throws FileNotFoundException, IOException {
        testTeam();
	}

    public static void testTeam(){
        Team t = new Team("");

        t.add(new Student());
        t.add(new Student());
        t.add(new Student());
        t.add(new Student());

        TeamCompetition tc = new TeamCompetition("","",new Date(), new LinkedHashMap<Team,String>());
        
        tc.put(t, "1");

        tc.getStudents().forEach(System.out::println);

    }

	public static void testExcel() throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("data.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		System.out.println(cell.getStringCellValue());
	}
}
