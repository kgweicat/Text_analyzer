package avltree;

/**
 *
 * @author Che-Wei Chou
 */
public class Word implements Comparable{
    
    //the actual word apparently
    private String word;
    
    //how many times the word has occurred in all the input strings
    private int occurrence;
    
    //the index of the first occurrence of the word
    private int firstPlace;
    
    /**
     * Constructor
     * @param str the word that is about to be put in the actual Word object
     * @param index the index of the first occurrence of the word
     */
    public Word(String str, int index){
        word = str;
        //because we re making it so of course it is the first time it occur
        occurrence = 1;
        firstPlace = index;
    }
  
    /**
     * get the actual word in the Word object
     * @return the actual word in this Word object
     */
    public String getWord(){
        return word;
    }
    
    /**
     * Disregard the occurrence and first index, only compare the String
     * throw an exception if the programmer is comparing the wrong thing
     * @param obj another Word object
     * @return if this Word has a bigger String than the other or not (if this Word is smaller, it will be negative)
     */
    @Override
    public int compareTo(Object obj){
        if (obj instanceof Word){
            Word w = (Word)obj;
            return word.compareTo(w.getWord());
        }
        throw new IllegalArgumentException();
    }
    
    /**
     * increment the occurrence of the Word by one
     */
    public void incrementOccurrence(){
        occurrence++;
    }
    
    /**
     * Print out of the information about the word
     * @return a String of all the information of the word
     */
    @Override
    public String toString(){
        String str = "";
        str += word;
        str += "\nnumber of times the word appeared in the string: " + occurrence;
        str += "\nfirst index of the word in the string: " + firstPlace + "\n";
        return str;
    }
}
