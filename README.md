# Text_analyzer
This program breaks up text into words and stores words in an AVL tree.

Since binary search trees have the risk of being unbalanced and making searches less efficient, I further expanded the binary tree written previously into an AVL tree to ensure the binary search tree is balanced.

The program takes as input a string of any size.
It breaks the text input into word objects, removes punctuations, and stores the word object into an AVL tree sorted by alphabetical order.
The word object has an occurrence filed. If there is a duplicate in the AVL tree, the word object occurrence will be incremented.
The text analyzer allows the user to do the following:
- enter text input and record each word into the AVL tree
- print inorder traversal of the AVL tree (the words will be sorted alphabetically by default)
- delete swear words from the AVL tree
- search for a word and print out the information about the word (number of occurrence and the first index where the word appears in the string)
