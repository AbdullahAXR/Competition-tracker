import java.io.File;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
public final class CompetitionSheetParser {

    enum Type {
        TEAM,
        INDIVIDUAL
    }

    ///// TODO: I bet there's a more elegant way to do this.
    private static String getName(XSSFSheet sheet){
        return sheet.getRow(Specs.NAME_ROW).getCell(Specs.INFO_CELL).toString();
    }

    private static Date getDate(XSSFSheet sheet){
        return sheet.getRow(Specs.DATE_ROW).getCell(Specs.INFO_CELL).getDateCellValue();
    }

    private static String getLink(XSSFSheet sheet){
        return sheet.getRow(Specs.LINK_ROW).getCell(Specs.INFO_CELL).toString();
    }
    /////

    public static Competition<?> getCompetition(XSSFSheet sheet){
        Competition<?> c;
        Type type =  getType(sheet);

        if(type == Type.INDIVIDUAL)
            c = getStudentCompetition(sheet);
        else
            c = getTeamCompetition(sheet);

        return c;
    }


    // get type of the competition
    public static Type getType(XSSFSheet sheet) {
        String team = sheet.getRow(Specs.TYPE_ROW).getCell(Specs.TYPE_CELL).toString();

        Type type;
        if(team.equals("team")) {
            type = Type.TEAM;
        } else
            type = Type.INDIVIDUAL;

        return type;
    }

    // get a student from a row
    private static Student getStudent(XSSFRow row) {
        // for some reason the id Specs.cell is numeric. I think this is a mistake
        // with the project files. We can workaround it by changing it to String

        XSSFCell idCell = row.getCell(Specs.ID_CELL);
        idCell.setCellType(CellType.STRING); //turn this into a method
        String id = row.getCell(Specs.ID_CELL).toString();

        String name  = row.getCell(Specs.NAME_CELL).toString();
        String major = row.getCell(Specs.MAJOR_CELL).toString();

        return new Student(id, name, major); // everytime, we create a new student...
    }

    private static LinkedHashMap<Student, String> getStudentCompetitionResults(XSSFSheet sheet){
        LinkedHashMap<Student, String> results = new LinkedHashMap<Student, String>();

        int i = Specs.FIRST_PARTICIPANT_ROW;
        XSSFRow row = sheet.getRow(i);
        while(row != null){
            Student student = getStudent(row);

            // a rank Specs.cell can either numeric or string. This is most likely an
            // issue with the provided excel file. However, we can overcome it
            // by querying the Specs.cell type and converting it accordingly using the
            // getCellType() method. Another appraoch is to use toString().
            // However I'm not sure how that behaves

            XSSFCell rankCell = row.getCell(Specs.INDIVIDUAL_RANK_CELL);
            rankCell.setCellType(CellType.STRING); //turn this into a method
            String rank = row.getCell(Specs.INDIVIDUAL_RANK_CELL).toString();
            // System.out.println(rank); looks fine
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

        int i = Specs.FIRST_PARTICIPANT_ROW;
        XSSFRow row = sheet.getRow(i);

        while(row != null){
            String name = row.getCell(Specs.TEAM_NAME_CELL).toString();

            // its okay to run keyset() everytime here since its O(1) (I checked
            // the source code)
            Set<Team> teams = results.keySet();

            // we can use a lambda expression via orElseGet() instead of returning null but
            // we can't since we won't have access to the row varaible as java
            // does not support lexical scoping. This costs an extra if
            // statement, but it shouldn't be a big deal
            Team team = teams.stream()
                .filter(t -> t.getName() == name)
                .findFirst()
                .orElse(null);

            if(team == null){
                team = new Team(name);
                XSSFCell rankCell = row.getCell(Specs.TEAM_RANK_CELL);
                rankCell.setCellType(CellType.STRING); // put this in a method
                String rank = row.getCell(Specs.TEAM_RANK_CELL).toString();
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
        final int PRE_ROW = Specs.FIRST_PARTICIPANT_ROW-1;
        XSSFRow prerow = sheet.createRow(PRE_ROW);

        // notice 0 is skipped

        prerow.getCell(1).setCellValue("Student ID");
        prerow.getCell(2).setCellValue("Student Name");
        prerow.getCell(3).setCellValue("Major");

        int ranknum;
        if( competition instanceof TeamCompetition){
            prerow.getCell(4).setCellValue("team");
            prerow.getCell(5).setCellValue("Team Name");
            ranknum = Specs.TEAM_RANK_CELL;
        } else {
            ranknum = Specs.INDIVIDUAL_RANK_CELL;
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

