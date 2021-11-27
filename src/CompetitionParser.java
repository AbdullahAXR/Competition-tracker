import java.io.File;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;
import java.io.IOException;

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

        TeamCompetition c0 = (TeamCompetition) getCompetition(sheet0);
        StudentCompetition c1 = (StudentCompetition) getCompetition(sheet1);

        System.out.println(c0.getName());
        System.out.println(c0.getLink());
        System.out.println(c0.getDate());
        System.out.println(c0.results);

        System.out.println(c1.getName());
        System.out.println(c1.getLink());
        System.out.println(c1.getDate());
        System.out.println(c1.results);


        // for(Student s : c.results.keySet()){
        //     System.out.println(s.getId());
        // }

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

    private static LinkedHashMap<Team, String> getTeamCompetitionResults(XSSFSheet sheet){
        LinkedHashMap<Team, String> results = new LinkedHashMap<Team, String>();

        int i = FIRST_PARTICIPANT_ROW;
        XSSFRow row = sheet.getRow(i);

        while(row != null){
            String name = row.getCell(TEAM_CELL).toString();

            Set<Team> teams = results.keySet();

            // if you dislike streams, then this is the imperative version
            // (untested)

            // Team team = null;
            // for(Team t: teams){
            //     if(t.getName() == name){
            //         team = t;
            //         break;
            //     }
            // }

            Team team = teams.stream()
                    .filter(t -> t.getName() == name)
                    .findFirst()
                    .orElse(null);

            if(team == null){
                team = new Team(name);
                String rank = row.getCell(TEAM_RANK).toString();
                results.put(team, rank);
            }

            team.add(getStudent(row));

            i++;
            row = sheet.getRow(i);
        }

        return results;
    }


    // notice that some code is shared with getStudentCompetition. Maybe write a
    // general getcompetition() method?
    private static TeamCompetition getTeamCompetition(XSSFSheet sheet){
        String name = getName(sheet);
        Date   date = getDate(sheet);
        String link = getLink(sheet);

        LinkedHashMap<Team, String> results = getTeamCompetitionResults(sheet);

        return new TeamCompetition(name, link, date, results);
    }

    public static Competition getCompetition(XSSFSheet sheet){
        Competition c;
        Competition.Type type =  getType(sheet);

        if(type == Competition.Type.INDIVIDUAL)
            c = getStudentCompetition(sheet);
        else
            c = getTeamCompetition(sheet);

        return c;
    }

    public static XSSFSheet getCompetitionSheet(Competition c) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, c.getName());
        workbook.close();

        sheet.createRow(0);
        sheet.getRow(0).getCell(0).setCellValue(c.getName());

        sheet.createRow(1);
        sheet.getRow(1).getCell(1).setCellValue(c.getLink());

        sheet.createRow(2);
        sheet.getRow(2).getCell(2).setCellValue(c.getDate());

        return sheet;
    }

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

