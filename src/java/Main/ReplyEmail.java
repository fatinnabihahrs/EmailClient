package Main;

import Listeners.ReplyActionListener;

import javax.swing.*;
import java.awt.*;

public class ReplyEmail extends ComposeEmail{

    //contructor
    public ReplyEmail(){

        super();
    }

    public static void main(String[] args){
        ReplyEmail em = new ReplyEmail();
        em.getEmailField().requestFocusInWindow();

        String option = ReplyActionListener.getOption();
        if (option.equals("Top-posting")){
            em.getEmailField().setCaretPosition(0);
        }
        else if (option.equals("Bottom-posting")){
            em.getEmailField().setCaretPosition(em.getEmailField().getText().length());
        }
        else if (option.equals("Inline-posting")){
            em.getEmailField().setCaretPosition(0);
            em.setInLine(true);

        }
        else
            //em.getEmailField().setCaretPosition(em.getEmailField().getDocument().getLength());
            em.getEmailField().setCaretPosition(em.getEmailField().getText().length());

        em.setVisible(true);
    }
}
