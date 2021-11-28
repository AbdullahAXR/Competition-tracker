import java.io.File;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;
import java.io.IOException;

public class CompetitionSheetBuilder<T extends Competition<? extends Participant>> {

    private XSSFSheet sheet;
    private T c; 
    private Set<Student> students;

    CompetitionSheetBuilder(XSSFSheet sheet, T c){
        this.sheet = sheet;
        this.c = c;
    }

    public static void main(String[] args) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new File("data.xlsx"));
		XSSFSheet sheet0 = workbook.getSheetAt(0);
		XSSFSheet sheet1 = workbook.getSheetAt(1);

        StudentCompetition c1 = (StudentCompetition) CompetitionSheetParser.getCompetition(sheet1);
        System.out.println(c1);

        XSSFSheet newSheet1 = workbook.createSheet();
        CompetitionSheetBuilder<StudentCompetition> cb = new CompetitionSheetBuilder<>(newSheet1, c1);
        XSSFSheet bsheet1 = cb.buildSheet();

    }

    public XSSFSheet buildSheet(){
        insertBasicInfo();
        insertPreRow();

        if(c instanceof TeamCompetition)
            insertTeams();
        else
            insertStudents();

        return sheet;
    }

    public void insertBasicInfo(){
        String name = c.getName();
        String link = c.getLink();
        Date date = c.getDate();

        sheet.createRow(0);
        sheet.getRow(0).getCell(0).setCellValue(c.getName());

        sheet.createRow(1);
        sheet.getRow(1).getCell(1).setCellValue(c.getLink());

        sheet.createRow(2);
        sheet.getRow(2).getCell(2).setCellValue(c.getDate());
    }


    private void insertPreRow() {
        final int PRE_ROW = CompetitionSheetParser.FIRST_PARTICIPANT_ROW-1;
        XSSFRow prerow = sheet.createRow(PRE_ROW);

        // notice 0 is skipped

        prerow.getCell(1).setCellValue("Student ID");
        prerow.getCell(2).setCellValue("Student Name");
        prerow.getCell(3).setCellValue("Major");

        int ranknum;
        if(c instanceof TeamCompetition){
            prerow.getCell(4).setCellValue("team");
            prerow.getCell(5).setCellValue("Team Name");
            ranknum = CompetitionSheetParser.TEAM_RANK;
        } else {
            ranknum = CompetitionSheetParser.INDIVIDUAL_RANK;
        }

        prerow.getCell(ranknum).setCellValue("Rank");
    }

    public void insertStudent(XSSFRow r, Student s){
        r.getCell(0).setCellValue(s.getId());
        r.getCell(1).setCellValue(s.getName());
        r.getCell(2).setCellValue(s.getMajor());
    }

    public void insertStudents(){
        int i = CompetitionSheetParser.FIRST_PARTICIPANT_ROW;
        XSSFRow r = sheet.getRow(i);

        for(Student s : students){
            insertStudent(r, s);
            r = sheet.getRow(++i);
        }
    }

    public void insertTeams(){
        TeamCompetition tc = (TeamCompetition) c;
        int i = CompetitionSheetParser.FIRST_PARTICIPANT_ROW;
        XSSFRow r = sheet.getRow(i);

        String teamName;
        String teamRank;
        for(Team t : tc.getTeams()){
            teamName = t.getName();
            teamRank = tc.getResult(t);

            for(Student s : t){
                r.getCell(CompetitionSheetParser.TEAM_INDEX).setCellValue(i-CompetitionSheetParser.FIRST_PARTICIPANT_ROW);
                r.getCell(CompetitionSheetParser.TEAM_NAME).setCellValue(teamName);
                r.getCell(CompetitionSheetParser.TEAM_RANK).setCellValue(teamRank);
                insertStudent(r, s);
            }
            r = sheet.getRow(++i);
        }
    }

//     private static void insertCompetitionInfo(XSSFSheet sheet, Competition<?> c){
//         sheet.createRow(0);
//         sheet.getRow(0).getCell(0).setCellValue(c.getName());

//         sheet.createRow(1);
//         sheet.getRow(1).getCell(1).setCellValue(c.getLink());

//         sheet.createRow(2);
//         sheet.getRow(2).getCell(2).setCellValue(c.getDate());
//     }
}
