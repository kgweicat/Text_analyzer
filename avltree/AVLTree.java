package avltree;

/**
 *
 * @author Che-Wei (Joanne) Chou
 */
public class AVLTree {

    private Node root;

    /**
     * constructor - construct an empty tree.
     */
    public AVLTree() {
        root = null;
    }

    /**
     * mainly calls addHelper - add a word to the tree.
     * @param val the Word that is added to the tree.
     */
    public void add(Word val) {
        //assigned new root to the root returned by addHelper
        root = addHelper(root, val);
    }

    /**
     * use recursion to add the Word to the tree and then check for balance and then rotate
     * @param r a Node, the parent (idk how to explain)
     * @param val the Word that is about to be inserted
     * @return recursion. When finish inserting it should return the root.
     */
    private Node addHelper(Node r, Word val) {
        //empty tree
        if (r == null) {
            r = new Node(val);
        } else if (val.compareTo(r.getData()) < 0) {    //word is smaller than parent
            //add to left
            r.setLeft(addHelper(r.getLeft(), val));

        } else {    //word is bigger than parent
            //add to right
            r.setRight(addHelper(r.getRight(), val));

        }

        //update height
        heightUpdate(r);

        //calculate the new balance of the new node
        int b = balance(r);

        //There should usually be four case: right right, right left, left right and left left
        //I used two big if statement to differentiate the left case and the right case
        //instead of how a lot of people usually use four for each case
        if (b > 1) {    //right
            if (val.compareTo(r.getLeft().getData()) > 0) { //left
                r.setLeft(leftRotate(r.getLeft()));
            }
            return rightRotate(r);
        }

        if (b < -1) {   //left
            if (val.compareTo(r.getRight().getData()) < 0) {    //right
                r.setRight(rightRotate(r.getRight()));
            }
            return leftRotate(r);
        }
        
        //return the node
        return r;
    }

    /**
     * Get the balance factor of a node
     * @param n the node we are looking into.
     * @return the balance factor. 0 of node = 0
     * if left tree is bigger, balance factor will be positive, and vice versa
     * if balance factor == 0, left and right tree has the same length
     */
    private int balance(Node n) {
        if (n == null) {
            return 0;
        }
        return height(n.getLeft()) - height(n.getRight());
    }

    /**
     * update the height of a node.
     * @param n the node we are updating height for
     */
    private void heightUpdate(Node n) {
        int right = height(n.getRight());
        int left = height(n.getLeft());
        
        //+1 to account the node itself
        n.setHeight(Math.max(right, left) + 1);
    }
    
    /**
     * basically calls get height, but have a null case to prevent exception
     * @param n the node of which we are getting the height
     * @return the height of the node. 0 if the node is null
     */
    private int height(Node n){
        if (n == null){
            return 0;
        }
        return n.getHeight();
    }

    /**
     * Right rotate
     * @param r root of the subtree
     * @return new root
     */
    private Node rightRotate(Node r) {

        //kid will become new root later
        Node kid = r.getLeft();
        Node grandkid = kid.getRight();

        //rotation
        kid.setRight(r);
        r.setLeft(grandkid);

        //update heights
        heightUpdate(r);
        heightUpdate(kid);

        return kid;
    }

    /**
     * Left rotate
     * @param r root of the subtree
     * @return new root
     */
    private Node leftRotate(Node r) {

        //kid will become new root later
        Node kid = r.getRight();
        Node grandkid = kid.getLeft();

        //rotation
        kid.setLeft(r);
        r.setRight(grandkid);

        //update heights
        heightUpdate(r);
        heightUpdate(kid);

        return kid;
    }

    /**
     * mainly calls delete helper - delete a word
     * @param w the Word that is about to be deleted
     * @return the deleted Word
     */
    public Word delete(Word w) {
        //update the new root aka update the new tree
        root = deleteHelper(root, w);
        return w;
    }

    /**
     * use recursion to delete a Word from the tree and then check for balance and then rotate
     * @param r a Node
     * @param w the word that is about to be deleted
     * @return recursion. When finish deleting it should return the root
     */
    private Node deleteHelper(Node r, Word w) {
        //base case
        if (r == null) {
            return r;
        }
        if (w.compareTo(r.getData()) < 0) {
            //the node is in the left tree
            r.setLeft(deleteHelper(r.getLeft(), w));
        } else if (w.compareTo(r.getData()) > 0) {
            //the node is in the right tree
            r.setRight(deleteHelper(r.getRight(), w));
        } else {
            //data in r equals data in n

            //if n has only one child, return the child
            if (r.getLeft() == null) {
                return r.getRight();
            }
            if (r.getRight() == null) {
                return r.getLeft();
            }

            //if n has two child
            r.setData(successor(r.getRight()));
            deleteHelper(r.getRight(), w);
        }

        //update height
        heightUpdate(r);

        //calculate the new balance of the new node
        int b = balance(r);

        //There should usually be four case: right right, right left, left right and left left
        //I used two big if statement to differentiate the left case and the right case
        //instead of how a lot of people usually use four for each case
        if (b > 1) {    //right
            if (w.compareTo(r.getLeft().getData()) > 0) { //left
                r.setLeft(leftRotate(r.getLeft()));
            }
            return rightRotate(r);
        }

        if (b < -1) {   //left
            if (w.compareTo(r.getRight().getData()) < 0) {    //right
                r.setRight(rightRotate(r.getRight()));
            }
            return leftRotate(r);
        }
        
        return r;

    }

    /**
     * return the inorder successor aka the Word that is bigger and next to it when they are sorted
     * @param r the node that we are getting the inorder successor for
     * @return the Word that is in the successor node
     */
    private Word successor(Node r) {
        Word result = r.getData();
        while (r.getLeft() != null) {
            r = r.getLeft();
            result = r.getData();
        }
        return result;
    }

    /**
     * Calls search helper - search for a word
     * @param str the word we are searching for
     * @return the Word that the word is str
     */
    public Word search(String str) {
        if (searchHelper(root, str) == null) {
            return null;
        }
        Word w = searchHelper(root, str).getData();
        return w;
    }

    /**
     * Use recursion to search for a word
     * @param r a node
     * @param str the word we are searching for
     * @return recursion
     */
    private Node searchHelper(Node r, String str) {
        if (r == null) {
            return r;
        } else if (r.getData().getWord().equals(str)) {
            return r;
        } else if (str.compareTo(r.getData().getWord()) < 0) {
            return searchHelper(r.getLeft(), str);

        } else {
            return searchHelper(r.getRight(), str);
        }
    }

    /**
     * calls print inorder to print out the sorted tree.
     */
    public void printInorder() {
        inorder(root);
    }

    /**
     * use recursion to print the inorder traversal of the tree.
     * @param r a node
     */
    private void inorder(Node r) {
        if (r != null) {
            inorder(r.getLeft());
            System.out.println(r.getData());
            inorder(r.getRight());
        }
    }

    /**
     * Check if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

}
