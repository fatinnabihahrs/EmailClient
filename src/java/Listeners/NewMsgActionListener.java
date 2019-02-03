//this action listener is used for replying with a new message/subject --NEW MSG Feature in ViewEmail

package Listeners;

import Main.ReplyNewMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMsgActionListener implements ActionListener {

    private static String recipient;

    public NewMsgActionListener(String r){
        recipient = r;
    }

    public void actionPerformed(ActionEvent e){
        ReplyNewMessage r = new ReplyNewMessage();

        //set the "TO" text field
        //but leave the rest of the text fields empty
        r.setRecipient(recipient);
        r.setSubject("");
        r.setEmailMessage("");

        r.main(null);
    }
}
