package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Main.ComposeEmail;
import Main.SendingEmail;

public class SendActionListener implements ActionListener{

    public SendActionListener(){

    }

    public void actionPerformed(ActionEvent event){
        String recp = ComposeEmail.getRecipient();
        String subj = ComposeEmail.getSubject();
        String msg = ComposeEmail.getEmailMessage();
        String time = ComposeEmail.getTime();
        System.out.println("SENDACTIONLISTENER: " + time);

        SendingEmail.setMessage(msg);
        SendingEmail.setRecp(recp);
        SendingEmail.setSubject(subj);
        SendingEmail.setTime(time);
        SendingEmail.start();           //invoke the main method
    }

}
