//this is the gui for the 2nd page of the application - viewing an email
//this page is from the 1st page by pressing on the button email

import java.awt.*;
import javax.swing.*;

public class ViewEmail extends JFrame{

    private static String sender;
    private static String subject;
    private static String message;
    private static int msgNo;

    //constructor
    public ViewEmail(){

        //-----------------------------------------------
        Panel topPanel = new Panel(new GridLayout(2,0));  //TOP
        topPanel.add(new JLabel("FROM: " + sender));
        topPanel.add(new JLabel("SUBJECT: " + subject));
        //------------------------------------------------
        JLabel email = new JLabel(message);  //MIDDLE
        //------------------------------------------------
        Panel bottomPanel = new Panel(new FlowLayout());        //BOTTOM
        JButton newMsgBut = new JButton("NEW MESSAGE");
        JButton replyBut = new JButton("REPLY");
        JButton forwardBut = new JButton("FORWARD");
        JButton redirectBut = new JButton("REDIRECT");
        bottomPanel.add(newMsgBut);
        bottomPanel.add(replyBut);
        bottomPanel.add(forwardBut);
        bottomPanel.add(redirectBut);
        //------------------------------------------------
        setTitle("Email " + msgNo);
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(topPanel, BorderLayout.NORTH);
        contents.add(email, BorderLayout.CENTER);
        contents.add(bottomPanel, BorderLayout.SOUTH);

        NewMsgActionListener newListener = new NewMsgActionListener(sender);
        newMsgBut.addActionListener(newListener);

        ForwardActionListener fwdListener = new ForwardActionListener();
        forwardBut.addActionListener(fwdListener);

        ReplyActionListener rpyListener = new ReplyActionListener();
        replyBut.addActionListener(rpyListener);

        RedirectActionListener rdtListener = new RedirectActionListener();
        redirectBut.addActionListener(rdtListener);


        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        pack();
    }

    public static void main(String[] args){
        ViewEmail em = new ViewEmail();
        em.setVisible(true);
    }

    public static void setSender(String s){
        sender = s;
    }

    public static void setSubject(String s){
        subject = s;
    }

    public static void setMessage(String m){
        message = m;
    }

    public static void setMsgNo(int i){
        System.out.println(i);
        msgNo = i;
    }

    public static void start(){
        main(null);
    }

}
