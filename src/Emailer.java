import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;

public class Emailer {
    
    // Desktop desktop = Desktop.getDesktop();

    // Emailer(){
    //     // boolean desktopSupported = Desktop.isDesktopSupported();
    //     // boolean emailSupported = desktop.isSupported(Desktop.Action.Mail);
    //     // if( !(desktopSupported && emailSupported) ){
    //     //     throw new RuntimeException("De
    //     // }
    // }

    public static void sendEmail(URI uri) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.mail(uri);
    }

}
