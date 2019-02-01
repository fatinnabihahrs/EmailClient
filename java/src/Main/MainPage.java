package Main;//this is the gui for the 1st page of the application

import javax.swing.*;
import java.awt.*;
import Listeners.NewMsgActionListener;
import Listeners.ViewActionListener;

public class MainPage extends JFrame
{
    private TextField searchBar;
    private Button searchBut;
    private Button nextBut;

    private static String[] senderArray;
    private static String[] messageArray;
    private static String[] subjectArray;

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
        int totalM = RetrieveEmailTest.getMsgCount(); //total number of messages currently in email account
        JLabel total = new JLabel("Total Messages: " + totalM);
        mPanel.add(total);

        JButton compButton = new JButton("COMPOSE +");

        NewMsgActionListener newListener = new NewMsgActionListener("");
        compButton.addActionListener(newListener);


        Panel topPanel = new Panel(new GridLayout(0,1)); //TOP-putting those two panels together
        topPanel.add(tPanel);
        topPanel.add(mPanel);
        topPanel.add(compButton);
        //--------------------------------------------------FROM HERE ONWARDS- POTENTIAL AREA FOR THE BUG
        Panel middlePanel = new Panel(new GridLayout(0,1));

        senderArray = new String[totalM];
        subjectArray = new String[totalM];
        messageArray = new String[totalM];
        int[] no = new int[totalM];

        //fil in the array of the sender ,subject, and message of all emails
        for (int i=totalM-1;i>=0;i--){
            senderArray[i] = RetrieveEmailTest.getSender(i+1);
            subjectArray[i] = RetrieveEmailTest.getSubject(i+1);
            messageArray[i] = RetrieveEmailTest.getMessage(i+1);
            no[i] = i+1;
        }

        Panel[] sumPanel = new Panel[totalM];
        Button[] b = new Button[totalM];


        //put the most recent email at the top
        for (int j=totalM-1;j>=0;j--){
            sumPanel[j] = new Panel(new GridLayout(0,1));
            b[j] = new Button(""+ (j+1));
            sumPanel[j].add(b[j]);
            sumPanel[j].add(new JLabel("FROM: " + senderArray[j]));
            sumPanel[j].add(new JLabel("SUBJECT: " + subjectArray[j]));
            middlePanel.add(sumPanel[j]);
        }

        ////////////////////----ABOVE PASS

        //action listener for the button to view each specific email
        ViewActionListener[] vListener = new ViewActionListener[totalM];

//        //attach listener to the button
        for (int k=totalM-1;k>=0;k--){
            vListener[k] = new ViewActionListener();
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

    public static String getMessage(int n){
        return messageArray[n-1];
    }
    public static String getSubject(int n){
        return subjectArray[n-1];
    }

    public static String getSender(int n){
        return senderArray[n-1];
    }

}
