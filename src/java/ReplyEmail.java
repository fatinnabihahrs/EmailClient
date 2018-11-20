import java.awt.*;
import javax.swing.*;

public class ReplyEmail extends JFrame{

    //contructor
    public ReplyEmail(){

        //--------------------------------------------------------
        JTextArea email = new JTextArea(100,100);
        //-------------------------------------------------------
        JPanel bottom = new JPanel(new FlowLayout());
        JButton send = new JButton("SEND");
        bottom.add(send);
        //-------------------------------------------------------

        setTitle("Replying to an Email");
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(email, BorderLayout.CENTER);
        contents.add(bottom, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args){
        ReplyEmail em = new ReplyEmail();
        em.setVisible(true);
    }
}
