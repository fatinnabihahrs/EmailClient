package Main;//this is the gui for the 2nd page of the application - viewing an email
//this page is from the 1st page by pressing on the button email

import javax.swing.*;
import java.awt.*;
import Listeners.*;


public class ViewEmail extends JFrame{

    private static String sender;
    private static String subject;
    private static String message;
    private static int msgNo;

    //constructor
    public ViewEmail(){

        //-----------------------------------------------
        Panel topPanel = new Panel(new GridLayout(2,0));  //TOP

        JLabel j = new JLabel("FROM: " + sender);
        j.setFont(new Font("Verdana", Font.BOLD, 18));

        JLabel k = new JLabel("SUBJECT: " + subject);
        k.setFont(new Font("Verdana", Font.BOLD, 18));

        topPanel.add(j);
        topPanel.add(k);
        //------------------------------------------------
        JLabel email = new JLabel("<html>"  + message + "</html>");  //MIDDLE
        //JLabel email = new JLabel(message);  //MIDDLE
        email.setFont(new Font("Verdana", Font.BOLD, 18));
        //------------------------------------------------
        Panel bottomPanel = new Panel(new FlowLayout());        //BOTTOM
        JButton newMsgBut = new JButton("NEW MESSAGE");
        newMsgBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        JButton replyBut = new JButton("REPLY");
        replyBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        JButton forwardBut = new JButton("FORWARD");
        forwardBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        JButton redirectBut = new JButton("REDIRECT");
        redirectBut.setFont(new Font("Verdana", Font.PLAIN, 20));
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

        NewMsgActionListener newListener = new NewMsgActionListener(sender);  //DONE
        newMsgBut.addActionListener(newListener);

        ForwardActionListener fwdListener = new ForwardActionListener(subject, message); //DONE
        forwardBut.addActionListener(fwdListener);

        //give the user the option to reply with top, bottom or inline posting (posting style)
        ReplyActionListener rpyListener = new ReplyActionListener(sender, subject, message);
        replyBut.addActionListener(rpyListener);

        RedirectActionListener rdtListener = new RedirectActionListener(sender,subject,message); //DONE MOSTLY??
        redirectBut.addActionListener(rdtListener);


        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        //pack();
        setSize(750,750); //set the default size of the new view email window
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
