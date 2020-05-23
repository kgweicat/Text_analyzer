package avltree;

/**
 *
 * @author Che-Wei (Joanne) Chou
 */
public class Node {

    //the actual data in the node
    private Word data;
    
    //the height of the node (to calculate the balance)
    private int height;
    
    //left child of the node
    private Node left;
    
    //right child of the node
    private Node right;
    
    /**
     * Constructor
     * @param object the data about to be put into the node
     */
    protected Node(Word object){
        data = object;
        height = 1;
    }
    
    /**
     * Chang the data in the node to w
     * Why do I even need to comment this
     * @param w 
     */
    protected void setData(Word w){
        data = w;
    }
    
    /**
     * setHeight
     * @param h 
     */
    protected void setHeight(int h){
        height = h;
    }
    
    /**
     * set the left child of the node
     * @param n 
     */
    protected void setLeft(Node n){
        left = n;
    }
   
    /**
     * set the right child of the node
     * @param n 
     */
    protected void setRight(Node n){
        right = n;
    }
    
    /**
     * get data
     * @return what is the word in the node 
     */
    protected Word getData(){
        return data;
    }
    
    /**
     * getHeight
     * @return the height of the node
     */
    protected int getHeight(){
        return height;
    }
    
    /**
     * get the left child of the node
     * @return the left child of a node
     */
    protected Node getLeft(){
        return left;
    }
    
    /**
     * get the right child of the node
     * @return the right child of the node
     */
    protected Node getRight(){
        return right;
    }
}
