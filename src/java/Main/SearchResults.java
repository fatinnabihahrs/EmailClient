package Main;

import Listeners.ViewActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchResults extends JFrame {

    private ArrayList<Integer> emailNumber;

    public SearchResults(ArrayList<Integer> en){

        emailNumber = en;
        Panel onePanel = new Panel(new GridLayout(0,1));

        //if not empty
        if (!en.isEmpty()) {
            JLabel notemptyLabel = new JLabel("List of emails returned based on the input search:");
            notemptyLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            onePanel.add(notemptyLabel);

            Panel[] sumPanel = new Panel[emailNumber.size()];
            Button[] viewb = new Button[emailNumber.size()];
            ViewActionListener[] vListener = new ViewActionListener[emailNumber.size()];

            for (int i = 0; i < emailNumber.size(); i++) {
                sumPanel[i] = new Panel(new GridLayout(0, 1));
                viewb[i] = new Button("" + emailNumber.get(i));
                viewb[i].setFont(new Font("Verdana", Font.BOLD, 20));
                sumPanel[i].add(viewb[i]);
                JLabel K = new JLabel("FROM: " + MainPage.getSender(emailNumber.get(i)));
                K.setFont(new Font("Verdana", Font.PLAIN, 25));
                sumPanel[i].add(K);
                JLabel L = new JLabel("SUBJECT: " + MainPage.getSubject(emailNumber.get(i)));
                L.setFont(new Font("Verdana", Font.PLAIN, 25));
                sumPanel[i].add(L);
                onePanel.add(sumPanel[i]);

                vListener[i] = new ViewActionListener();
                viewb[i].addActionListener(vListener[i]);
            }
        }
        //if empty
        else{
             JLabel emptyLabel = new JLabel("No emails returned based on the input search string");
             emptyLabel.setFont(new Font("Verdana", Font.BOLD, 20));
             onePanel.add(emptyLabel);
        }



        setTitle("Search Results");
        Container contents = getContentPane();
        contents.add(onePanel);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        pack();
    }

//    public static void main(String[] args){
//        SearchResults sr = new SearchResults();
//        sr.setVisible(true);
//    }
//
//    public void setEmailNumber(ArrayList<Integer> list){
//        emailNumber = list;
//    }
}
