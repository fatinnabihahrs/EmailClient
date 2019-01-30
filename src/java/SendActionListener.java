import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendActionListener implements ActionListener{

    public SendActionListener(){

    }

    public void actionPerformed(ActionEvent event){
        String recp = ComposeEmail.getRecipient();
        String subj = ComposeEmail.getSubject();
        String msg = ComposeEmail.getEmailMessage();

        SendingEmail.setMessage(msg);
        SendingEmail.setRecp(recp);
        SendingEmail.setSubject(subj);
        SendingEmail.start();           //invoke the main method
    }

}
