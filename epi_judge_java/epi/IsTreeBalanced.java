package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> root) {
        boolean result = true;
        if (root != null) {
            result = Math.abs(preOrderTraversal(root)-preOrderTraversalRight(root)) >= 2;

            isBalanced(root.left);
            isBalanced(root.right);
        }

        return result;
    }


    static void inOrderTraversal();

    static int preOrderTraversal(BinaryTreeNode<Integer> root) {
        int i = 0;
        if (root != null) {
            System.out.println(root.data);
            i++;
            preOrderTraversal(root.left);
        }

        return i;
    }

    static int preOrderTraversalRight(BinaryTreeNode<Integer> root) {
        int i = 0;
        if (root != null) {
            System.out.println(root.data);
            i++;
            preOrderTraversal(root.right);
        }
        return i;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
