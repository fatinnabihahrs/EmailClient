package Listeners;

import Main.SimpleCoreNLPDemo;
import Main.TagWindow;
import sun.java2d.pipe.SpanShapeRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TagsActionListener implements ActionListener {

    public String text = "";
    public String subject = "";
    public int number;

    public TagsActionListener(String t, String s, int n){
        text = t;
        subject = s;
        number = n;
    }

    public void actionPerformed(ActionEvent e){
        //System.out.println("TEXT:" + text);
        SimpleCoreNLPDemo simple = new SimpleCoreNLPDemo();
        ArrayList<String> tokens = simple.getTokens(text, subject, number);
        System.out.println("TOKENS: "+ tokens);
        TagWindow t = new TagWindow(tokens, number);
        //t.setTagsList(tokens);
        //t.start();
    }
}
