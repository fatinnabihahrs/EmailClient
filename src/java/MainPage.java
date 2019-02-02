//this is the gui for the 1st page of the application

import java.awt.*;
import javax.swing.*;

public class MainPage extends JFrame
{
    private TextField searchBar;
    private Button searchBut;
    private Button nextBut;

    public MainPage()
    {
        //-----------------------------------------------
        Panel topPanel = new Panel(new FlowLayout());  //TOP
        searchBar = new TextField("0",100);
        searchBut = new Button("SEARCH");
        topPanel.add(searchBar);
        topPanel.add(searchBut);
        //--------------------------------------------------
        Panel middlePanel = new Panel(new GridLayout(0,1));

        Panel sumPanel1 = new Panel(new GridLayout(0,1));//the summary email panel within middle panel
        sumPanel1.add(new JLabel("-------------------------------"));
        sumPanel1.add(new Button("EMAIL 1"));
        sumPanel1.add(new JLabel("FROM: roda100@yahoo.com"));
        sumPanel1.add(new JLabel("SUBJECT: Meeting Cancelled!"));
        sumPanel1.add(new JLabel("-------------------------------"));
        middlePanel.add(sumPanel1);

        Panel sumPanel2 = new Panel(new GridLayout(0,1));//the summary email panel within middle panel
        sumPanel2.add(new JLabel("-------------------------------"));
        sumPanel2.add(new Button("EMAIL 2"));
        sumPanel2.add(new JLabel("FROM: booklover@gmail.com"));
        sumPanel2.add(new JLabel("SUBJECT: New Book?"));
        sumPanel2.add(new JLabel("-------------------------------"));
        middlePanel.add(sumPanel2);


        Panel sumPanel3 = new Panel(new GridLayout(0,1));//the summary email panel within middle panel
        sumPanel3.add(new JLabel("-------------------------------"));
        sumPanel3.add(new Button("EMAIL 1"));
        sumPanel3.add(new JLabel("FROM: roda100@yahoo.com"));
        sumPanel3.add(new JLabel("SUBJECT: Meeting Cancelled!"));
        sumPanel3.add(new JLabel("-------------------------------"));
        middlePanel.add(sumPanel3);

        Panel sumPanel4 = new Panel(new GridLayout(0,1));//the summary email panel within middle panel
        sumPanel4.add(new JLabel("-------------------------------"));
        sumPanel4.add(new Button("EMAIL 2"));
        sumPanel4.add(new JLabel("FROM: booklover@gmail.com"));
        sumPanel4.add(new JLabel("SUBJECT: New Book?"));
        sumPanel4.add(new JLabel("-------------------------------"));
        middlePanel.add(sumPanel4);


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
