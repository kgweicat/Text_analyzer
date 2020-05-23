# Text_analyzer
Break text up into words and store words in an AVL tree.

Since binary search tree has the risk of being unbalanced and make a search less efficient, I further expand the binary tree written previously into an AVL tree to ensure the binary search tree is balanced.

The program take an input string that can be anywhere from one word to a whole book.
It breaks the text input into word objects, remove punctuations, and store the word object into an AVL tree by alphabetical order.
The word object has a occurrence filed. If there is a duplicate in the AVL tree, the word object occurrence will be incremented.
The Text analyzer allow the user to do the following:
- enter text input and record each word into the AVL tree
- print inorder traversal of the AVL tree (the words will be sorted by default)
- delete swear words from the AVL tree
- search for word and print out the information about the word (number of occurrence and the first index where the word appears
