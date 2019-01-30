/* THINGS TO CONSIDER
1. SMTP HOST-prob need to change to something else gmail has a limitation of how many emails can be sent per month not useful in long run
SOLUTION--> using mailjet smtp relay. its free for 6000 emails/month aka 200 emails/day more than enough. also this enable to send from any type of
email address eg gmail, yahoo
2. THE PASSWORD ENTERED BY USER-need to be protected in form ****?prob need to be protected at the front end?do i need a db for this
3. THE SENDTO() METHOD-doesnt handle if there are multiple recipients. one way to do this is have a while stt and keep on asking if want to add more.
regardless of the number of emails, we will save this in a file. then need a way to read from a file the list of recipient(s).
 */

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;


public class SendingEmail {


    private static String recipient; //recipient's email
    private static String subject;  //subject
    private static String content;  //email content

    public static void main(String[] args){
        try{
            String host = "smtp.gmail.com";

            String user = "projectfatin@gmail.com";
            String password = "blngwyzxdgwdcgfb";             //gmail password-for app that dont support 2 factor verification
            String from = "projectfatin@gmail.com";

            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(recipient)};
            ((MimeMessage) msg).setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            ((MimeMessage) msg).setText(content);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Message sent succesfull...");
        }
        catch(Exception e){
            System.out.println("Message failed to send");
            System.out.println(e);
        }
    }

    public static void setMessage(String m){
        content = m;
    }

    public static void setSubject(String s){
        subject = s;
    }

    public static void setRecp(String r){
        recipient = r;
    }

    public static void start(){
        main(null);
    }
}
