import java.util.ArrayList;
import java.util.List;
import java.util.*;

// All Nodes Distance K
//
// There could be two cases:
// 1. Nodes are in the subtree rooted at target.
// 2. Nodes are not in the subtree rooted at target.
//
// For case 1: Find descendants using DFS.
// For case 2: Find non-descendants using DFS + BFS.
//
// Time Complexity : O(N) where n is the number of nodes in the tree.
// Space Complexity : O(N) where n is the number of nodes in the tree.
//
class Solution {
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();

        if (root == null || target == null || K < 0) {
            return result;
        }

        if (K == 0) {
            result.add(target.val);
            return result;
        }

        // Case 1: Find descendants having a distance K
        List<TreeNode> descendants = new ArrayList<>();
        findDescendants(target, 0, K, descendants);
        List<TreeNode> nodes = new ArrayList<>(descendants);

        // Case 2: Find non-descendants in two steps:
        // 2.1 Find ancestors of target
        List<TreeNode> ancestors = new ArrayList<>();
        findAncestors(root, target, ancestors);
        // 2.2 For each ancestor, find nodes having a distance (K - distance between ancestor and target)
        int size = ancestors.size();
        for (int i = 0, d = size; i < size; i++, d--) {
            TreeNode parent = ancestors.get(i);
            boolean isLeftChildVisited = (i + 1) >= ancestors.size()
                    ? (parent.left == target)
                    : (parent.left == ancestors.get(i + 1))
                    ;
            List<TreeNode> nonDescendants = findOtherSubTree(parent, K - d, isLeftChildVisited);
            nodes.addAll(nonDescendants);
        }

        return getValues(nodes);
    }
    
    private static void findDescendants(TreeNode root, int current, int distance, List<TreeNode> nodes) {
        if (root == null || current > distance) {
            return;
        }

        if (current == distance) {
            nodes.add(root);
            return;
        }

        findDescendants(root.left, current + 1, distance, nodes);
        findDescendants(root.right, current + 1, distance, nodes);
    }

    private static boolean findAncestors(TreeNode root, TreeNode target, List<TreeNode> nodes) {
        if (root == null) {
            return false;
        }

        if (root == target) {
            return true;
        }

        nodes.add(root);
        if (findAncestors(root.left, target, nodes)) {
            return true;
        }
        if (findAncestors(root.right, target, nodes)) {
            return true;
        }
        nodes.remove(nodes.size() - 1);

        return false;
    }
    
    private static List<TreeNode> findOtherSubTree(TreeNode root, int distance, boolean isLeftChildVisited) {
        List<TreeNode> nodes = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList();
        Set<TreeNode> visited = new HashSet();
        queue.add(root);
        visited.add(root);
        // Avoid revisiting a child
        visited.add(isLeftChildVisited ? root.left : root.right);

        int current = 0;
        while (!queue.isEmpty() || current <= distance) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                nodes.add(node);
                if (node.left != null && !visited.contains(node.left)) {
                    queue.add(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    queue.add(node.right);
                }
            }
            if (current == distance) {
                break;
            } else {
                nodes.clear();
                current++;
            }
        }

        return nodes;
    }

    private static List<Integer> getValues(List<TreeNode> nodes) {
        List<Integer> values = new ArrayList<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }
        return values;
    }
}
