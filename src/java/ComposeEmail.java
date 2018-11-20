import java.awt.*;
import javax.swing.*;

public class ComposeEmail extends JFrame {

    private static JTextField recipient;
    private static JTextField subject;
    private static JTextArea email;

    //contructor
    public ComposeEmail(){

        //--------------------------------------------------------
        recipient = new JTextField("Recipient's email",100); //TOP
        subject = new JTextField("Subject", 100);

        Panel topPanel = new Panel(new GridLayout(0,1));
        topPanel.add(recipient);
        topPanel.add(subject);
        //--------------------------------------------------------
        email = new JTextArea(100,100); //CENTER
        //-------------------------------------------------------
        JPanel bottom = new JPanel(new FlowLayout()); //BOTTOM
        JButton sendButton = new JButton("SEND");
        bottom.add(sendButton);
        //-------------------------------------------------------

        setTitle("Composing an Email");
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(topPanel, BorderLayout.NORTH);
        contents.add(email, BorderLayout.CENTER);
        contents.add(bottom, BorderLayout.SOUTH);


        SendActionListener listener = new SendActionListener();
        sendButton.addActionListener(listener);                    //if the sendButton is press, then invoke the method in action listener

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();


    }

    public static void main(String[] args){
        ComposeEmail em = new ComposeEmail();
        em.setVisible(true);
    }


    public static String getRecipient() {
        return recipient.getText();
    }

    public static String getSubject(){
        return subject.getText();
    }

    public static String getEmailMessage(){
        return email.getText();
    }
}
