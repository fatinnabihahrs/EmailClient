//import com.sun.org.apache.xpath.internal.operations.Mult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class RetrieveEmailTest {

    private static int msgCount;  //used as JLabel in MainPage

    private static String subject;
    private static String recipient;
    private static int emailNo;

    public static void main (String[] args){

        int max = 5; //take the last 5 emails messages

        try {
            String host = "imap.gmail.com";
            String mailStoreType = "imaps";
            String username = "projectfatin@gmail.com";
            String password = "blngwyzxdgwdcgfb";

            //boolean sessionDebug = false;

            Properties props = System.getProperties();
            props.put("mail.imaps.host", host);
            props.put("mail.store.protocol", mailStoreType);
            props.put("mail.imaps.port", "993");
            props.put("mail.imaps.auth", "true");
            //props.put("mail.imaps.socketFactory.port", "587");
            //props.put("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //props.put("mail.imaps.socketFactory.fallback", "false");

            //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider()); //NOT SURE ABOUT THIS THO
            Session mailSession = Session.getDefaultInstance(props, null);
            //mailSession.setDebug(sessionDebug);


            Store store = mailSession.getStore(mailStoreType);
            store.connect(username, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            msgCount = emailFolder.getMessageCount();
            System.out.println("Total message in INBOX: " + msgCount);

            Message[] messages = emailFolder.getMessages();
            System.out.println("Messages: " + messages.length);


            //print last 5 emails
            for(int i=msgCount;i>msgCount-max;i--){
                Message message = emailFolder.getMessage(i);
                System.out.println("-------------------------");
                System.out.println("Email number: " + (i));
                System.out.println("Subject: "+  message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Content: "); //returns an object
                System.out.println(getTextMessage(message));
                System.out.println("-------------------------");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    private static String message;

    public static String getTextMessage(Part m) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("--CONTENT-TYPE:--" + m.getContentType());--for testing content type

        //for now only handle plain text
        //others etc attachments, html ignored as nothing to do with features-FOR NOW
        if (m.isMimeType("text/plain")){
            message =  (String) m.getContent();
        }
        else if (m.isMimeType("multipart/*")){
            Multipart mult = (Multipart) m.getContent();
            int count = mult.getCount();
            for (int i=0;i<count;i++)
                getTextMessage(mult.getBodyPart(i));
        }
        else {
            Object o = m.getContent();
            if (o instanceof String)
                System.out.println("");
            else if (o instanceof InputStream){
//                InputStream in = (InputStream) o;
//                in = (InputStream) o;
//                int i;
//                while ((i = in.read()) != -1)
//                    System.out.write(i);
                message = "";
            }
            else
                message = "";
        }
        return message;
    }

    //JLabel at the top of MainPage
    public static int getMsgCount(){
         return msgCount;
    }

    public static String getSubject(){
        return subject;
    }

    public static String getRecipient(){
        return recipient;
    }

    public static int getEmailNo(){
        return emailNo;
    }

}
