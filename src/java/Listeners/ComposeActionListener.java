//action listener is used for composing a new email - COMPOSE + Feature in the Main Page

package Listeners;

import Main.ComposeEmail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ComposeActionListener implements ActionListener{

    public ComposeActionListener(){

    }

    public void actionPerformed(ActionEvent e){
        ComposeEmail c = new ComposeEmail();

        //make all the text field to be empty
        c.setRecipient("");
        c.setSubject("");
        c.setEmailMessage("");

        c.main(null);
    }
}
