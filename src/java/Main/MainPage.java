package Main;//this is the gui for the 1st page of the application
//put the recent 10 emails at first page, 11-20 on 2nd page, and so on...

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Listeners.*;
import Listeners.ViewActionListener;

import java.util.Date;
import java.text.*;

public class MainPage extends JFrame implements ActionListener
{
    private static TextField searchBar;
    private static Button searchBut;
    private static Button nextBut;
    private static Button backBut;

    private static Email[] emailArray;

    private static Panel[] sumPanel;
    private static Panel topPanel;
    private static Panel middlePanel;
    private static Button[] b;

    private static JLabel K;
    private static JLabel L;
    private static ViewActionListener[] vListener;


    private static String option = "";
    private static int start;
    private static int totalPanelsPerPage;
    private static int totalM;

//    private static int currentPage;
//    private static int pages; //total number of pages


    private static JMenuBar bar;
    private static JMenu menu;
    private static JMenu submenu;
    private static JMenuItem i1, i2;

    private boolean normalSort = true;

    private ArrayList<Integer> priorityList = new ArrayList<>();
    private ArrayList<Integer> finalPriorityList = new ArrayList<>();

    private Container contents;
    private int j;

    private JCheckBox[] checks;


    public MainPage()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        //------------------------------------------------MENU BAR AT WINDOW BAR
        bar = new JMenuBar();
        menu = new JMenu("Settings");
        menu.setFont(new Font("Verdana", Font.PLAIN, 20));
        submenu = new JMenu("Sort Emails");
        submenu.setFont(new Font("Verdana", Font.PLAIN, 20));
        i1 = new JMenuItem("Default(normal sort)");
        i1.setFont(new Font("Verdana", Font.PLAIN, 20));
        i2 = new JMenuItem("Priority(urgency)");
        i2.setFont(new Font("Verdana", Font.PLAIN, 20));

        i1.addActionListener(this);
        i2.addActionListener(this);

        bar.add(menu);
        menu.add(submenu);
        submenu.add(i1);
        submenu.add(i2);
        //-----------------------------------------------//TOP
        Panel tPanel = new Panel(new FlowLayout());
        searchBar = new TextField("",100);
        searchBar.setFont(new Font("Verdana", Font.PLAIN, 30));
        searchBut = new Button("SEARCH");
        searchBut.setFont(new Font("Verdana", Font.BOLD, 20));
        SearchActionListener searchListener = new SearchActionListener();
        searchBut.addActionListener(searchListener);
        tPanel.add(searchBar);
        tPanel.add(searchBut);

        Panel mPanel = new Panel(new FlowLayout());
        totalM = RetrieveEmailTest.getMsgCount(); //total number of messages currently in email account
        start = totalM;
        JLabel total = new JLabel("Total Messages: " + totalM);
        total.setFont(new Font("Verdana", Font.BOLD, 20));
        mPanel.add(total);

        JButton compButton = new JButton("COMPOSE +");
        compButton.setFont(new Font("Verdana", Font.BOLD, 20));

        ComposeActionListener cListener = new ComposeActionListener();
        compButton.addActionListener(cListener);


        topPanel = new Panel(new GridLayout(0,1)); //TOP-putting those two panels together
        topPanel.add(tPanel);
        topPanel.add(mPanel);
        topPanel.add(compButton);

        middlePanel = new Panel(new GridLayout(0,1));

        //prob try to create an array of Email
        emailArray = new Email[totalM];
        sumPanel = new Panel[totalM];
        b = new Button[totalM];

        //action listener for the button to view each specific email
        vListener = new ViewActionListener[totalM];

        for (int j = totalM - 1; j >= 0; j--) {
            String sender = RetrieveEmailTest.getSender(j+1);
            String subject = RetrieveEmailTest.getSubject(j+1);
            String message = RetrieveEmailTest.getMessage(j+1);
            String time = RetrieveEmailTest.getTime(j+1);
            System.out.println("EMAIL: " + (j+1));
            System.out.println("SENDER: " + sender);
            System.out.println("SUBJECT: " + subject);
            System.out.println("MESSAGE: " + message);
            ArrayList<String> tags = new ArrayList<>();
            emailArray[j] = new Email(sender, subject, message, tags, j+1, time);
        }

