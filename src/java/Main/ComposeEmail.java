package Main;

import javax.swing.*;
import java.awt.*;
import Listeners.SendActionListener;

public class ComposeEmail extends JFrame {

    //private static JTextField senderField;
    private static TextField recipientField;
    private static TextField subjectField;
    private static TextArea emailField;

    private static String recipient = "";
    private static String subject = "";
    private static String message = "";

    //contructor
    public ComposeEmail(){

        //--------------------------------------------------------TOP

        JLabel r = new JLabel("To:");
        r.setFont(new Font("Verdana", Font.PLAIN, 20));
        recipientField = new TextField(recipient,100);
        recipientField.setFont(new Font("Verdana", Font.BOLD, 30));

        Panel p1 = new Panel(new FlowLayout());
        p1.add(r);
        p1.add(recipientField);

        JLabel s = new JLabel("Subject:");
        s.setFont(new Font("Verdana", Font.PLAIN, 20));
        subjectField = new TextField(subject, 100);
        subjectField.setFont(new Font("Verdana", Font.BOLD, 30));
        Panel p2 = new Panel(new FlowLayout());
        p2.add(s);
        p2.add(subjectField);

        Panel topPanel = new Panel(new GridLayout(0,1));
        //topPanel.add(p0);
        topPanel.add(p1);
        topPanel.add(p2);
        //--------------------------------------------------------
        if (message == "")
            emailField = new TextArea(100,100); //CENTER
        else {
            //emailField = new TextArea("\n -----Original Message----- \n" + message, 100, 100); //only use this for reply button set
            emailField = new TextArea("\n" + ">" + message, 100, 100);
            //emailField.setCaretPosition(0);
        }

        emailField.setFont(new Font("Verdana", Font.BOLD, 30));
        //-------------------------------------------------------
        JPanel bottom = new JPanel(new FlowLayout()); //BOTTOM
        JButton sendButton = new JButton("SEND");
        sendButton.setFont(new Font("Verdana", Font.PLAIN, 20));
        bottom.add(sendButton);
        //-------------------------------------------------------

        setTitle("New Email");
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(topPanel, BorderLayout.NORTH);
        contents.add(emailField, BorderLayout.CENTER);
        contents.add(bottom, BorderLayout.SOUTH);


        SendActionListener listener = new SendActionListener();
        sendButton.addActionListener(listener);                    //if the sendButton is press, then invoke the method in action listener

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        pack();


    }

    public static void main(String[] args){
        ComposeEmail em = new ComposeEmail();
        //em.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        em.setVisible(true);
    }

    /*private static String getSender(){
        return senderField.getText();
    }*/

    public static String getRecipient() {
        return recipientField.getText();
    }

    public static String getSubject(){
        return subjectField.getText();
    }

    public static String getEmailMessage(){
        return emailField.getText();
    }

    ///////////////////////////////////////////////////////////////
    //used to replying an email need to setup based on prev email
    //use the compose email template to put all the stuff in
    public static void setRecipient(String s){
        recipient = s;
    }

    public static void setSubject (String s){
        subject = s;
    }


    //use here for replying/forwarding/redirecting
    //need to a way for distinguish the prev email message to the new email type by user
    public static void setEmailMessage(String s) {
        message = s;
    }


    public static TextArea getEmailField(){
        return emailField;
    }

}
