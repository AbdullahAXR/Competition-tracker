import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import java.util.*;
import org.apache.poi.ss.usermodel.BuiltinFormats;


public class Test {
	public static void main(String[] args) throws Exception {
        testCompetitionManger();
        // testObservableList();
        // launch();
	}

    public static void testStyle() throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("data.xlsx"));
        // XSSFDataFormat format = workbook.createDataFormat();

		XSSFSheet sheet = workbook.getSheetAt(1);
		XSSFRow row = sheet.getRow(5);

		XSSFCell sampleCell = row.getCell(1);
        XSSFCellStyle sampleStyle = sampleCell.getCellStyle();

        System.out.println("sample:");
        showStyle(sampleCell.getCellStyle());

        XSSFCell defaultCell = workbook.createSheet().createRow(19).createCell(20);
        XSSFCellStyle defaultStyle = defaultCell.getCellStyle();
        System.out.println("default:");
        showStyle(defaultStyle);
        // System.out.println(BuiltinFormats.getBuiltinFormat(1));
        // sampleStyle.setDataFormat(BuiltinFormats.getBuiltinFormat(1));
        // cell.setCellStyle(sampleStyle);
        // System.out.println(cell.toString());
        // System.out.println(cs.getDataFormatString());
        // System.out.println(cell.toString());
        // System.out.println(cell.getCellStyle().getShrinkToFit());
    }

    public static void showStyle(XSSFCellStyle cs){
        System.out.println("cs.getAlignment = "+cs.getAlignment());
        System.out.println("cs.getBorderBottom = "+cs.getBorderBottom());
        System.out.println("cs.getBorderLeft = "+cs.getBorderLeft());
        System.out.println("cs.getBorderRight = "+cs.getBorderRight());
        System.out.println("cs.getBorderTop = "+cs.getBorderTop());
        System.out.println("cs.getBottomBorderColor = "+cs.getBottomBorderColor());
        System.out.println("cs.getDataFormat = "+cs.getDataFormat());
        System.out.println("cs.getDataFormatString = "+cs.getDataFormatString());
        System.out.println("cs.getFillBackgroundColor = "+cs.getFillBackgroundColor());
        System.out.println("cs.getFillBackgroundColorColor = "+cs.getFillBackgroundColorColor());
        System.out.println("cs.getFillForegroundColor = "+cs.getFillForegroundColor());
        System.out.println("cs.getFillForegroundColorColor = "+cs.getFillForegroundColorColor());
        System.out.println("cs.getFillPattern = "+cs.getFillPattern());
        System.out.println("cs.getFontIndex = "+cs.getFontIndex());
        System.out.println("cs.getFontIndexAsInt = "+cs.getFontIndexAsInt());
        System.out.println("cs.getFontIndex = "+cs.getFontIndex());
        System.out.println("cs.getHidden = "+cs.getHidden());
        System.out.println("cs.getIndention = "+cs.getIndention());
        System.out.println("cs.getIndex = "+cs.getIndex());
        System.out.println("cs.getLeftBorderColor = "+cs.getLeftBorderColor());
        System.out.println("cs.getLocked = "+cs.getLocked());
        System.out.println("cs.getQuotePrefixed = "+cs.getQuotePrefixed());
        System.out.println("cs.getRightBorderColor = "+cs.getRightBorderColor());
        System.out.println("cs.getRotation = "+cs.getRotation());
        System.out.println("cs.getShrinkToFit = "+cs.getShrinkToFit());
        System.out.println("cs.getTopBorderColor = "+cs.getTopBorderColor());
        System.out.println("cs.getVerticalAlignment = "+cs.getVerticalAlignment());
        System.out.println("cs.getWrapText = "+cs.getWrapText());
        System.out.println("cs.getIndex = "+cs.getIndex());
        System.out.println("cs.getDataFormat = "+cs.getDataFormat());
        System.out.println("cs.getDataFormatString = "+cs.getDataFormatString());
        System.out.println("cs.getFontIndex = "+cs.getFontIndex());
        System.out.println("cs.getFontIndexAsInt = "+cs.getFontIndexAsInt());
        System.out.println("cs.getFontIndex = "+cs.getFontIndex());
        System.out.println("cs.getHidden = "+cs.getHidden());
        System.out.println("cs.getLocked = "+cs.getLocked());
        System.out.println("cs.getQuotePrefixed = "+cs.getQuotePrefixed());
        System.out.println("cs.getAlignment = "+cs.getAlignment());
        System.out.println("cs.getWrapText = "+cs.getWrapText());
        System.out.println("cs.getVerticalAlignment = "+cs.getVerticalAlignment());
        System.out.println("cs.getRotation = "+cs.getRotation());
        System.out.println("cs.getIndention = "+cs.getIndention());
        System.out.println("cs.getBorderLeft = "+cs.getBorderLeft());
        System.out.println("cs.getBorderRight = "+cs.getBorderRight());
        System.out.println("cs.getBorderTop = "+cs.getBorderTop());
        System.out.println("cs.getBorderBottom = "+cs.getBorderBottom());
        System.out.println("cs.getLeftBorderColor = "+cs.getLeftBorderColor());
        System.out.println("cs.getRightBorderColor = "+cs.getRightBorderColor());
        System.out.println("cs.getTopBorderColor = "+cs.getTopBorderColor());
        System.out.println("cs.getBottomBorderColor = "+cs.getBottomBorderColor());
        System.out.println("cs.getFillPattern = "+cs.getFillPattern());
        System.out.println("cs.getFillBackgroundColor = "+cs.getFillBackgroundColor());
        System.out.println("cs.getFillBackgroundColorColor = "+cs.getFillBackgroundColorColor());
        System.out.println("cs.getFillForegroundColor = "+cs.getFillForegroundColor());
        System.out.println("cs.getFillForegroundColorColor = "+cs.getFillForegroundColorColor());
        System.out.println("cs.getShrinkToFit = "+cs.getShrinkToFit());
    }

	public static void testExcel() throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("data.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		System.out.println(cell.getStringCellValue());

	}

    public static void testId() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("data.xlsx"));
        System.out.println(workbook.createDataFormat().getFormat("0"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(5);
		XSSFCell cell = row.getCell(1);
        XSSFCellStyle cs = cell.getCellStyle();
        cs.setDataFormat(1);
        cell.setCellStyle(cs);
        System.out.println(cell.getNumericCellValue());
        // System.out.println((long)(cell.getNumericCellValue()));
    }

    public static void testObservableList(){
        ArrayList<Competition<?>> comps = new ArrayList<>();
        // comps.add(new StudentCompetition("my comp", "https;asd;fasd", new Date(), new LinkedHashMap<Student, String));
        comps.add(exampleCompetition());
        ObservableList<Competition<?>> l = FXCollections.observableArrayList(comps);

        System.out.println(l.isEmpty());
        comps.remove(0);
        System.out.println(l.isEmpty());

    }

    public static void testListView(){

    }

    public static StudentCompetition exampleCompetition(){
        LinkedHashMap <Student, String> lhm =  new LinkedHashMap<Student, String>();
        lhm.put(new Student("30238531", "saher's friend", "MATH"), "1");
        lhm.put(new Student("11218731", "saher's other friend", "swe"), "2");
        lhm.put(new Student("50731591", "saher himself", "cs"), "-");
        return new StudentCompetition("saher's cool competition", "www.saher.com", new Date(), lhm);
    }

    public static void testCompetitionManger() throws Exception {
        CompetitionManager cm = new CompetitionManager();
        ArrayList<Competition<?>> comps =  cm.readCompetitions();
        System.out.println(comps);
        System.out.println(comps.size());
        comps.add(exampleCompetition());
        cm.writeCompetitions(comps);
    }
}
