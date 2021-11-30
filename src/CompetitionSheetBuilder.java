import java.io.*;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.LinkedHashMap;
import java.util.Set;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellStyle;

public class CompetitionSheetBuilder<T extends Competition<? extends Participant>> {

    private XSSFSheet sheet;
    private T c; 
    private XSSFRow prerow;

    // cel style of participants
    static XSSFCellStyle style;

    CompetitionSheetBuilder(XSSFSheet sheet, T c) throws Exception {

        // used globally
        this.sheet = sheet;
        this.c = c;
        this.prerow = sheet.createRow(Specs.FIRST_PARTICIPANT_ROW-1); // this is used two times, so we just create a variable for it.

        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("data.xlsx"));
        XSSFCellStyle sampleStyle = wb.getSheetAt(0).getRow(Specs.FIRST_PARTICIPANT_ROW).getCell(Specs.ID_CELL).getCellStyle();
        wb.close();

        this.style = sheet.getWorkbook().createCellStyle();
        this.style.cloneStyleFrom(sampleStyle);
    }

    public static void main(String[] args) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new File("data.xlsx"));
		XSSFSheet sheet0 = workbook.getSheetAt(0);
		XSSFSheet sheet1 = workbook.getSheetAt(1);

        TeamCompetition c0 = (TeamCompetition) CompetitionSheetParser.getCompetition(sheet0);
        StudentCompetition c1 = (StudentCompetition) CompetitionSheetParser.getCompetition(sheet1);
        XSSFSheet newSheet0 = workbook.createSheet();
        XSSFSheet newSheet1= workbook.createSheet();
        // System.out.println((StudentCompetition)CompetitionSheetParser.getCompetition(sheet0));

        CompetitionSheetBuilder<TeamCompetition> cb0 = new CompetitionSheetBuilder<TeamCompetition>(newSheet0, c0);
        CompetitionSheetBuilder<StudentCompetition> cb1 = new CompetitionSheetBuilder<StudentCompetition>(newSheet1, c1);

        newSheet0 = cb0.buildSheet();
        newSheet1 = cb1.buildSheet();
        workbook.write(new FileOutputStream("temp.xlsx"));
        // StudentCompetition sc = (StudentCompetition) CompetitionSheetParser.getCompetition(newSheet);
        // System.out.println((TeamCompetition)CompetitionSheetParser.getCompetition(newSheet0));
        // System.out.println((StudentCompetition)CompetitionSheetParser.getCompetition(newSheet1));

    }

    public XSSFSheet buildSheet(){
        insertBasicInfo();

        if(c instanceof TeamCompetition) {
            insertTeamCompetitionPreRow();
            insertTeams();
        }
        else {
            insertStudentCompetitionPreRow();
            insertStudents();
        }

        autoSizeColumns();
        return sheet;
    }

    public void insertBasicInfo(){

        sheet.createRow(0).createCell(0).setCellValue("Competition");
        sheet.getRow(0).createCell(1).setCellValue(c.getName());

        sheet.createRow(1).createCell(0).setCellValue("Link");
        sheet.getRow(1).createCell(1).setCellValue(c.getLink());

        sheet.createRow(2).createCell(0).setCellValue("Date");

        XSSFCell dateCell = sheet.getRow(2).createCell(1);
        CellStyle style = dateCell.getCellStyle();
        style.setDataFormat((short)0xf); // put this as a constant
        dateCell.setCellStyle(style);
        dateCell.setCellValue(c.getDate());

    }


    private void insertCommonPreRow() {
        // notice 0 is skipped

        prerow.createCell(1).setCellValue("Student ID");
        prerow.createCell(2).setCellValue("Student Name");
        prerow.createCell(3).setCellValue("Major");

        prerow.createCell(0).setCellStyle(this.style); // we style this cell even though its empty
        prerow.getCell(1).setCellStyle(this.style);
        prerow.getCell(2).setCellStyle(this.style);
        prerow.getCell(3).setCellStyle(this.style);

    }

    private void insertStudentCompetitionPreRow() {
        insertCommonPreRow();
        prerow.createCell(Specs.INDIVIDUAL_RANK_CELL).setCellValue("Rank");
        prerow.getCell(Specs.INDIVIDUAL_RANK_CELL).setCellStyle(this.style);
    }

    private void insertTeamCompetitionPreRow(){
        insertCommonPreRow();
        prerow.createCell(4).setCellValue("team");
        prerow.createCell(5).setCellValue("Team Name");
        prerow.createCell(Specs.TEAM_RANK_CELL).setCellValue("Rank");

        prerow.getCell(4).setCellStyle(this.style);
        prerow.getCell(5).setCellStyle(this.style);
        prerow.getCell(Specs.TEAM_RANK_CELL).setCellStyle(this.style);
    }

    public void insertStudent(XSSFRow r, Student s, int index){
        r.createCell(0).setCellValue(index);
        r.createCell(1, CellType.STRING).setCellValue(s.getId());
        r.createCell(2).setCellValue(s.getName());
        r.createCell(3).setCellValue(s.getMajor());

        r.getCell(0).setCellStyle(this.style);
        r.getCell(1).setCellStyle(this.style);
        r.getCell(2).setCellStyle(this.style);
        r.getCell(3).setCellStyle(this.style);
    }

    // with rank
    public void insertStudents(){
        int i = Specs.FIRST_PARTICIPANT_ROW;
        // sheet.createRow(i);
        XSSFRow r;
        Set<Student> students = c.getStudents();

        int index;
        String rank;
        for(Student s : students){
            sheet.createRow(i);
            r = sheet.getRow(i);
            index = i-Specs.FIRST_PARTICIPANT_ROW+1;
            insertStudent(r, s, index);
            rank = ((StudentCompetition) c).getResult(s);
            r.createCell(Specs.INDIVIDUAL_RANK_CELL, CellType.STRING).setCellValue(rank);
            r.getCell(Specs.INDIVIDUAL_RANK_CELL).setCellStyle(this.style);
            i++;

        }
    }

    public void insertTeams(){
        TeamCompetition tc = (TeamCompetition) c;
        int i = Specs.FIRST_PARTICIPANT_ROW;
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
                r.createCell(Specs.TEAM_INDEX_CELL).setCellValue(teamIndex);
                r.getCell(Specs.TEAM_INDEX_CELL).setCellStyle(this.style);

                r.createCell(Specs.TEAM_NAME_CELL).setCellValue(teamName);
                r.getCell(Specs.TEAM_NAME_CELL).setCellStyle(this.style);

                r.createCell(Specs.TEAM_RANK_CELL, CellType.STRING).setCellValue(teamRank);
                r.getCell(Specs.TEAM_RANK_CELL).setCellStyle(this.style);

                insertStudent(r, s, studentIndex++);
                i++;
            }
            teamIndex++;
            // r = sheet.getRow(++i);
        }
    }

    public void autoSizeColumns(){
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
    }

}
