package Main;

import java.util.ArrayList;

public class Email {

    private String sender;
    private String subject;
    private String content;
    private ArrayList<String> tags;
    private int no;
    private String expectedTime;

    public Email(String send, String subj, String m, ArrayList<String> t,int n, String expect){
        sender = send;
        subject = subj;
        content = m;
        tags = t;
        no = n;
        expectedTime = expect;
    }

    public String getSender(){
        return sender;
    }

    public String getSubject(){
        return subject;
    }

    public String getContent(){
        return content;
    }

    public ArrayList<String> getTags(){
        return tags;
    }

    public void setTags(ArrayList<String> t){
        tags = t;
    }

    public void setTime(String s){
        expectedTime = s;
    }

    public String getTime(){
        return expectedTime;
    }

}
