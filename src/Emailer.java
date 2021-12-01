import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;

public class Emailer {

    private URI uri;
    private static Desktop desktop = Desktop.getDesktop();

    // Emailer(URI uri){
    //     this.uri = uri;
    // }

    // private static void sendEmail(URI uri) throws IOException {
    //     desktop.mail(uri);
    // }


    // public static void emailStudent(Student s) throws IOException {
    //     String email = s.getEmail();
    // }

    public static void emailStudent(Student s, StudentCompetition c) throws IOException {
        String sname= s.getName();
        String cname = c.getName();


        // TODO: we're supposed generate URIs from strings. Perhaps a better way to do
        // this is by creating a static class URIMaker that has a method that
        // takes a string and output a proper mailto link.
        System.out.println(getSubject(c.getResult(s), cname));
        System.out.println(getBody(sname, cname));
    }

    // See todo below
    private static String getSubject(String rank, String competitionName){
        return "Conguratulations on achieving "+rank+" place in "+competitionName;
    }

    // TODO: this is just a proof of concept. Redo this using String.format()
    // and store the body in a constant in globals. If you don't know
    // String.format(), then look it up
    private static String getBody(String participantName, String competitionName){
        return "Dear "+participantName+",\n\nConguratulation on your achievement in "+competitionName+". This achievement is not deeply appreciated by the unversity and we will announce it in the approbrite medias.\n\nIn case you have Photos you want to share with the news post, reply to this email with the photos.\n\nRegards and Congrats,\nKFUPM News Team";
    }
}
