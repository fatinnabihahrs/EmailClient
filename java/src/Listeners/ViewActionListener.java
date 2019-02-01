package Listeners;////view the whole email on a separate window

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import Main.*;


public class ViewActionListener implements ActionListener {

    private static String subject;
    private static String sender;
    private static int emailNo;
    private static String message;


    public ViewActionListener(){
    }

    public void actionPerformed(ActionEvent e){
        //get the source ie button
        //then get the label convert to int to get the email number
        Button source = (Button)e.getSource();
        emailNo = Integer.parseInt(source.getLabel());
        System.out.println("VIEW EMAIL : " + emailNo);

        //create a new window for viewing an email
        ViewEmail v = new ViewEmail();
        v.setMsgNo(emailNo);
        v.setSender(MainPage.getSender(emailNo));
        v.setSubject(MainPage.getSubject(emailNo));
        v.setMessage(MainPage.getMessage(emailNo));
        v.start();

    }

}

