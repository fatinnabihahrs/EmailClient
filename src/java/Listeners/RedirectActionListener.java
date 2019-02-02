//this redirect feature will redirect an email to another person suitable of receiving this
//alternative or more suited than the forward feature
//depending on the purpose

package Listeners;

import Main.ComposeEmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirectActionListener implements ActionListener {

    private static String sender;
    private static String message;

    public RedirectActionListener(String s, String m){
        sender = s;
        message = m;
    }

    //on pressing the redirect feature button
    public void actionPerformed (ActionEvent e){

        //1. redirect the email to new receiver-looks the same as the new msg feature
        ComposeEmail c = new ComposeEmail();
        //c.set


        //2. send a seperate email automatically to original sender

    }
}