        getPriorityList();

        sortEmails("Default(normal sort)");
        //--------------------------------------------------
        Panel bottomPanel = new Panel(new FlowLayout()); //BOTTOM
        nextBut = new Button("NEXT");
        nextBut.setFont(new Font("Verdana", Font.PLAIN, 20));

        backBut = new Button("BACK");
        backBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        backBut.setEnabled(false);

        backBut.addActionListener(this);
        nextBut.addActionListener(this);
        bottomPanel.add(backBut);
        bottomPanel.add(nextBut);

        setTitle("Basic Email Client");
        contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(topPanel, BorderLayout.NORTH);
        contents.add(middlePanel, BorderLayout.WEST);
        contents.add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }


    public static void main(String[] args){
        MainPage em = new MainPage();
        em.setJMenuBar(bar);
        em.setVisible(true);
    }


    public static String getMessage(int n){
        return emailArray[n-1].getContent();
    }
    public static String getSubject(int n){
        return emailArray[n-1].getSubject();
    }

    public static String getSender(int n){
        return emailArray[n-1].getSender();
    }

    public static Email[] getEmailArray(){
        return emailArray;
    }

    public static ArrayList<String> getTags(int n){
    return emailArray[n-1].getTags();
}

    public static void setTags(int n, ArrayList<String> t){
        emailArray[n-1].setTags(t);
    }

    public void setTime(int n, String s){
        emailArray[n-1].setTime(s);
    }

    public String getTime(int n){
        return emailArray[n-1].getTime();
    }

    public static TextField getSearchBar(){
        return searchBar;
    }

    public static int getTotalM(){
        return totalM;
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        String sortOption = source.getLabel();
        System.out.println("SORT OPTION: " + sortOption + "FINAL PRIORITY LIST: " + finalPriorityList);

        //remove the panels associated first
        for (int j = totalM - 1; j >= 0; j--) {
            middlePanel.remove(sumPanel[j]);
        }

        sortEmails(sortOption);

    }


    public void getPriorityList(){
        //produce a list of emails in priority order
        ArrayList<Integer> extras = new ArrayList<>();
        //compare with the all emails that has the 'time' header
        int count = 0;
        int countT = 0;
        //RetrieveEmailTest rm = new RetrieveEmailTest();

        for (int j = totalM - 1; j >= 0; j--) {
            if (emailArray[j].getTime().equals("")){
                extras.add(j+1);
                count++;
            }
            else{
                priorityList.add(j+1);
                countT++;
            }
        }

        System.out.println(priorityList + "\n" + extras);

        String[] timeFirst = new String[6];
        String[] timeSec = new String[6];
        int smallest;

        //ArrayList<Integer> updatedList = new ArrayList<>();
        for (int j = 0; j < priorityList.size()-1; j++) {
            //get the first element and second element to compare
            int first = priorityList.get(j);
            int second = priorityList.get(j + 1);

            String nu = this.getTime(first);
            timeFirst = nu.split("/|:| ");

            nu = this.getTime(second);
            timeSec = nu.split("/|:| ");

                //compare year first
            if (Integer.parseInt(timeFirst[0]) < Integer.parseInt(timeSec[0])){
                priorityList.set(j, first);
                priorityList.set(j+1, second);
            }
            else if ((Integer.parseInt(timeFirst[0]) == Integer.parseInt(timeSec[0])) && (Integer.parseInt(timeFirst[1]) < Integer.parseInt(timeSec[1]))) {
                priorityList.set(j, first);
                priorityList.set(j + 1, second);
            }
            else if ((Integer.parseInt(timeFirst[0]) == Integer.parseInt(timeSec[0])) && (Integer.parseInt(timeFirst[1]) ==  Integer.parseInt(timeSec[1])) && (Integer.parseInt(timeFirst[2]) < Integer.parseInt(timeSec[2]))){
                        priorityList.set(j, first);
                        priorityList.set(j+1, second);
            }
            else if ((Integer.parseInt(timeFirst[0]) == Integer.parseInt(timeSec[0])) && (Integer.parseInt(timeFirst[1]) ==  Integer.parseInt(timeSec[1])) && (Integer.parseInt(timeFirst[2]) == Integer.parseInt(timeSec[2]))){
                        priorityList.set(j, first);
                        priorityList.set(j+1, second);
            }
            else if ((Integer.parseInt(timeFirst[0]) == Integer.parseInt(timeSec[0])) && (Integer.parseInt(timeFirst[1]) ==  Integer.parseInt(timeSec[1])) && (Integer.parseInt(timeFirst[2]) > Integer.parseInt(timeSec[2]))){
                        priorityList.set(j, second);
                        priorityList.set(j+1, first);
            }
            else if ((Integer.parseInt(timeFirst[0]) == Integer.parseInt(timeSec[0])) && (Integer.parseInt(timeFirst[1]) > Integer.parseInt(timeSec[1]))) {
                    priorityList.set(j, second);
                    priorityList.set(j+1, first);
            }
            else if (Integer.parseInt(timeFirst[0]) > Integer.parseInt(timeSec[0])){
                priorityList.set(j, second);
                priorityList.set(j+1, first);
            }
            else
                ;
            System.out.println(priorityList);
        }


        System.out.println("PL: " + priorityList);
        //System.out.println("EXTRAS: " + extras);

        //print out the list of the emails in order of priority
        finalPriorityList = new ArrayList<>(priorityList);
        finalPriorityList.addAll(extras);
        System.out.println("FINAL PRIORITY LIST: " + finalPriorityList);
    }

    public void sortEmails(String option){
        if (option.equals("Default(normal sort)")){
            for (int j = totalM - 1; j >= 0; j--) {
                sumPanel[j] = new Panel(new GridLayout(0, 1));
                b[j] = new Button("" + (j + 1));
                b[j].setFont(new Font("Verdana", Font.BOLD, 20));
                sumPanel[j].add(b[j]);
                K = new JLabel("FROM: " + emailArray[j].getSender());
                K.setFont(new Font("Verdana", Font.PLAIN, 25));
                sumPanel[j].add(K);
                L = new JLabel("SUBJECT: " + emailArray[j].getSubject());
                L.setFont(new Font("Verdana", Font.PLAIN, 25));
                sumPanel[j].add(L);
                middlePanel.add(sumPanel[j]);


                vListener[j] = new ViewActionListener();
                b[j].addActionListener(vListener[j]);
            }
        }
        else if ((option.equals("Priority(urgency)"))){
            int index = 0;
            JLabel[] Time = new JLabel[totalM];
            checks = new JCheckBox[totalM];
            for (j = totalM - 1; j >= 0; j--) {
                sumPanel[j] = new Panel(new GridLayout(0, 1));
                b[j] = new Button("" + finalPriorityList.get(index));
                b[j].setFont(new Font("Verdana", Font.BOLD, 20));
                sumPanel[j].add(b[j]);
                checks[j] = new JCheckBox("DONE?", false);
                checks[j].setFont(new Font("Verdana", Font.BOLD, 20));
                sumPanel[j].add(checks[j]);
                K = new JLabel("FROM: " + emailArray[finalPriorityList.get(index)-1].getSender());
                K.setFont(new Font("Verdana", Font.PLAIN, 25));
                sumPanel[j].add(K);
                L = new JLabel("SUBJECT: " + emailArray[finalPriorityList.get(index)-1].getSubject());
                L.setFont(new Font("Verdana", Font.PLAIN, 25));
                sumPanel[j].add(L);
                Time[j] = new JLabel("DATE EXPECTED A REPLY: " + this.getTime(finalPriorityList.get(index)));
                Time[j].setFont(new Font("Verdana", Font.BOLD, 20));
                sumPanel[j].add(Time[j]);

                middlePanel.add(sumPanel[j]);

                vListener[finalPriorityList.get(index)-1] = new ViewActionListener();
                b[j].addActionListener(vListener[finalPriorityList.get(index)-1]);

                index++;
            }
        }

       middlePanel.revalidate();
       middlePanel.repaint();
       pack();
    }


}
