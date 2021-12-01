import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;
import java.io.IOException;
import java.net.URI;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        BorderPane bp = new BorderPane();

        StatusBar statusBar = new StatusBar(Globals.SPACING);
        bp.setBottom(statusBar);

        CompetitionView view = new CompetitionView();
        bp.setLeft(view);

        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // try {
        // Emailer.sendEmail(new URI("mailto:s201914330@kfupm.edu.sa"));
        // }
        // catch(Exception e){
        //     System.out.println(e);
        // }
        launch();
        // System.out.println(Globals.MANAGER.getNumberOfTeamCompetition());
    }

    // public static void add(){
    //     LinkedHashMap <Student, String> lhm =  new LinkedHashMap<Student, String>();
    //     lhm.put(new Student("30238531", "saher's friend", "MATH"), "1");
    //     lhm.put(new Student("11218731", "saher's other friend", "swe"), "2");
    //     lhm.put(new Student("50731591", "saher himself", "cs"), "-");
    //     Globals.MANAGER.add(new StudentCompetition("saher's cool competition", "www.saher.com", new Date(), lhm));
    //     // TeamCompetition tc = (TeamCompetition) cm.competitions.get(0);

    //     try {
    //     Globals.MANAGER.writeCompetitions();
    //     }
    //     catch(Exception e){
    //         System.out.println(e);
    //     }

    // }

}

    // public void start(Stage stage) {
    //     String javaVersion = System.getProperty("java.version");
    //     String javafxVersion = System.getProperty("javafx.version");
    //     Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    //     Scene scene = new Scene(new StackPane(l), 640, 480);
    //     stage.setScene(scene);
    //     stage.show();
    // }
