import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.*;

class p {}
class c extends p {}
class S {
    public Set<? extends p> set = new HashSet<>();

    public Set<? extends p> getSet(){
        return this.getSet();
    }


}

public class Test {
	public static void main(String[] args) throws FileNotFoundException, IOException {
        testGenerics();
	}

	public static void testExcel() throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("data.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		System.out.println(cell.getStringCellValue());
	}
}
