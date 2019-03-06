package Main;//this is the gui for the 2nd page of the application - viewing an email
//this page is from the 1st page by pressing on the button email

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Listeners.*;


public class ViewEmail extends JFrame implements ActionListener {

    private static String sender;
    private static String subject;
    private static String message;
    private static int msgNo;


    private static JMenuBar bar;
    private static JMenu menu;
    private static JMenu submenu;
    private static JMenuItem i1, i2, i3;

    //private static JMenuBar tagBar;

    private static String option = "";

    //constructor
    public ViewEmail(){


        //------------------------------------------------MENU BAR AT WINDOW BAR
        bar = new JMenuBar();
        menu = new JMenu("Settings");
        menu.setFont(new Font("Verdana", Font.PLAIN, 20));
        submenu = new JMenu("Reply");
        submenu.setFont(new Font("Verdana", Font.PLAIN, 20));
        i1 = new JMenuItem("Top-posting");
        i1.setFont(new Font("Verdana", Font.PLAIN, 20));
        i2 = new JMenuItem("Bottom-posting");
        i2.setFont(new Font("Verdana", Font.PLAIN, 20));
        i3 = new JMenuItem("Inline-posting");
        i3.setFont(new Font("Verdana", Font.PLAIN, 20));

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);

        bar.add(menu);
        menu.add(submenu);
        submenu.add(i1);
        submenu.add(i2);
        submenu.add(i3);
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
        JButton tagsBut = new JButton("TAGS");
        tagsBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        JButton newMsgBut = new JButton("NEW MESSAGE");
        newMsgBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        JButton replyBut = new JButton("REPLY");
        replyBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        JButton forwardBut = new JButton("FORWARD");
        forwardBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        JButton redirectBut = new JButton("REDIRECT");
        redirectBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        bottomPanel.add(tagsBut);
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

        TagsActionListener tagListener = new TagsActionListener(message);
        tagsBut.addActionListener(tagListener);

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
        em.setJMenuBar(bar);
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

    public static String getOption(){
        return option;
    }

    public void actionPerformed(ActionEvent e){
        //get the source of the button
        JMenuItem source = (JMenuItem) e.getSource();
        option = source.getLabel();
        System.out.println("View email: " + option);
    }

}
