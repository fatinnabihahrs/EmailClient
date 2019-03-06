package Listeners;

import Main.SimpleCoreNLPDemo;
import Main.TagWindow;
import sun.java2d.pipe.SpanShapeRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TagsActionListener implements ActionListener {

    public String text = "";

    public TagsActionListener(String t){
        text = t;
    }

    public void actionPerformed(ActionEvent e){
        System.out.println("TEXT:" + text);
        SimpleCoreNLPDemo simple = new SimpleCoreNLPDemo();
        ArrayList<String> tokens = simple.getTokens(text);
        System.out.println("TOKENS: "+ tokens);
        TagWindow t = new TagWindow(tokens);
        //t.setTagsList(tokens);
        //t.start();
    }
}
