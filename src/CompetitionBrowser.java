import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

// we can't extend webview
public class CompetitionBrowser {

    public static void browse(Competition<?> c, boolean jsEnabled){
        WebView wv = new WebView();
        wv.getEngine().load(c.getLink());
        wv.getEngine().setJavaScriptEnabled(jsEnabled);
        Pane pane = new Pane(wv);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static void browse(Competition<?> c){
        browse(c, true); // if the user does not specify, enable javascript
    }

}
