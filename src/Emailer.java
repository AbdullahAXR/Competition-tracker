import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.net.URISyntaxException;
import java.io.IOException;
import java.nio.charset.StandardCharsets ;

public class Emailer {

    // DON'T CHANGE THE SPELLING MISTAKES, THAT WOULD BE REQUIREMENT LEACKAGE.
    private final static String SUBJECT = "Congratulation on achieving %s place in %s";
    private final static String BODY = "Dear %s,\n\nConguratulation on your achievement in %s. This achievement is not deeply appreciated by the unversity and we will announce it in the approbrite medias.\n\nIn case you have Photos you want to share with the news post, reply to this email with the photos.\n\nRegards and Congrats,\nKFUPM News Team\n";

    private static Desktop desktop = Desktop.getDesktop();

    // this is quite general, maybe it should be in its own class.
    // Encode an email to a mailto String.
    public static String encodeEmail(String address, String subject, String body){
        String encoded = "mailto:"+address+"?subject="+URLEncoder.encode(subject, StandardCharsets.UTF_8)+
            "&body="+URLEncoder.encode(body,StandardCharsets.UTF_8);

        encoded = encoded.replace("+", "%20"); // URLEncoder replaces whitespace with +
        return encoded;
    }

    public static void email(Competition<? extends Participant> c, Participant p) {
        String competitionName = c.getName();

        String rank;
        // this is kinda dumb... Can we do better?
        if(p instanceof Team)
            rank = ((TeamCompetition)c).getResult((Team)p);
        else 
            rank = ((StudentCompetition)c).getResult((Student)p);

        String addresses = String.join(",", p.getEmails());
        String subject = String.format(SUBJECT, rank, competitionName);
        String body = String.format(BODY, p.getName(), competitionName);
        String encoded = encodeEmail(addresses, subject, body);

        try {
            URI uri = new URI(encoded);
            desktop.mail(uri);
        }
        catch(URISyntaxException e){ // TODO: remove the catches later
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }

    }
}
