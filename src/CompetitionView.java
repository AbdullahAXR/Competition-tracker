import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CompetitionView extends Pane {
    Competition<?> currentCompetition;
    Button emailBtn = new Button("Email");

    CompetitionView(){
        super();
        if(!Globals.MANAGER.isEmpty()){
        this.getChildren().add(new Label(Globals.MANAGER.get(0).toString()));
        this.getChildren().add(emailBtn);
        }

        emailBtn.setOnAction(e -> sendEmail());
    }

    // public String[] getEmails(Participant p){
    //     String[] emails;
    //     Team t;
    //     if(p instanceof Team)
    //         Team t = (Team) p;
    //         emails = new String[t.size()];
    //         email = ((Team) p).getEmails();
    //     else
    //         emails[0] = ((Student) p).getEmail();

    //     return emails;
    // }

    public void sendEmail(){
        new Thread(() -> {
            String[] emails;
            try {
                Emailer.emailStudent(new Student("201914330", "saher", "cs"), (StudentCompetition) Globals.MANAGER.get(0));
                // Emailer.sendEmail(new URI("mailto:s201914330@kfupm.edu.sa"));
            }
            catch(Exception e){
                System.out.println(e);
            }
        }).start();
    }

    // private URI getURI(){
    //     currentCompetition.getStudents().stream().filter(
    // }
}
