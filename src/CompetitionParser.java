import java.io.File;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;

public final class CompetitionParser {

    // private final XSSFSheet SHEET;

    // basic info location
    final static int NAME_ROW = 0;
    final static int LINK_ROW = 1;
    final static int DATE_ROW = 2;
    final static int CELL = 1;

    // competition type information
    final static int TYPE_ROW = 4;
    final static int TYPE_CELL = 4;

    // team name
    final static int TEAM_CELL = 5;

    // participant location
    final static int FIRST_PARTICIPANT_ROW = 5;

    // rank location
    final static int INDIVIDUAL_RANK = 4;
    final static int TEAM_RANK = 6;

    // student location
    final static int ID_CELL = 1;
    final static int NAME_CELL = 2;
    final static int MAJOR_CELL = 3;

    // TODO: Delete later. This is for testing purposes
    public static void main(String[] args) throws Exception {
        test();
    }

    // TODO: Delete later. This is for testing purposes
    public static void test() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new File("data.xlsx"));
		XSSFSheet sheet0 = workbook.getSheetAt(0);
		XSSFSheet sheet1 = workbook.getSheetAt(1);

        StudentCompetition c = getStudentCompetition(sheet1);
        System.out.println(c.getName());
        System.out.println(c.getLink());
        System.out.println(c.getDate());
        System.out.println(c.results);

        for(Student s : c.results.keySet()){
            System.out.println(s.getId());
        }

    }

    ///// TODO: I bet there's a more elegant way to do this.
    private static String getName(XSSFSheet sheet){
        return sheet.getRow(NAME_ROW).getCell(CELL).toString();
    }

    private static Date getDate(XSSFSheet sheet){
        return sheet.getRow(DATE_ROW).getCell(CELL).getDateCellValue();
    }

    private static String getLink(XSSFSheet sheet){
        return sheet.getRow(LINK_ROW).getCell(CELL).toString();
    }
    /////

    // get type of the competition
    public static Competition.Type getType(XSSFSheet sheet) {
        String team = sheet.getRow(TYPE_ROW).getCell(TYPE_CELL).toString();

       Competition.Type type;
        if(team.equals("team")) {
            type = Competition.Type.TEAM;
        } else
            type = Competition.Type.INDIVIDUAL;

        return type;
    }

    // get a student from a row
    private static Student getStudent(XSSFRow row) {
        // for some reason the id cell is numeric. I think this is a mistake
        // with the project files. We can workaround it by using toString()
        String id    = row.getCell(ID_CELL).toString();
        String name  = row.getCell(NAME_CELL).toString();
        String major = row.getCell(MAJOR_CELL).toString();

        return new Student(id, name, major); // everytime, we create a new student...
    }

    private static LinkedHashMap<Student, String> getStudentCompetitionResults(XSSFSheet sheet){
        LinkedHashMap<Student, String> results = new LinkedHashMap<Student, String>();

        int i = FIRST_PARTICIPANT_ROW;
        XSSFRow row = sheet.getRow(i);
        while(row != null){
            Student student = getStudent(row);

            // a rank cell can either numeric or string. This is most likely an
            // issue with the provided excel file. However, we can overcome it
            // by querying the cell type and converting it accordingly using the
            // getCellType() method. Another appraoch is to use toString().
            // However I'm not sure how that behaves
            String rank = row.getCell(INDIVIDUAL_RANK).toString();
            results.put(student, rank);
            i++;
            row = sheet.getRow(i);
        }

        return results;
    }

    private static StudentCompetition getStudentCompetition(XSSFSheet sheet){
        String name = getName(sheet);
        Date   date = getDate(sheet);
        String link = getLink(sheet);

        LinkedHashMap<Student, String> results = getStudentCompetitionResults(sheet);

        return new StudentCompetition(name, link, date, results);
    }


    // get the basic info for the competition
    // public static void getCompetition(XSSFSheet sheet) throws Exception {
    //     String name = sheet.getRow(NAME_ROW).getCell(CELL).getStringCellValue();
    //     String link = sheet.getRow(LINK_ROW).getCell(CELL).getStringCellValue();
    //     Date date = sheet.getRow(DATE_ROW).getCell(CELL).getDateCellValue();
    //         // SimpleDateFormat("dd-MMM-yyyy").parse(sheet.getRow(DATE_ROW).getCell(CELL).getDateCellValue());

    //     Competition.Type type = getType(sheet);

    //     System.out.println("name: " + name);
    //     System.out.println("link: " + link);
    //     System.out.println("date: " + date);
    //     System.out.println("type: " + type);

    //     if(type == Competition.Type.INDIVIDUAL){
    //         StudentCompetition result = new StudentCompetition(name, link, date);

    //     }
    //     else {
    //         TeamCompetition result = new TeamCompetition();
    //     }

    //     // return result;
    // }

    // // reuturn an array of all students in the SHEET
    // private static ArrayList<Student> getStudents(XSSFSheet sheet) {
    //     ArrayList<Student> students = new ArrayList<Student>();

    //     int i = FIRST_PARTICIPANT_ROW;
    //     XSSFRow row = sheet.getRow(i);
    //     while(row != null) { // if the first cell is non-blank
    //         students.add(getStudent(row));
    //         i++;
    //         row = sheet.getRow(i);
    //     }

    //     return students;
    // }

    // private LinkedHashMap<Participant, String> getCompetitionResults() {
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
//

// 1. let s be a sheet
// 2. if s is team based, do:
//  1. get the team name
//  2. create the team if its not there already
//  3. get the rank
//  4. add the team with the rank
//  5. fill the team with students belonging to the team
//  5. add

