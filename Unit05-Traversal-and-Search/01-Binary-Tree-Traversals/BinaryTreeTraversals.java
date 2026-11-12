import java.util.Stack;

public class BinaryTreeTraversals {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void recursiveInorder(Node root) {
        if (root == null) {
            return;
        }
        recursiveInorder(root.left);
        System.out.print(root.data + " ");
        recursiveInorder(root.right);
    }

    public static void recursivePreorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        recursivePreorder(root.left);
        recursivePreorder(root.right);
    }

    public static void recursivePostorder(Node root) {
        if (root == null) {
            return;
        }
        recursivePostorder(root.left);
        recursivePostorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void iterativeInorder(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
    }

    public static void iterativePreorder(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public static void iterativePostorder(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("BINARY TREE CREATION AND TRAVERSALS");
        System.out.println("Tree Structure: Root = 1, Left Child = 2, Right Child = 3 ...");
        System.out.println("-----------------------------------------------------------------");

        System.out.print("Recursive Preorder Traversal:  ");
        recursivePreorder(root);
        System.out.println();

        System.out.print("Iterative Preorder Traversal:  ");
        iterativePreorder(root);
        System.out.println();

        System.out.println("-----------------------------------------------------------------");

        System.out.print("Recursive Inorder Traversal:   ");
        recursiveInorder(root);
        System.out.println();

        System.out.print("Iterative Inorder Traversal:   ");
        iterativeInorder(root);
        System.out.println();

        System.out.println("-----------------------------------------------------------------");

        System.out.print("Recursive Postorder Traversal: ");
        recursivePostorder(root);
        System.out.println();

        System.out.print("Iterative Postorder Traversal: ");
        iterativePostorder(root);
        System.out.println();

        System.out.println("-----------------------------------------------------------------");
    }
}
