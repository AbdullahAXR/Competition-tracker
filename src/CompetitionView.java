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

    public static void sendEmail(){
        new Thread(() -> {
            try {
                Emailer.sendEmail(new URI("mailto:s201914330@kfupm.edu.sa"));
            }
            catch(Exception e){
                System.out.println(e);
            }
        }).start();
    }
}
