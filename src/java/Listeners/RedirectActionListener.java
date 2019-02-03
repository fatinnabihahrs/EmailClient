//this redirect feature will redirect an email to another person suitable of receiving this
//alternative or more suited than the forward feature
//depending on the purpose

package Listeners;

import Main.ComposeEmail;
import Main.RedirectEmail;
import Main.SendingEmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirectActionListener implements ActionListener {

    private static String from;
    private static String message;
    private static String subject;

    private static String owner = "projectfatin@gmail.com";

    public RedirectActionListener(String f, String s, String m){
        from = f;
        message = m;
        subject = s;
    }

    //on pressing the redirect feature button
    public void actionPerformed (ActionEvent e){

        //1. redirect the email to new receiver-looks the same as the new msg feature
        RedirectEmail c = new RedirectEmail();

        c.setRecipient("");
        c.setSubject(subject);
        c.setEmailMessage("<<REDIRECTED BY " + owner + " >>\n" + message); //TEST TO SEE IF THIS WORKS

        SendingEmail.setFrom(from);

        c.main(null);


        //2. send a seperate email automatically to original sender
//        SendingEmail se = new SendingEmail();
//        se.setRecp(from);
//        se.setSubject("EMAIL REDIRECTED");
//        se.setMessage("This is an automatic email. This is to inform that your email to " + owner + " has been redirected to " + message);

    }
}
