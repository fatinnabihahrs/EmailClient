package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Main.ComposeEmail;

public class NewMsgActionListener implements ActionListener {

    private static String recipient;

    public NewMsgActionListener(String r){
        recipient = r;
    }

    public void actionPerformed(ActionEvent e){
        ComposeEmail c = new ComposeEmail();
        c.setRecipient(recipient);
        recipient = ""; //set it to empty string back
        c.main(null);
//        c.setRecipient("");
    }
}
