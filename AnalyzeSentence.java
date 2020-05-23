import avltree.AVLTree;
import avltree.Word;
import java.util.Scanner;

/**
 *
 * @author Che-Wei (Joanne) Chou
 */
public class AnalyzeSentence {
    
    private static final String[] BAD_WORDS = {"fuck", "bitch", "ass", "asshole", "bastard", "cunt", "shit"};

    public static void main(String[] args) {

        int choice;
        AVLTree tree = new AVLTree();
        Scanner keyboard = new Scanner(System.in);

        do {
            printChoices();
            
            //ask for a choice from the user
            System.out.print("Your Choice: ");
            
            //check if the user input is an integer
            choice = isInt(keyboard);
            keyboard.nextLine();
            while (choice < 1 || choice > 5) { //If number entered is not one of the options
                System.out.println("The number you entered is not a valid choice.");
                System.out.print("Please enter a choice that is on the list: ");
                choice = isInt(keyboard);
            }
            
            switch (choice) {
                case 1:
                    //record the tree in input
                    System.out.println("Enter the sentence you would like to input: ");
                    String line = keyboard.nextLine();
                    record(tree, line);
                    System.out.println();
                    break;
                    
                case 2:
                    if (tree.isEmpty()){
                        //print out an error message if user is trying to print out an empty tree
                        //also prevents exception
                        System.out.println("Error: tree does not exist. Please press 1 and input a message.");
                    } else {
                        //if tree is not empty print the inorder traversal aka sorted words
                        tree.printInorder();
                    }
                    System.out.println();
                    break;
                    
                case 3:
                    //go over each bad words in the errar and clear them
                    for (String s: BAD_WORDS){
                        Word bad = new Word(s, 0);
                        tree.delete(bad);
                    }
                    System.out.println("Tree was cleaned.\n");
                    break;
                    
                case 4:
                    System.out.print("Please enter the word you would like to search for: ");
                    String search = keyboard.nextLine();
                    Word w = tree.search(search);
                    if (w == null){
                        System.out.println("I\'m sorry, but it looks like the word you are looking for has not been input.");
                    } else {
                        System.out.println(w);
                    }
                    System.out.println();
                    break;
                    
            }
        } while (choice != 5);
        
        System.out.println("Done");

    }

    /**
     * print out all choices a user have.
     */
    public static void printChoices() {
        System.out.println("1. Record");
        System.out.println("2. Sort");
        System.out.println("3. Clean");
        System.out.println("4. Find Word");
        System.out.println("5. Done");
    }

    /**
     * put all the words in a new input String into a binary tree.
     * @param t the tree
     * @param str the big string that is input
     */
    public static void record(AVLTree t, String str) {
        str = str.toLowerCase();
        
        //create a scanner for the giant input string
        Scanner input = new Scanner(str);
        
        while (input.hasNext()) {
            //break them up into different tokens
            String token = input.next();
            
            //remove the punctuation at the end
            char c = token.charAt(token.length() - 1);
            while(c == '!' || c == ':' || c == '.' || c == '\"' || c == ',' || c == ';' || c == ')'){
                token = token.substring(0, token.length() - 1);
                c = token.charAt(token.length() - 1);
            }
            
            //renove the punctuation at front
            c = token.charAt(0);
            while(c == '(' || c == '\"'){
                token = token.substring(1);
                c = token.charAt(0);
            }
            
            //search if the words exist. w == null if word is not there
            Word w = t.search(token);
            
            if (w == null) {    //the word does not exist in the binary tree
                //create a new Word object and add it
                int index = str.indexOf(token);
                w = new Word(token, index);
                t.add(w);
            } else {
                //increment the occrrence of the original word
                w.incrementOccurrence();
            }
        }
    }

    /**
     * Keep asking until the input is an integer
     * @param key Scanner must be created, connected to System.in
     * @return the integer from the user input.
     */
    public static int isInt(Scanner key) {
        while (!key.hasNextInt()) {
            //key.nextLine();
            String notAnInt = key.nextLine();
            System.out.println("\n" + notAnInt + " is not an integer.");
            System.out.print("Please enter an integer: ");
        }
        return key.nextInt();
    }
    


}
