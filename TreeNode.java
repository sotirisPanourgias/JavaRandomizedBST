public class TreeNode {

    // The data it holds
    LargeDepositor item;

    // Left subtree (child)
    TreeNode left;

    // Right subtree (child)
    TreeNode right;

    // The BST node's parent
    // (useful for performing rotations (swaps) during root insertion)
    TreeNode parent;
    int n;
    /**
     * Default TreeNode constructor
     */
    public TreeNode(){
    }

    /**
     * TreeNode constructor accepting data
     * @param data
     */
    public TreeNode(LargeDepositor data){
        this.item = data;
    }

    /**
     * @return The data it holds
     */
    public LargeDepositor getitem() {
        return item;
    }

    /**
     * @param data the data to store
     */
    public void setData(LargeDepositor data) {
        this.item = data;
    }

    /**
     * @return left subtree
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * @param left Set left subtree
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * @return right subtree
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * @param right Set right subtree
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * @return the BST node's parent
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     *
     * @param parent Set the BST node's parent
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

}