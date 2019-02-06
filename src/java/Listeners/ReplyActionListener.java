package Listeners;

import Main.ReplyEmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyActionListener implements ActionListener {

    private static String option = "";
    private static String recipient;
    private static String subject;
    private static String message;
    private static ReplyEmail r;

    public ReplyActionListener(String r, String s, String m){
        recipient = r;
        subject = s;
        message = m;
    }

    public void actionPerformed (ActionEvent e) {
        if (option == "TOP-POSTING") {
            handleBasic();
            r.setEmailMessage(message);
        } else if (option == "BOTTOM-POSTING") {
            handleBasic();
            r.setEmailMessage(message);
        } else if (option == "INLINE-POSTING") {
            handleBasic();
            r.setEmailMessage(message);
        } else{
            handleBasic();
            r.setEmailMessage(message);
        }
        r.main(null);
    }

    public static void setReplySettings(String o){
        option = o;
    }

    public static void handleBasic(){
        r.setRecipient(recipient);
        r.setSubject(subject);
    }


}
