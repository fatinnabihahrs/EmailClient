package Listeners;

import Main.ReplyEmail;
import Main.ViewEmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyActionListener implements ActionListener {

    private static String option;
    private static String recipient;
    private static String subject;
    private static String message;
    private static ReplyEmail reply;

    public ReplyActionListener(String r, String s, String m){
        recipient = r;
        subject = s;
        message = m;
        reply = new ReplyEmail();
    }

    public void actionPerformed (ActionEvent e) {
        option = ViewEmail.getOption();
        handleBasic();
        reply.setEmailMessage(message);
        reply.main(null);
    }

    public static void setReplySettings(String o){
        option = o;
    }

    public static void handleBasic(){
        reply.setRecipient(recipient);
        reply.setSubject(subject);
    }

    public static String getOption(){
        return option;
    }


}
