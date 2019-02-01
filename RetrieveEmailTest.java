package Main;

import javax.mail.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class RetrieveEmailTest {

    private static int msgCount;  //used as JLabel in MainPage

    private static String subject;
    private static String sender;
    private static int emailNo;
    private static String messagePassed;


    public static void main (String[] args){

        int choose = Integer.parseInt(args[0]);
        int index = Integer.parseInt(args[1]);


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

            if (choose == 0)
                msgCount = emailFolder.getMessageCount();
            else {
                //System.out.println("Line 56 RET:" + index);
                Message message = emailFolder.getMessage(index);
                subject = message.getSubject();
                sender = "" + message.getFrom()[0];
                messagePassed = getTextMessage(message); //content of the message
            }
        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    public static String getTextMessage(Part m) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("--CONTENT-TYPE:--" + m.getContentType());--for testing content type

        //for now only handle plain text
        //others etc attachments, html ignored as nothing to do with features-FOR NOW
        if (m.isMimeType("text/plain")){
            messagePassed =  (String) m.getContent();
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
                messagePassed = "";
            }
            else
                messagePassed = "";
        }
        return messagePassed;
    }

    //JLabel at the top of MainPage
    public static int getMsgCount(){
        start(0, 0);
        return msgCount;
    }

    public static String getSubject(int index){
        start(1, index);
        return subject;
    }

    public static String getSender(int index){
        start(1, index);
        return sender;
    }

    public static String getMessage(int index){
        start(1, index);
        return messagePassed;
    }

    public static void start(int choose, int index){
        String args[] = {"" + choose, "" + index};
        main(args);
    } //invoke the main method to start

}