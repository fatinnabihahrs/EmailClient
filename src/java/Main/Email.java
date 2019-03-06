package Main;

import java.util.ArrayList;

public class Email {

    private String sender;
    private String subject;
    private String content;
    private ArrayList<String> tags;
    private int no;

    public Email(String send, String subj, String m, ArrayList<String> t,int n){
        sender = send;
        subject = subj;
        content = m;
        tags = t;
        no = n;
    }

    public String getSender(int n){
        return sender;
    }

    public String getSubject(int n){
        return subject;
    }

    public String getContent(int n){
        return content;
    }

    public ArrayList<String> getTags(int n){
        return tags;
    }

}
