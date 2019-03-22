package Main;


//process the text of the email first using NLP
//present options to the user
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TagWindow extends JFrame implements ActionListener {

    private JList<String> tagsList;

    private TextField tagField;
    private JTextArea tagsDisplayedArea;
    private static List<String> selectedList;
    private boolean yesSelect=false;
    private ArrayList<String> tokensList;
    private int emailNu;

    public TagWindow (ArrayList<String> tokens, int n){

        JPanel topP = new JPanel(new GridLayout(0,1));
        JLabel instruction = new JLabel("Please choose the tags appropriate for this email");
        instruction.setFont(new Font("Verdana", Font.PLAIN, 25));
        tokensList = tokens;
        emailNu = n;


        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i=0;i<tokensList.size();i++) {
            listModel.addElement(tokensList.get(i));
        }
        //create the list
        //selectedList.add("");


        tagsList = new JList<>(listModel);
        tagsList.setFont(new Font("Verdana", Font.PLAIN, 25));
        tagsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tagsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    yesSelect = true;
                    selectedList = tagsList.getSelectedValuesList();
                    //selectedList = (ArrayList<>)selectedList;
                    System.out.println(selectedList);
//                    for (int i=0;i<selectedList.size();i++) {
//                        tagsDisplayedArea.setText(selectedList.get(i));
//                    }
                }
            }
        });
        topP.add(instruction);
        topP.add(new JScrollPane(tagsList));

        JPanel leftP = new JPanel(new GridLayout(0, 1));
        JLabel anotherInstruction = new JLabel("Or add a new tag of your choice");
        anotherInstruction.setFont(new Font("Verdana", Font.PLAIN, 20));
        tagField = new TextField("",100);
        tagField.setFont(new Font("Verdana", Font.PLAIN, 30));
        Button showBut = new Button("SHOW");
        showBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        showBut.addActionListener(this);
        leftP.add(anotherInstruction);
        leftP.add(tagField);
        leftP.add(showBut);

        JPanel rightP = new JPanel(new GridLayout(0,1));
        tagsDisplayedArea = new JTextArea();
        tagsDisplayedArea.setFont(new Font("Verdana", Font.PLAIN, 30));
        tagsDisplayedArea.setEditable(false);
        //confirm button
        Button confirmBut = new Button("CONFIRM");
        confirmBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        confirmBut.addActionListener(this);
        Button clearBut = new Button("CLEAR");
        clearBut.setFont(new Font("Verdana", Font.PLAIN, 20));
        clearBut.addActionListener(this);
        JScrollPane scroll = new JScrollPane(tagsDisplayedArea);
        rightP.add(scroll);
        rightP.add(confirmBut);
        rightP.add(clearBut);

        JPanel bottomP = new JPanel(new GridLayout(0,2));
        bottomP.add(leftP);
        bottomP.add(rightP);


        setTitle("Tag Window");
        Container contents = getContentPane();
        contents.setLayout(new GridLayout(0,1));
        contents.add(topP);
        contents.add(bottomP);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(true);
        //pack();
        setSize(750,750); //set the default size of the new view email window

    }

//    public static void main(String[] args){
//        TagWindow t = new TagWindow();
//        t.setVisible(true);
//    }
//
//    public static void start(){
//        main(null);
//    }

    ArrayList<String> ifZero = new ArrayList<>();

    //this sets the tags for each email
    public void actionPerformed(ActionEvent e){
       //first need to know which button was pressed
        String temp;
        Button sourcebut = (Button) e.getSource();
        String source = sourcebut.getLabel();
        System.out.println(source);
        if (source == "SHOW") {
            temp = tagField.getText();
            System.out.println("temp: "+ temp);
            if (!yesSelect) {
                tagsDisplayedArea.setText(temp);
                ifZero.add(temp);
            }
            else {
                selectedList.add(temp);
                System.out.println(selectedList);
                String newL = "";
                for (int i = 0; i < selectedList.size(); i++) {
                    newL = newL + selectedList.get(i) + "\n";
                }
                tagsDisplayedArea.setText(newL);
            }
        }
        else if (source == "CLEAR") {
            tagsDisplayedArea.setText("");
            if (!ifZero.isEmpty())
                ifZero.clear();
            else {
                selectedList.clear();
            }
        }
        else if (source == "CONFIRM"){
            //get the list of tags confirmed by user and set it to that particular email
            String tag = tagsDisplayedArea.getText();
            System.out.println(tag);

            //SimpleCoreNLPDemo simple = new SimpleCoreNLPDemo();
            //int emailNu = simple.getNumber();
            System.out.println("EMAIL NUMBER: **" + emailNu);
            Email[] emails = MainPage.getEmailArray();
            if (!ifZero.isEmpty())
                emails[emailNu - 1].setTags((ArrayList<String>) ifZero);
            else {
                emails[emailNu - 1].setTags((ArrayList<String>) selectedList);
            }



//            //try using the hashmap here
//            //dont allow duplicates here
//            TagEmailNumberMap tagEm = new TagEmailNumberMap();
//            for (int i=0;i<selectedList.size();i++) {
//                //if the map already has the tag
//                    tagEm.putNew(emailNu, selectedList.get(i));
//
//            }
            System.out.println("EMAILS TAGS: **" + emails[emailNu-1].getTags());
            tagsDisplayedArea.setText("Tags set!");

            MainPage.setTags(emailNu, emails[emailNu-1].getTags());
            //ViewEmail vm = new ViewEmail();
            //vm.setTags(emails[emailNu-1].getTags());

        }
        else
            ;



    }

//    public void setTagsList(ArrayList<String> t){
//        tokensList = t;
//    }

}
