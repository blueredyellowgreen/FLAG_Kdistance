import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SolutionTest {
    @Test
    public void testDistanceK() {

        List<TestCase> testCases = getTestCases();

        for (int i = 0; i < testCases.size(); i++) {

            System.out.printf("case %d\n", i);
            TestCase testCase = testCases.get(i);

            List<Integer> actual = Solution.distanceK(testCase.root, testCase.target, testCase.k);
            Collections.sort(actual);

            assertTrue(testCase.expect.equals(actual));
        }
    }

    private List<TestCase> getTestCases() {
        List<TestCase> testCases = new ArrayList<>();

        //      1
        //     / \
        //    2   N
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        testCases.add(new TestCase(root, 1, 1, new int[]{2}));
        testCases.add(new TestCase(root, 2, 1, new int[]{1}));
        testCases.add(new TestCase(root, 2, 2, new int[]{}));
        testCases.add(new TestCase(root, 1, 2, new int[]{}));

        //      1
        //     / \
        //    2   3
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        testCases.add(new TestCase(root, 1, 1, new int[]{2, 3}));
        testCases.add(new TestCase(root, 2, 1, new int[]{1}));
        testCases.add(new TestCase(root, 2, 2, new int[]{3}));
        testCases.add(new TestCase(root, 2, 3, new int[]{}));
        testCases.add(new TestCase(root, 3, 1, new int[]{1}));
        testCases.add(new TestCase(root, 3, 2, new int[]{2}));
        testCases.add(new TestCase(root, 3, 3, new int[]{}));

        //          1
        //         /
        //        2
        //       /
        //      3
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        testCases.add(new TestCase(root, 1, 1, new int[]{2}));
        testCases.add(new TestCase(root, 1, 2, new int[]{3}));
        testCases.add(new TestCase(root, 1, 3, new int[]{}));
        testCases.add(new TestCase(root, 2, 1, new int[]{1, 3}));
        testCases.add(new TestCase(root, 2, 2, new int[]{}));
        testCases.add(new TestCase(root, 3, 1, new int[]{2}));
        testCases.add(new TestCase(root, 3, 2, new int[]{1}));
        testCases.add(new TestCase(root, 3, 3, new int[]{}));

        //          1
        //           \
        //            2
        //             \
        //              3
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        testCases.add(new TestCase(root, 1, 1, new int[]{2}));
        testCases.add(new TestCase(root, 1, 2, new int[]{3}));
        testCases.add(new TestCase(root, 1, 3, new int[]{}));
        testCases.add(new TestCase(root, 2, 1, new int[]{1, 3}));
        testCases.add(new TestCase(root, 2, 2, new int[]{}));
        testCases.add(new TestCase(root, 3, 1, new int[]{2}));
        testCases.add(new TestCase(root, 3, 2, new int[]{1}));
        testCases.add(new TestCase(root, 3, 3, new int[]{}));

        //      1
        //     / \
        //    2   3
        //   / \   \
        //  4   5   6
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        testCases.add(new TestCase(root, 1, 1, new int[]{2, 3}));
        testCases.add(new TestCase(root, 2, 1, new int[]{1, 4, 5}));
        testCases.add(new TestCase(root, 2, 2, new int[]{3}));


        //             1
        //            /  \
        //           2    3
        //          /  \    \
        //         4    5    6
        //        /    /
        //       7    8
        //      /
        //     9
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.left.left = new TreeNode(9);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(8);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        testCases.add(new TestCase(root, 2, 2, new int[]{3, 7}));
        testCases.add(new TestCase(root, 5, 2, new int[]{1, 4}));
        testCases.add(new TestCase(root, 5, 3, new int[]{3, 7}));
        testCases.add(new TestCase(root, 4, 2, new int[]{1, 5, 9}));
        return testCases;
    }
}
