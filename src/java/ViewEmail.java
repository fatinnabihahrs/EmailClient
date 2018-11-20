//this is the gui for the 2nd page of the application - viewing an email
//this page is from the 1st page by pressing on the button email

import java.awt.*;
import javax.swing.*;

public class ViewEmail extends JFrame{

    //constructor
    public ViewEmail(){

        //-----------------------------------------------
        Panel topPanel = new Panel(new GridLayout(2,0));  //TOP
        topPanel.add(new JLabel("SUBJECT: Book Talk Cancelled"));
        topPanel.add(new JLabel("FROM: mirodk4@yahoo.com"));
        //------------------------------------------------
        JLabel email = new JLabel("IODJFILJFOJFPODKFPODKFKK FJDFODS FSFJ PSFOPDKFSPMFSIF OFESJF OF ESFSPKPDSFJ FJOEF");  //MIDDLE
        //------------------------------------------------
        Panel bottomPanel = new Panel(new FlowLayout());        //BOTTOM
        bottomPanel.add(new JButton("REPLY"));
        bottomPanel.add(new JButton("FORWARD"));
        bottomPanel.add(new JButton("REDIRECT"));
        //------------------------------------------------
        setTitle("Viewing an email");
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(topPanel, BorderLayout.NORTH);
        contents.add(email, BorderLayout.CENTER);
        contents.add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args){
        ViewEmail em = new ViewEmail();
        em.setVisible(true);
    }

}
