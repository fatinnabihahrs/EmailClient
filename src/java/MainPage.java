//this is the gui for the 1st page of the application

import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;

import java.awt.*;
import javax.swing.*;

public class MainPage extends JFrame
{
    private TextField searchBar;
    private Button searchBut;
    private Button nextBut;

    private static String subject;
    private static String sender;
    private static String message;
    private static int msgNo;

    public MainPage()
    {
        //-----------------------------------------------//TOP
        Panel tPanel = new Panel(new FlowLayout());
        searchBar = new TextField("",100);
        searchBut = new Button("SEARCH");
        searchBut.setPreferredSize(new Dimension(70,75));
        //searchBut.setBounds(150,150,80,30);
        //searchBar.setBounds(140,110,200,30);
        tPanel.add(searchBar);
        tPanel.add(searchBut);

        Panel mPanel = new Panel(new FlowLayout());
        int totalM = RetrieveEmailTest.getMsgCount();
        JLabel total = new JLabel("Total Messages: " + totalM);
        mPanel.add(total);

        JButton compButton = new JButton("COMPOSE +");

        NewMsgActionListener newListener = new NewMsgActionListener("");
        compButton.addActionListener(newListener);


        Panel topPanel = new Panel(new GridLayout(0,1)); //TOP-putting those two panels together
        topPanel.add(tPanel);
        topPanel.add(mPanel);
        topPanel.add(compButton);
        //--------------------------------------------------
        Panel middlePanel = new Panel(new GridLayout(0,1));

        String[] sender = new String[totalM];
        String[] subject = new String[totalM];
        int[] no = new int[totalM];

        //fil in the array of the sender and subject of all emails
        for (int i=totalM-1;i>=0;i--){
            sender[i] = RetrieveEmailTest.getSender(i+1);
            subject[i] = RetrieveEmailTest.getSubject(i+1);
            no[i] = i+1;
        }

        Panel[] sumPanel = new Panel[totalM];
        Button[] b = new Button[totalM];


        //put the most recent email at the top
        for (int j=totalM-1;j>=0;j--){
            sumPanel[j] = new Panel(new GridLayout(0,1));
            b[j] = new Button("EMAIL " + (j+1));
            sumPanel[j].add(b[j]);
            sumPanel[j].add(new JLabel("FROM: " + sender[j]));
            sumPanel[j].add(new JLabel("SUBJECT: " + subject[j]));
            middlePanel.add(sumPanel[j]);
        }

        //action listener for the button to view each specific email
        ViewActionListener[] vListener = new ViewActionListener[totalM];

        //a class to store the properties of an email
        Email[] manyEmails = new Email[totalM];


//        //attach listener to the button
////        for (int k=totalM-1;k>=0;k--){
////            System.out.println("Main page: " + no[k]);
////            vListener[k] = new ViewActionListener(sender[k], subject[k], no[k]);
////            System.out.println("Attach " + vListener[k].getEmailNo());
////            b[k].addActionListener(vListener[k]);
////        }
        for (int k=totalM-1;k>=0;k--){
            manyEmails[k] = new Email(sender[k], subject[k], no[k]);


            //specify the action listener for each button
            vListener[k] = new ViewActionListener();
            //ViewActionListener.setEmailNo(k+1);
            b[k].addActionListener(vListener[k]);
        }





        //--------------------------------------------------
        Panel bottomPanel = new Panel(new FlowLayout()); //BOTTOM
        nextBut = new Button("NEXT");
        bottomPanel.add(nextBut);
        //--------------------------------------------------
        setTitle("Basic Email Client");
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(topPanel, BorderLayout.NORTH);
        contents.add(middlePanel, BorderLayout.WEST);
        contents.add(bottomPanel, BorderLayout.SOUTH);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args){
        MainPage em = new MainPage();
        em.setVisible(true);

    }

}
