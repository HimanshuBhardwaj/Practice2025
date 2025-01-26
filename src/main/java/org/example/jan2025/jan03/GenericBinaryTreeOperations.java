package org.example.jan2025.jan03;

public class GenericBinaryTreeOperations {
    public static void main(String[] args) {
        TreeI<String>  tree = new Tree<>();
        Node<String> root = tree.insert(null,"1");
        root = tree.insert(root,"2");
        root = tree.insert(root,"9");
        root = tree.insert(root,"0");
        root = tree.insert(root,"5");
        root=tree.insert(root,"-1");
        printTreeData(root,tree);
        root = tree.delete(root,"-10");
        printTreeData(root,tree);
        root = tree.delete(root,"1");
        printTreeData(root,tree);
        root = tree.delete(root,"-1");
        root = tree.delete(root,"0");
        root = tree.delete(root,"2");
        root = tree.delete(root,"5");
        printTreeData(root,tree);
        root = tree.delete(root,"9");
        printTreeData(root,tree);
    }

    static void printTreeData(Node<String> root, TreeI<String> tree) {
        tree.inorder(root);
        System.out.println();
        System.out.println("Height: "+tree.height(root));
        System.out.println("Size: "+tree.size(root));
        System.out.println();
    }
}

class Tree<T extends Comparable<T>> implements TreeI<T> {

    @Override
    public Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }
        if (value.compareTo(root.value) < 0) {
            root.left = insert(root.left,value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    @Override
    public int size(Node<T> root) {
        if (root == null) {
            return 0;
        }

        return 1+size(root.left)+size(root.right);
    }

    @Override
    public int height(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right))+1;
    }

    @Override
    public void inorder(Node<T> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value+", ");
        inorder(root.right);
    }

    @Override
    public Node<T> delete(Node<T> root, T value) {
        if (root==null || value==null) {
            return null;
        }
        if (root.value.compareTo(value)>0) {
            root.left = delete(root.left, value);
        } else if (root.value.compareTo(value) < 0) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node<T> succ = getSuccessor(root);
                root.value = succ.value;
                root.right = delete(root.right,succ.value);
            }
        }
        return root;
    }

    private Node<T> getSuccessor(Node<T> root) {
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }
}

//13.03 - 13:31
class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
    T value;
    Node<T> left;
    Node<T> right;

    public Node(T value) {
        this.value = value;
        left = null;
        right=null;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.value.compareTo(o.value);
    }
}

interface TreeI<T extends Comparable<T>> {
    Node<T> insert(Node<T> root, T value);
    int size(Node<T> root);
    int height(Node<T> root);
    void inorder(Node<T> root);
    Node<T> delete(Node<T> root, T value);
}