package Main;

public class Email {

    private static String sender;
    private static String subject;
    private static int no;

    public Email(String send, String subj, int n){
        sender = send;
        subject = subj;
        no = n;
    }

    public static String getSender(int n){
        return sender;
    }

    public static String getSubject(int n){
        return subject;
    }

}
