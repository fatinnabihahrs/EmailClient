//this class acts like a database to store the tags connected

package Main;
import java.util.*;
import java.util.stream.Collectors;

public class TagEmailNumberMap {

    private static Map<Integer, String> wordsmap;

    public static void main (String[] args){

        //emailNumber --> tags
        wordsmap = new HashMap<Integer, String>();
        //wordsmap = new TreeMap<>(wordsmap);

    }

    public void putNew(int nu, String tag){
        wordsmap.put(nu, tag);
    }

    //return a list of integers
    public ArrayList<Integer> getEmailNu(String tag){
        ArrayList<Integer> emaillist = new ArrayList<>();

        Collection<String> tags = wordsmap.values();
        //Set<Integer> emails = wordsmap.keySet();


        //iterate over the map
        if (!wordsmap.isEmpty()) {
            for (Map.Entry<Integer, String> entry : wordsmap.entrySet()) {
                if (entry.getValue().equals(tag)) {
                    emaillist.add(entry.getKey());
                }
            }
        }

        return emaillist;
    }

    public boolean containsTag(String tag){
        Collection<String> taglist = wordsmap.values();
        return taglist.contains(tag);
    }
}
