public class Solution {

    public TreeNode recoverFromPreorder(String traversal) {
        Stack<TreeNode> stack = new Stack<>();
        int index = 0;

        while (index < traversal.length()) {
            int depth = 0;
            while (
                index < traversal.length() && traversal.charAt(index) == '-'
            ) {
                depth++;
                index++;
            }

            int value = 0;
            while (
                index < traversal.length() &&
                Character.isDigit(traversal.charAt(index))
            ) {
                value = value * 10 + (traversal.charAt(index) - '0');
                index++;
            }

            TreeNode node = new TreeNode(value);

            while (stack.size() > depth) {
                stack.pop();
            }

            if (!stack.empty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }

            stack.push(node);
        }

        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.peek();
    }
}