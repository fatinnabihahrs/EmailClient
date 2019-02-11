package Listeners;

import Main.ReplyEmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyActionListener implements ActionListener {

    private static String option = "";
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
        if (option.equals("TOP-POSTING")) {
            handleBasic();
            reply.setEmailMessage(message);
            reply.getEmailField().setCaretPosition(5);
        } else if (option.equals("BOTTOM-POSTING")) {
            handleBasic();
            reply.setEmailMessage(message);
            //r.getEmailField().setCaretPosition(r.getEmailField().getText().length());
        } else if (option.equals("INLINE-POSTING")) {
            handleBasic();
            //r.setEmailMessage(message);
        } else{
            handleBasic();
            reply.setEmailMessage(message);
            //r.getEmailField().setCaretPosition(0);
        }
        reply.main(null);
    }

    public static void setReplySettings(String o){
        option = o;
    }

    public static void handleBasic(){
        reply.setRecipient(recipient);
        reply.setSubject(subject);
    }


}
