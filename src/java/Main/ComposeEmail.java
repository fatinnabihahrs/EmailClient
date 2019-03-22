package Main;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;


import Listeners.SendActionListener;

public class ComposeEmail extends JFrame implements ActionListener{

    private static TextField recipientField;
    private static TextField subjectField;
    private static JTextArea emailField;
    private static StyledDocument doc;

    private static String recipient = "";
    private static String subject = "";
    private static String message = "";

    private boolean setInLine;

    //used here in actionPerformed method
    private int count = 1;
    private static String d = "";
    private static String m = "";
    private static String y = "";
    private static String t_hour = "";
    private static String t_min = "";
    private static String time = "";

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

        Panel middlebetween = new Panel(new FlowLayout());
        Integer[] day = new Integer[31];
        Integer[] month = new Integer[12];
        Integer[] year = new Integer[10];
        Integer[] time_hour = new Integer[24];
        Integer[] time_min = new Integer[60];

        int count = 1;
        for(int i=0;i<day.length;i++){
            day[i] = count;
            count++;
        }

        count = 1;
        for(int i=0;i<month.length;i++){
            month[i] = count;
            count++;
        }

        count = 2019;
        for(int i=0;i<year.length;i++){
            year[i] = count;
            count++;
        }

        count = 0;
        for(int i=0;i<time_hour.length;i++){
            time_hour[i] = count;
            count++;
        }

        count = 0;
        for(int i=0;i<time_min.length;i++){
            time_min[i] = count;
            count++;
        }

        JComboBox<Integer> cbDay = new JComboBox<Integer>(day);
        cbDay.setFont(new Font("Verdana", Font.PLAIN, 20));
        JComboBox<Integer> cbMonth = new JComboBox<Integer>(month);
        cbMonth.setFont(new Font("Verdana", Font.PLAIN, 20));
        JComboBox<Integer> cbYear = new JComboBox<Integer>(year);
        cbYear.setFont(new Font("Verdana", Font.PLAIN, 20));
        JComboBox<Integer> cbTimeHour = new JComboBox<Integer>(time_hour);
        cbTimeHour.setFont(new Font("Verdana", Font.PLAIN, 20));
        JComboBox<Integer> cbTimeMin = new JComboBox<Integer>(time_min);
        cbTimeMin.setFont(new Font("Verdana", Font.PLAIN, 20));

        cbDay.addActionListener(this);
        cbMonth.addActionListener(this);
        cbYear.addActionListener(this);
        cbTimeHour.addActionListener(this);
        cbTimeMin.addActionListener(this);


        cbDay.setVisible(true);
        cbMonth.setVisible(true);
        cbYear.setVisible(true);
        cbTimeHour.setVisible(true);
        cbTimeMin.setVisible(true);

        JLabel dayL = new JLabel("Day");
        dayL.setFont(new Font("Verdana", Font.PLAIN, 20));
        JLabel monthL = new JLabel("Month");
        monthL.setFont(new Font("Verdana", Font.PLAIN, 20));
        JLabel yearL = new JLabel("Year");
        yearL.setFont(new Font("Verdana", Font.PLAIN, 20));
        JLabel minL = new JLabel("Minutes");
        minL.setFont(new Font("Verdana", Font.PLAIN, 20));
        JLabel hourL = new JLabel("Hour");
        hourL.setFont(new Font("Verdana", Font.PLAIN, 20));

        JLabel instr = new JLabel("Enter date and time to inform receiver when you expect a reply");
        instr.setFont(new Font("Verdana", Font.PLAIN, 20));

        middlebetween.add(instr);
        middlebetween.add(cbDay);
        middlebetween.add(dayL);
        middlebetween.add(cbMonth);
        middlebetween.add(monthL);
        middlebetween.add(cbYear);
        middlebetween.add(yearL);
        middlebetween.add(cbTimeHour);
        middlebetween.add(hourL);
        middlebetween.add(cbTimeMin);
        middlebetween.add(minL);


        Panel topPanel = new Panel(new GridLayout(0,1));
        topPanel.add(p1);
        topPanel.add(p2);
        topPanel.add(middlebetween);
        //--------------------------------------------------------
        StyledDocument doc = new DefaultStyledDocument();
        if (message == "")
            emailField = new JTextArea(100, 100); //CENTER
        else {
            if (setInLine) {
                emailField = new JTextArea("\n " + message, 100, 100);
            }
            else {
                emailField = new JTextArea("\n" + message, 100, 100);
            }
            emailField.setCaretPosition(0);
        }
        emailField.setForeground(Color.BLACK);
        emailField.setFont(new Font("Verdana", Font.BOLD, 30));


        //ONLY FOR INLINE POSTING
        //highlight the text in email
        emailField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    int end = emailField.getSelectionEnd();
                    emailField.setCaretPosition(end);
                    emailField.insert( "\n\t>\n",emailField.getCaretPosition());
                    System.out.println("HERE: " + emailField.getSelectedText());
                }
            }
        });

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
        em.setVisible(true);
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
    public static void setEmailMessage(String s) {
        message = s;
    }


    public static JTextArea getEmailField(){
        return emailField;
    }


    public void setInLine(boolean b){
        setInLine = b;
    }

    public static StyledDocument getDoc(){
        return doc;
    }

    public void actionPerformed(ActionEvent e){
        JComboBox<Integer> source = (JComboBox<Integer>) e.getSource();
        int whichone = source.getItemCount();
        if (whichone == 31) {
            d = source.getSelectedItem().toString();
            if (Integer.parseInt(d) < 10)
                d = "0" + d;
            System.out.println("DAY CHOSEN: " + d);
            count++;
        }
        else if (whichone == 12) {
            m = source.getSelectedItem().toString();
            if (Integer.parseInt(m) < 10)
                m = "0" + m;
            System.out.println("MONTH CHOSEN " + m);
            count++;
        }
        else if (whichone == 10) {
            y = source.getSelectedItem().toString();
            System.out.println("YEAR CHOSEN " + y);
            count++;
        }
        else if (whichone == 24) {
            t_hour = source.getSelectedItem().toString();
            if (Integer.parseInt(t_hour) < 10)
                t_hour = "0" + t_hour;
            System.out.println("TIME_HOUR " + t_hour);
            count++;
        }
        else if (whichone == 60) {
            t_min = source.getSelectedItem().toString();
            if (Integer.parseInt(t_min) < 10)
                t_min = "0" + t_min;
            System.out.println("TIME_MIN " + t_min);
            count++;
        }
        else
            ;
    }

    public static String getTime(){
        //no time is set
        if (d.equals("") || m.equals("") || y.equals("")|| t_hour.equals("") || t_min.equals(""))
            time = "";
        else {
            time = "" + y + "/" + m + "/" + d + " " + t_hour + ":" + t_min + ":00";
        }

        //set to empty
        y = "";
        m = "";
        d = "";
        t_min = "";
        t_hour = "";
        return time;
    }

}
