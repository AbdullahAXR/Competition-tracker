import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

// TODO: make this static. It makes more sense as we really just need something
// to parase a sheet.
public class CompetitionSheet {

    private final XSSFSheet SHEET;

    // basic info location
    final static int NAME_ROW = 0;
    final static int LINK_ROW = 1;
    final static int DATE_ROW = 2;
    final static int CELL = 1;

    // participant location
    final int FIRST_PARTICIPANT_ROW = 5;

    CompetitionSheet(XSSFSheet sheet){
        this.SHEET = sheet;
    }

    // TODO: Delete later. This is for testing purposes
    public static void main(String[] args) throws Exception {
        test();
    }

    // TODO: Delete later. This is for testing purposes
    public static void test() throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook(new File("data.xlsx"));
		XSSFSheet sheet0 = workbook.getSheetAt(0);
		XSSFSheet sheet1 = workbook.getSheetAt(1);
        CompetitionSheet compSheet0 = new CompetitionSheet(sheet0); 
        CompetitionSheet compSheet1 = new CompetitionSheet(sheet1); 

        Competition.Type type0 = compSheet0.getType();
        Competition.Type type1 = compSheet1.getType();

        ArrayList<Student> students0 = compSheet0.getStudents();
        ArrayList<Student> students1 = compSheet1.getStudents();
        System.out.println(students1.get(0).getName());
    }

    // get type of the competition
    public Competition.Type getType(){
        String team = SHEET.getRow(4).getCell(4).getStringCellValue();

        if(team.equals("team")){
            return Competition.Type.TEAM;
        } else
            return Competition.Type.INDIVIDUAL;

    }

    // get the basic info for the competition
    // private Competition getCompetitionInfo(){
    //     String name = SHEET.getRow(NAME_ROW).getCell(CELL).getStringCellValue();
    //     String link = SHEET.getRow(LINK_ROW).getCell(CELL).getStringCellValue();
    //     // TODO this needs to be date, dont forget to add that later via a
    //     // static method that parses a string and produces a date
    //     String date = SHEET.getRow(DATE_ROW).getCell(CELL).getStringCellValue();
    // }

    // get a student from a row
    private Student getStudent(XSSFRow row){

        // for some reason the id cell is numeric. I think this is a mistake
        // with the project files. Anyhow there's a bug with this one
        String id = "" + row.getCell(1).getNumericCellValue();

        String name = row.getCell(2).getStringCellValue();
        String major = row.getCell(3).getStringCellValue();

        return new Student(id, name, major); // everytime, we create a new student...
    }

    // reuturn an array of all students in the SHEET
    private ArrayList<Student> getStudents(){
        ArrayList<Student> results = new ArrayList<Student>();

        // For blank cells a 0 is returned
        int i = FIRST_PARTICIPANT_ROW;
        XSSFRow row = SHEET.getRow(i);
        while(row != null){ // if the first cell is non-blank
            results.add(getStudent(row));
            i++;
            row = SHEET.getRow(i);
        }

        return results;
    }

    // TODO
    // private HashMap<Participant, String> getCompetitionResults(){
    // }

}

// random psuedo code (ignore)
// 1. let r be row
// 2. get all students from r
// 3. check type of sheet
//     3a. if individual, get ranks and leave
//     3b. if team, create team object, read rank:
//         - read team name
//         - add first student to team
//         - repeat until team name changes
//         - quit when null
