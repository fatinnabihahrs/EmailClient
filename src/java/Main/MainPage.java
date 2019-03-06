package Main;//this is the gui for the 1st page of the application
//put the recent 10 emails at first page, 11-20 on 2nd page, and so on...

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Listeners.*;
import Listeners.ViewActionListener;

public class MainPage extends JFrame implements ActionListener
{
    private static TextField searchBar;
    private static Button searchBut;
    private static Button nextBut;
    private static Button backBut;

    private static String[] senderArray;
    private static String[] messageArray;
    private static String[] subjectArray;

    private static Panel[] sumPanel;
    private static Panel middlePanel;
    private static Button[] b;

    private static JLabel K;
    private static JLabel L;
    private static ViewActionListener[] vListener;


    private static String option = "";
    private static int start;
    private static int totalPanelsPerPage = 10;
    private static int totalM;

    private static int currentPage;
    private static int pages; //total number of pages


    public MainPage()
    {

        //-----------------------------------------------//TOP
        Panel tPanel = new Panel(new FlowLayout());
        searchBar = new TextField("",100);
        searchBar.setFont(new Font("Verdana", Font.PLAIN, 30));
        searchBut = new Button("SEARCH");
        searchBut.setFont(new Font("Verdana", Font.BOLD, 20));
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


        Panel topPanel = new Panel(new GridLayout(0,1)); //TOP-putting those two panels together
        topPanel.add(tPanel);
        topPanel.add(mPanel);
        topPanel.add(compButton);

        middlePanel = new Panel(new GridLayout(0,1));
        senderArray = new String[totalM];
        subjectArray = new String[totalM];
        messageArray = new String[totalM];


        //fil in the array of the sender ,subject, and message of all emails
        for (int i = totalM - 1; i >= 0; i--) {
            senderArray[i] = RetrieveEmailTest.getSender(i + 1);
            System.out.println("[" + (i+1) + "]: " + senderArray[i]);
            subjectArray[i] = RetrieveEmailTest.getSubject(i + 1);
            System.out.println(subjectArray[i]);
            messageArray[i] = RetrieveEmailTest.getMessage(i + 1);
        }


        sumPanel = new Panel[totalPanelsPerPage];
        b = new Button[totalPanelsPerPage];

        //action listener for the button to view each specific email
        vListener = new ViewActionListener[totalPanelsPerPage];

        //determine how many pages needed
        //need 10 emails per page
        int temp = totalM;
        pages = 1;
        while (temp > 10){
            temp = temp - 10;
            pages++;
        }

        //------------------------------------------------------------FROM HERE DOWNWARDS IT WILL CHANGE
        //OBVIOUSLY THIS IS FIRST PAGE

        currentPage = 1;


        //first determine if start email is >10 or <10
        if (totalM > 10) {

            int k = totalPanelsPerPage-1;

            //put the most recent email at the top
            //take only the first 10
            for (int j = totalM - 1; j >= totalM - 1 - 9; j--) {
                    sumPanel[k] = new Panel(new GridLayout(0, 1));
                    b[k] = new Button("" + (j + 1));
                    b[k].setFont(new Font("Verdana", Font.BOLD, 20));
                    sumPanel[k].add(b[k]);
                    K = new JLabel("FROM: " + senderArray[j]);
                    K.setFont(new Font("Verdana", Font.PLAIN, 18));
                    sumPanel[k].add(K);
                    L = new JLabel("SUBJECT: " + subjectArray[j]);
                    L.setFont(new Font("Verdana", Font.PLAIN, 18));
                    sumPanel[k].add(L);
                    middlePanel.add(sumPanel[k]);


                    vListener[k] = new ViewActionListener();
                    b[k].addActionListener(vListener[k]);

                    k--;
            }
        }
//        //1-10
//        else{
//
//            //put the most recent email at the top
//            //take only the first 10
//            for (int j = totalM - 1; j >= 0; j--) {
//                sumPanel[j] = new Panel(new GridLayout(0, 1));
//                b[j] = new Button("" + (j + 1));
//                b[j].setFont(new Font("Verdana", Font.BOLD, 20));
//                sumPanel[j].add(b[j]);
//                K = new JLabel("FROM: " + senderArray[j]);
//                K.setFont(new Font("Verdana", Font.PLAIN, 18));
//                sumPanel[j].add(K);
//                L = new JLabel("SUBJECT: " + subjectArray[j]);
//                L.setFont(new Font("Verdana", Font.PLAIN, 18));
//                sumPanel[j].add(L);
//                middlePanel.add(sumPanel[j]);
//
//                vListener[j] = new ViewActionListener();
//                b[j].addActionListener(vListener[j]);
//            }
//        }

        //--------------------------------------------------
        Panel bottomPanel = new Panel(new FlowLayout()); //BOTTOM
        nextBut = new Button("NEXT");
        nextBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        if (totalM <= 10)
            nextBut.setEnabled(false);

        backBut = new Button("BACK");
        backBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        backBut.setEnabled(false);



        backBut.addActionListener(this);
        nextBut.addActionListener(this);
        bottomPanel.add(backBut);
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
        //em.setJMenuBar(bar);
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


    public void actionPerformed(ActionEvent e){

        //get the source of the button
        Button source = (Button)e.getSource();
        String label = source.getLabel();


        int k = totalPanelsPerPage-1;


        //remove action listener everytime
        for (int j = totalM - 1; j >= totalM - 1 - 9; j--) {
            b[k].removeActionListener(vListener[k]);
            k--;
        }

        k = totalPanelsPerPage-1;

        //NEXT button
        if (label.equals("NEXT")){

            currentPage++;
            if (currentPage == pages) {
                nextBut.setEnabled(false);
                backBut.setEnabled(true);
            }
            else {
                nextBut.setEnabled(true);
                backBut.setEnabled(true);
            }


            start = start - 10;
            if (start > 10) {

                //put the most recent email at the top
                //take only the first 10
                for (int j = start - 1; j >= start - 1 - 9; j--) {

                    b[k].setLabel("" + (j + 1));
                    b[k].setFont(new Font("Verdana", Font.BOLD, 20));

                    String newStr = "FROM: " + senderArray[j];
                    //System.out.println(newStr);
                    K.setText(newStr);
                    //K.paintImmediately(K.getVisibleRect());
                    //K = new JLabel(newStr);
                    K.setFont(new Font("Verdana", Font.PLAIN, 18));

                    newStr = "SUBJECT: " + subjectArray[j];
                    L.setText(newStr);
                    L.setFont(new Font("Verdana", Font.PLAIN, 18));

                    vListener[k] = new ViewActionListener();
                    b[k].addActionListener(vListener[k]);

                    k--;
                }

                this.repaint();

            } else if (start <= 10)  {

                k = totalPanelsPerPage-1;

                for (int j = k; j > start - 1; j--) {
                    b[j].setLabel("" + (j + 1));
                    b[j].setFont(new Font("Verdana", Font.BOLD, 20));

                    K.setText("FROM: " + senderArray[j]);
                    //K.setText("FROM: ");
                    K.setFont(new Font("Verdana", Font.PLAIN, 18));
                    //sumPanel[j].add(K);

                    L.setText("SUBJECT: " + subjectArray[j]);
                    //L.setText("SUBJECT: " );
                    L.setFont(new Font("Verdana", Font.PLAIN, 18));
                    //sumPanel[j].add(L);

                    int B;

                    vListener[j] = new ViewActionListener();
                    b[j].addActionListener(vListener[j]);

                }


                for (int j = start - 1; j >= 0; j--) {
                    b[j].setLabel("" + (j + 1));
                    b[j].setFont(new Font("Verdana", Font.BOLD, 20));

                    //K.setText("FROM: " + senderArray[j]);
                    K.setText("FROM: " + senderArray[j]);
                    K.setFont(new Font("Verdana", Font.PLAIN, 18));
                    //sumPanel[j].add(K);

                    //L.setText("SUBJECT: " + subjectArray[j]);
                    L.setText("SUBJECT: " + subjectArray[j]);
                    L.setFont(new Font("Verdana", Font.PLAIN, 18));
                    //sumPanel[j].add(L);

                    vListener[j] = new ViewActionListener();
                    b[j].addActionListener(vListener[j]);

                }

                this.repaint();


            } else ;
        }
//        //BACK button
        else if (label.equals("BACK")) {

            currentPage--;
            if (currentPage == 1) {
                backBut.setEnabled(false);
                nextBut.setEnabled(true);
            }
            else {
                backBut.setEnabled(true);
                nextBut.setEnabled(true);
            }


            start = start + 10;
            if (start > 10) {

                k = totalPanelsPerPage-1;

                //put the most recent email at the top
                //take only the first 10
                for (int j = start - 1; j >= start - 1 - 9; j--) {

                    int ab;

                    //sumPanel[j] = new Panel(new GridLayout(0, 1));
                    b[k].setLabel("" + (j + 1));
                    b[k].setFont(new Font("Verdana", Font.BOLD, 20));
                    //sumPanel[j].add(b[j]);

                    K.setText("FROM: " + senderArray[j]);
                    //K.setText("FROM: " + senderArray[j]);
                    K.setFont(new Font("Verdana", Font.PLAIN, 18));
                    //sumPanel[j].add(K);

                    L.setText("SUBJECT: " + subjectArray[j]);
                    //L.setText("SUBJECT: " + subjectArray[j]);
                    L.setFont(new Font("Verdana", Font.PLAIN, 18));
                    //sumPanel[j].add(L);
                    //middlePanel.add(sumPanel[j]);

                    vListener[k] = new ViewActionListener();
                    b[k].addActionListener(vListener[k]);

                    k--;
                }

                this.repaint();
            }
        }
        else;

        pack();


    }

}
