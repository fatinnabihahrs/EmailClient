package Main;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;
import java.util.regex.Pattern;
import java.lang.String;
import java.util.stream.Collectors;

public class SimpleCoreNLPDemo {

    //instance variable
    //public static String text = "";
    public int number;
    public SimpleCoreNLPDemo(){

    }

    public ArrayList<String> getTokens(String t, String subj,int n) {

        String text = t;
        String subject = subj;
        number = n;

        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos");
        //props.setProperty("annotators","tokenize");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = new CoreDocument(text + subject);
        // annnotate the document
        pipeline.annotate(document);
        // examples

        //remove POS tags--> IN(preposition), LS(list item marker), CC(Coordinating conjunction), DT(determiner)



//        List<String> posTags = document.sentences().get(0).posTags();
//        System.out.println(posTags);

        //use a dynamic array
        //List<String> tokensFound = new ArrayList<String>();
        //String tokensFound = "";

        ArrayList<String> newListTokens = new ArrayList<String>();

        //token rep by CoreLabel
        //get the tokens for all the sentences in the text(document)
        for (int i=0;i<document.sentences().size();i++) {
            List<CoreLabel> tokens = document.sentences().get(i).tokens(); //token
            List<String> posTags = document.sentences().get(i).posTags(); //that token's pos tag


            for (int j = 0; j < tokens.size(); j++) {

                //getting NN(noun-singular), NNS(noun-plural), NNP(proper noun-singular), NNPS(proper noun-plural), JJ(adjectives), JJR(adj-comparative), JJS(adj-superlative)
                if (posTags.get(j).equals("NN") || posTags.get(j).equals("NNS") || posTags.get(j).equals("NNP")|| posTags.get(j).equals("NNPS")|| posTags.get(j).equals("JJ") || posTags.get(j).equals("JJR") || (posTags.get(j).equals("JJS"))){
                    if (Pattern.matches("\\p{Punct}", tokens.get(j).word()))
                        ;
                    else
                        //tokensFound = tokensFound + tokens.get(j).word() + " ";
                        newListTokens.add(tokens.get(j).word());
                }


            }
        }
        //System.out.println(newListTokens);
        //return newListTokens;


        //remove duplicates
        StringBuilder sb = new StringBuilder();
        for (String s: newListTokens){
            sb.append(s);
            sb.append(" ");
        }

        List<String> list1 = Arrays.asList(sb.toString().split(" "));
        List<String> list2 = list1.stream().distinct().collect(Collectors.toList());
        newListTokens = (ArrayList)list2;
        System.out.println(newListTokens);
        return newListTokens;




//        String splitTokens[] = newListTokens.split(" ");
//        Map<String, Integer> words = new HashMap<>();
//
//        //count the occurence of each word
//        for (String str: splitTokens){
//            //to handle multiple same words
//            str = str.toLowerCase();
//            if (words.containsKey(str)) {
//                words.put(str, 1 + words.get(str));
//            }
//            else {
//                words.put(str, 1);
//            }
//        }

        //print out how many times each word appears in the text
//        System.out.println(words);


        //for each word in words Map, calculate the weightage
        //double weightNoun = 0.8;
        //double weightAdj = 0.6;

    }

//    public int getNumber(){
//        return number;
//    }
}