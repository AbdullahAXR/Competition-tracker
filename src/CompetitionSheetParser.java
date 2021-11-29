import java.io.File;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;
import java.io.IOException;

public final class CompetitionSheetParser {

    enum Type {
        TEAM,
        INDIVIDUAL
    }

    // TODO: Delete later. This is for testing purposes
    // public static void main(String[] args) throws Exception {
    //     test();
    // }

    // TODO: Delete later. This is for testing purposes
    public static void test() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new File("data.xlsx"));
		XSSFSheet sheet0 = workbook.getSheetAt(0);
		XSSFSheet sheet1 = workbook.getSheetAt(1);

        TeamCompetition c0 = (TeamCompetition) getCompetition(sheet0);
        StudentCompetition c1 = (StudentCompetition) getCompetition(sheet1);

        // System.out.println(c0.getName());
        // System.out.println(c0.getLink());
        // System.out.println(c0.getDate());
        // System.out.println(c0.results);

        // System.out.println(c1.getName());
        // System.out.println(c1.getLink());
        // System.out.println(c1.getDate());
        // System.out.println(c1.results);

        // for(Student s : c.results.keySet()){
        //     System.out.println(s.getId());
        // }

    }

    ///// TODO: I bet there's a more elegant way to do this.
    private static String getName(XSSFSheet sheet){
        return sheet.getRow(CompetitionSheetSpecs.NAME_ROW).getCell(CompetitionSheetSpecs.CELL).toString();
    }

    private static Date getDate(XSSFSheet sheet){
        return sheet.getRow(CompetitionSheetSpecs.DATE_ROW).getCell(CompetitionSheetSpecs.CELL).getDateCellValue();
    }

    private static String getLink(XSSFSheet sheet){
        return sheet.getRow(CompetitionSheetSpecs.LINK_ROW).getCell(CompetitionSheetSpecs.CELL).toString();
    }
    /////

    // get type of the competition
    public static Type getType(XSSFSheet sheet) {
        String team = sheet.getRow(CompetitionSheetSpecs.TYPE_ROW).getCell(CompetitionSheetSpecs.TYPE_CELL).toString();

       Type type;
        if(team.equals("team")) {
            type = Type.TEAM;
        } else
            type = Type.INDIVIDUAL;

        return type;
    }

    // get a student from a row
    private static Student getStudent(XSSFRow row) {
        // for some reason the id CompetitionSheetSpecs.cell is numeric. I think this is a mistake
        // with the project files. We can workaround it by using toString()
        // System.out.println(row.getCell(CompetitionSheetSpecs.ID_CELL));
        String id    = row.getCell(CompetitionSheetSpecs.ID_CELL).toString();
        String name  = row.getCell(CompetitionSheetSpecs.NAME_CELL).toString();
        String major = row.getCell(CompetitionSheetSpecs.MAJOR_CELL).toString();

        return new Student(id, name, major); // everytime, we create a new student...
    }

    private static LinkedHashMap<Student, String> getStudentCompetitionResults(XSSFSheet sheet){
        LinkedHashMap<Student, String> results = new LinkedHashMap<Student, String>();

        int i = CompetitionSheetSpecs.FIRST_PARTICIPANT_ROW;
        XSSFRow row = sheet.getRow(i);
        while(row != null){
            Student student = getStudent(row);

            // a rank CompetitionSheetSpecs.cell can either numeric or string. This is most likely an
            // issue with the provided excel file. However, we can overcome it
            // by querying the CompetitionSheetSpecs.cell type and converting it accordingly using the
            // getCellType() method. Another appraoch is to use toString().
            // However I'm not sure how that behaves
            String rank = row.getCell(CompetitionSheetSpecs.INDIVIDUAL_RANK).toString();
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

        int i = CompetitionSheetSpecs.FIRST_PARTICIPANT_ROW;
        XSSFRow row = sheet.getRow(i);

        while(row != null){
            String name = row.getCell(CompetitionSheetSpecs.TEAM_NAME).toString();

            Set<Team> teams = results.keySet();

            // functional appraoch:
            /* 
               Team team = teams.stream()
                     .filter(t -> t.getName() == name)
                     .findFirst()
                     .orElse(null);
            */

            Team team = null;
            for(Team t: teams){
                if(t.getName() == name){
                    team = t;
                    break;
                }
            }

            if(team == null){
                team = new Team(name);
                String rank = row.getCell(CompetitionSheetSpecs.TEAM_RANK).toString();
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

    public static Competition<?> getCompetition(XSSFSheet sheet){
        Competition<?> c;
        Type type =  getType(sheet);

        if(type == Type.INDIVIDUAL)
            c = getStudentCompetition(sheet);
        else
            c = getTeamCompetition(sheet);

        return c;
    }

    public static XSSFSheet getCompetitionSheet(Competition<?> c) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, c.getName());
        workbook.close();

        addBasicInfo(sheet, c);
        addPreRow(sheet, c);

        return sheet;
    }

    public static void addBasicInfo(XSSFSheet sheet, Competition<?> c){
        sheet.createRow(0);
        sheet.getRow(0).getCell(0).setCellValue(c.getName());

        sheet.createRow(1);
        sheet.getRow(1).getCell(1).setCellValue(c.getLink());

        sheet.createRow(2);
        sheet.getRow(2).getCell(2).setCellValue(c.getDate());
    }

    public static void addPreRow(XSSFSheet sheet, Competition<?> competition){
        final int PRE_ROW = CompetitionSheetSpecs.FIRST_PARTICIPANT_ROW-1;
        XSSFRow prerow = sheet.createRow(PRE_ROW);

        // notice 0 is skipped

        prerow.getCell(1).setCellValue("Student ID");
        prerow.getCell(2).setCellValue("Student Name");
        prerow.getCell(3).setCellValue("Major");
        
        int ranknum;
        if( competition instanceof TeamCompetition){
            prerow.getCell(4).setCellValue("team");
            prerow.getCell(5).setCellValue("Team Name");
            ranknum = CompetitionSheetSpecs.TEAM_RANK;
        } else {
            ranknum = CompetitionSheetSpecs.INDIVIDUAL_RANK;
        }

        prerow.getCell(ranknum).setCellValue("Rank");

    }

}

    // public static void addStudents(XSSFSheet sheet, Competition competition){
    // }

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

