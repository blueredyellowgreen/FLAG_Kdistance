import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCase {
    TreeNode root;
    TreeNode target;
    int k;
    List<Integer> expect;

    public TestCase(TreeNode root, int target, int k, int[] expect) {
        this.root = root;
        this.k = k;
        this.target = findTreeNodeByValue(target);
        this.expect = new ArrayList<>();
        for (int v : expect) {
            this.expect.add(v);
        }
        Collections.sort(this.expect);
    }

    private TreeNode findTreeNodeByValue(int val) {
        return findTreeNodeByValueHelper(val, root);
    }

    private TreeNode findTreeNodeByValueHelper(int val, TreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.val == val) {
            return node;
        }

        TreeNode result = findTreeNodeByValueHelper(val, node.left);
        if (result != null) {
            return result;
        }

        result = findTreeNodeByValueHelper(val, node.right);
        return result;
    }
}

