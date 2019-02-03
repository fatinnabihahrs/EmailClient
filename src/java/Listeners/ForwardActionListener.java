package Listeners;
import Main.ForwardEmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForwardActionListener implements ActionListener {

    private static String subject;
    private static String message;

    public ForwardActionListener(String s, String m){
        subject = s;
        message = m;
    }

    public void actionPerformed(ActionEvent e){
        ForwardEmail f = new ForwardEmail();

        f.setRecipient("");
        f.setSubject("FORWARD: " + subject);
        f.setEmailMessage("-----------FORWARDED MESSAGE----------- \n" + message);

        f.main(null);
    }
}
