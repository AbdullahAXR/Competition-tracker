import java.io.*;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;

public class CompetitionSheetBuilder<T extends Competition<? extends Participant>> {

    private XSSFSheet sheet;
    private T c; 

    CompetitionSheetBuilder(XSSFSheet sheet, T c){
        this.sheet = sheet;
        this.c = c;
    }

    public static void main(String[] args) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new File("data.xlsx"));
		XSSFSheet sheet0 = workbook.getSheetAt(0);
		XSSFSheet sheet1 = workbook.getSheetAt(1);

        TeamCompetition c0 = (TeamCompetition) CompetitionSheetParser.getCompetition(sheet0);
        StudentCompetition c1 = (StudentCompetition) CompetitionSheetParser.getCompetition(sheet1);
        System.out.println(c1);

        XSSFSheet newSheet = workbook.createSheet();
        CompetitionSheetBuilder<TeamCompetition> cb = new CompetitionSheetBuilder<TeamCompetition>(newSheet, c0);
        newSheet = cb.buildSheet();
        workbook.write(new FileOutputStream("temp.xlsx"));
        // TeamCompetition sc = (TeamCompetition) CompetitionSheetParser.getCompetition(newSheet);
        System.out.println((TeamCompetition)CompetitionSheetParser.getCompetition(newSheet));

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

        sheet.createRow(0).createCell(0).setCellValue("Competition");
        sheet.getRow(0).createCell(1).setCellValue(c.getName());

        sheet.createRow(1).createCell(0).setCellValue("Link");
        sheet.getRow(1).createCell(1).setCellValue(c.getLink());

        sheet.createRow(2).createCell(0).setCellValue("Date");
        sheet.getRow(2).createCell(1).setCellValue(c.getDate());
    }


    private void insertPreRow() {
        final int PRE_ROW = CompetitionSheetParser.FIRST_PARTICIPANT_ROW-1;
        XSSFRow prerow = sheet.createRow(PRE_ROW);

        // notice 0 is skipped

        prerow.createCell(1).setCellValue("Student ID");
        prerow.createCell(2).setCellValue("Student Name");
        prerow.createCell(3).setCellValue("Major");

        int ranknum;
        if(c instanceof TeamCompetition){
            prerow.createCell(4).setCellValue("team");
            prerow.createCell(5).setCellValue("Team Name");
            ranknum = CompetitionSheetParser.TEAM_RANK;
        } else {
            ranknum = CompetitionSheetParser.INDIVIDUAL_RANK;
        }

        prerow.createCell(ranknum).setCellValue("Rank");
    }

    public void insertStudent(XSSFRow r, Student s, int index){
        r.createCell(0).setCellValue(index);
        r.createCell(1, CellType.STRING).setCellValue(s.getId());
        r.createCell(2).setCellValue(s.getName());
        r.createCell(3).setCellValue(s.getMajor());
    }

    // with rank
    public void insertStudents(){
        int i = CompetitionSheetParser.FIRST_PARTICIPANT_ROW;
        // sheet.createRow(i);
        XSSFRow r;
        Set<Student> students = c.getStudents();

        int index;
        String rank;
        for(Student s : students){
            sheet.createRow(i);
            r = sheet.getRow(i);
            index = i-CompetitionSheetParser.FIRST_PARTICIPANT_ROW+1;
            insertStudent(r, s, index);
            rank = ((StudentCompetition) c).getResult(s);
            r.createCell(CompetitionSheetParser.INDIVIDUAL_RANK).setCellValue(rank);
            i++;

        }
    }

    public void insertTeams(){
        TeamCompetition tc = (TeamCompetition) c;
        int i = CompetitionSheetParser.FIRST_PARTICIPANT_ROW;
        XSSFRow r = sheet.getRow(i);

        String teamName;
        String teamRank;

        // we start from one
        int studentIndex = 1;
        int teamIndex = 1;
        for(Team t : tc.getTeams()){
            teamName = t.getName();
            teamRank = tc.getResult(t);

            for(Student s : t){
                r = sheet.createRow(i);
                r.createCell(CompetitionSheetParser.TEAM_INDEX).setCellValue(teamIndex);
                r.createCell(CompetitionSheetParser.TEAM_NAME).setCellValue(teamName);
                r.createCell(CompetitionSheetParser.TEAM_RANK).setCellValue(teamRank);
                insertStudent(r, s, studentIndex++);
                i++;
            }
            teamIndex++;
            // r = sheet.getRow(++i);
        }
    }

//     private static void insertCompetitionInfo(XSSFSheet sheet, Competition<?> c){
//         sheet.createRow(0);
//         sheet.getRow(0).createCell(0).setCellValue(c.getName());

//         sheet.createRow(1);
//         sheet.getRow(1).createCell(1).setCellValue(c.getLink());

//         sheet.createRow(2);
//         sheet.getRow(2).createCell(2).setCellValue(c.getDate());
//     }
}
