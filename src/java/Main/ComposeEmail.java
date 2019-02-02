package Main;

import javax.swing.*;
import java.awt.*;
import Listeners.SendActionListener;

public class ComposeEmail extends JFrame {

    private static JTextField senderField;
    private static JTextField recipientField;
    private static JTextField subjectField;
    private static JTextArea emailField;

    private static String recipient = "";
    private static String subject = "";
    private static String message = "";
    private static String sender = "";

    //contructor
    public ComposeEmail(){

        //--------------------------------------------------------
        JLabel send = new JLabel("Sender:");          //TOP
        //recipient = "";
        senderField = new JTextField(sender,100);
        Panel p0 = new Panel(new FlowLayout());
        p0.add(send);
        p0.add(senderField);

        JLabel r = new JLabel("Recipient:");
        //recipient = "";
        recipientField = new JTextField(recipient,100);
        Panel p1 = new Panel(new FlowLayout());
        p1.add(r);
        p1.add(recipientField);

        JLabel s = new JLabel("Subject:");
//        subject = "";
        subjectField = new JTextField(subject, 100);
        Panel p2 = new Panel(new FlowLayout());
        p2.add(s);
        p2.add(subjectField);

        Panel topPanel = new Panel(new GridLayout(0,1));
        topPanel.add(p0);
        topPanel.add(p1);
        topPanel.add(p2);
        //--------------------------------------------------------

        emailField = new JTextArea(100,100); //CENTER
        //-------------------------------------------------------
        JPanel bottom = new JPanel(new FlowLayout()); //BOTTOM
        JButton sendButton = new JButton("SEND");
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
        em.setVisible(true);
    }

    private static String getSender(){
        return senderField.getText();
    }

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
    public static void setEmailMessage(String s){
        emailField.setText(s);
    }

    public static void setSender (String s){
        sender = s;
    }

}
