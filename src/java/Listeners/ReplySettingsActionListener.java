package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplySettingsActionListener implements ActionListener {

    private static String option;

    public ReplySettingsActionListener(String o){
        option = o;
    }

    public void actionPerformed(ActionEvent e){
        if (option !=  "") {
            ReplyActionListener.setReplySettings(option);
        }
        //if empty string use default?
        else
            ReplyActionListener.setReplySettings(""); //set default
    }
}
