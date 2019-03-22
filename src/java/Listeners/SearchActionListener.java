package Listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Main.*;

public class SearchActionListener implements ActionListener {

    public SearchActionListener(){

    }

    public void actionPerformed(ActionEvent e){

        //need to get the results of the search based on the input from the user
        //MainPage mp = new MainPage();
        String inputSearch = MainPage.getSearchBar().getText();
        System.out.println("INPUT SEARCH:" + inputSearch);

        //open up the search results page
        //Email[] emailArray = MainPage.getEmailArray();
        //System.out.println("Size of email array: "+ emailArray.length);
        ArrayList<Integer> emailsSearched = new ArrayList<Integer>();
        //int emailsSearched = 0;

//        for (int i=0;i<emailArray.length;i++){
//            ArrayList<String> tags = emailArray[i].getTags(i + 1);
//            System.out.println(tags);
//        }

//        TagEmailNumberMap tagEm = new TagEmailNumberMap();
//        ArrayList<Integer> emList = tagEm.getEmailNu(inputSearch);

        int total = Main.MainPage.getTotalM();
//        System.out.println("TOTAL M: " + total);
        System.out.println("PRINT EMAIL OF ALL TAGS");

        int i = total-1;
        while(i >= 0){
                ArrayList<String> tags = Main.MainPage.getTags(i + 1);
                System.out.println("EMAIL: " + (i+1) + " " + tags);
                for (int j = 0; j < tags.size(); j++) {
                    if (tags.get(j).equals(inputSearch)){
//                        System.out.println("ENTER HERE!!");
                        emailsSearched.add(i+1);
                        break;
                    }
                }
                i--;
        }

       System.out.println("LIST OF EMAILS IN THE SEARCH:" + emailsSearched);


        //open up a new window that has the search emails
        SearchResults sr = new SearchResults(emailsSearched);
        sr.setVisible(true);


    }
}
