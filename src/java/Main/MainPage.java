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

    private static JMenuBar bar;
    private static JMenu menu;
    private static JMenu submenu;
    private static JMenuItem i1, i2, i3;

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


    public MainPage()
    {
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

        ReplySettingsActionListener rcpSett = new ReplySettingsActionListener(option);
        i1.addActionListener(rcpSett);
        i2.addActionListener(rcpSett);
        i3.addActionListener(rcpSett);

        bar.add(menu);
        menu.add(submenu);
        submenu.add(i1);
        submenu.add(i2);
        submenu.add(i3);

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
                subjectArray[i] = RetrieveEmailTest.getSubject(i + 1);
                messageArray[i] = RetrieveEmailTest.getMessage(i + 1);
            }


        sumPanel = new Panel[totalPanelsPerPage];
        b = new Button[totalPanelsPerPage];

        //action listener for the button to view each specific email
        vListener = new ViewActionListener[totalPanelsPerPage];

        //------------------------------------------------------------FROM HERE DOWNWARDS IT WILL CHANGE
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
        //1-10
        else{

            //put the most recent email at the top
            //take only the first 10
            for (int j = totalM - 1; j >= 0; j--) {
                sumPanel[j] = new Panel(new GridLayout(0, 1));
                b[j] = new Button("" + (j + 1));
                b[j].setFont(new Font("Verdana", Font.BOLD, 20));
                sumPanel[j].add(b[j]);
                K = new JLabel("FROM: " + senderArray[j]);
                K.setFont(new Font("Verdana", Font.PLAIN, 18));
                sumPanel[j].add(K);
                L = new JLabel("SUBJECT: " + subjectArray[j]);
                L.setFont(new Font("Verdana", Font.PLAIN, 18));
                sumPanel[j].add(L);
                middlePanel.add(sumPanel[j]);

                vListener[j] = new ViewActionListener();
                b[j].addActionListener(vListener[j]);
            }
        }

        //--------------------------------------------------
        Panel bottomPanel = new Panel(new FlowLayout()); //BOTTOM
        nextBut = new Button("NEXT");
        nextBut.setFont(new Font("Verdana", Font.PLAIN, 20));

        backBut = new Button("BACK");
        backBut.setFont(new Font("Verdana", Font.PLAIN, 20));

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
        em.setJMenuBar(bar);
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

        //NEXT button
        if (label.equals("NEXT")){


            System.out.print("CURRENT START: "  + start);

            if (start > 0) {

                start = start - 10;
                if (start > 10) {

                    //put the most recent email at the top
                    //take only the first 10
                    for (int j = start - 1; j >= start - 1 - 9; j--) {

                        //sumPanel[j] = new Panel(new GridLayout(0, 1));
                        b[k].setLabel("" + (j + 1));
                        b[k].setFont(new Font("Verdana", Font.BOLD, 20));
                        //sumPanel[j].add(b[j]);

                        K.setText("FROM: " + senderArray[j]);
                        K.setFont(new Font("Verdana", Font.PLAIN, 18));
                        //sumPanel[j].add(K);

                        L.setText("SUBJECT: " + subjectArray[j]);
                        L.setFont(new Font("Verdana", Font.PLAIN, 18));
                        //sumPanel[j].add(L);
                        //middlePanel.add(sumPanel[j]);

                        vListener[k] = new ViewActionListener();
                        b[k].addActionListener(vListener[k]);

                        k--;
                    }
                } else if ((start > 0) && (start <= 10)) {

                    for (int j = k; k >= start - 1; j--) {
                        b[j].setLabel("" + (j + 1));
                        b[j].setFont(new Font("Verdana", Font.BOLD, 20));

                        K.setText("FROM: " + senderArray[j]);
                        K.setFont(new Font("Verdana", Font.PLAIN, 18));


                        L.setText("SUBJECT: " + subjectArray[j]);
                        L.setFont(new Font("Verdana", Font.PLAIN, 18));

                        int B;

                        vListener[j] = new ViewActionListener();
                        b[j].addActionListener(vListener[j]);

                    }


                    for (int j = start - 1; j >= 0; j--) {

                        b[j].setLabel("" + (j + 1));
                        b[j].setFont(new Font("Verdana", Font.BOLD, 20));

                        K.setText("FROM: " + senderArray[j]);
                        K.setFont(new Font("Verdana", Font.PLAIN, 18));


                        L.setText("SUBJECT: " + subjectArray[j]);
                        L.setFont(new Font("Verdana", Font.PLAIN, 18));


                        vListener[j] = new ViewActionListener();
                        b[j].addActionListener(vListener[j]);

                    }
                } else ;

                if (start < 0)
                    start = start + 10;
            }
        }
        //BACK button
        else if (label.equals("BACK")) {


           if (start < totalM) {

               start = start + 10;
                if (start > 10) {

                    //put the most recent email at the top
                    //take only the first 10
                    for (int j = start - 1; j >= start - 1 - 9; j--) {

                        int ab;

                        //sumPanel[j] = new Panel(new GridLayout(0, 1));
                        b[k].setLabel("" + (j + 1));
                        b[k].setFont(new Font("Verdana", Font.BOLD, 20));
                        //sumPanel[j].add(b[j]);

                        K.setText("FROM: " + senderArray[j]);
                        K.setFont(new Font("Verdana", Font.PLAIN, 18));
                        //sumPanel[j].add(K);

                        L.setText("SUBJECT: " + subjectArray[j]);
                        L.setFont(new Font("Verdana", Font.PLAIN, 18));
                        //sumPanel[j].add(L);
                        //middlePanel.add(sumPanel[j]);

                        vListener[k] = new ViewActionListener();
                        b[k].addActionListener(vListener[k]);

                        k--;
                    }
                }

            }
        }
        else;

        pack();


    }

}
