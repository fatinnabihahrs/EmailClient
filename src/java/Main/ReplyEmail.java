package Main;

import Listeners.ReplyActionListener;
import edu.stanford.nlp.util.StringUtils;

import javax.swing.*;
import javax.swing.text.*;
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
            em.getEmailField().setText("\n" + "----<ORIGINAL MESSAGE>---- " + em.getEmailMessage());
            em.getEmailField().setCaretPosition(0);
        }
        else if (option.equals("Bottom-posting")){
            String newTxt = StringUtils.chomp(em.getEmailMessage());
            //newTxt = newTxt.replace("\n", "\n");
            em.getEmailField().setText("----ORIGINAL MESSAGE---- " + newTxt + "\n---------------------------" + "\n>>");
            em.getEmailField().setCaretPosition(em.getEmailField().getText().length());
        }
        else if (option.equals("Inline-posting")){
            String newTxt = StringUtils.chomp(em.getEmailMessage());
            newTxt = newTxt.replace("?", "?\n\t>\n");
            em.getEmailField().setText(newTxt);
            em.getEmailField().setCaretPosition(0);
            em.setInLine(true);

        }
        else
            //em.getEmailField().setCaretPosition(em.getEmailField().getDocument().getLength());
            em.getEmailField().setCaretPosition(em.getEmailField().getText().length());

        em.setVisible(true);
    }
}
