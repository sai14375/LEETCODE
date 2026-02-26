import java.util.*;

class Solution {

    class Pair {
        TreeNode node;
        int row, col;

        Pair(TreeNode n, int r, int c) {
            node = n;
            row = r;
            col = c;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // col -> list of [row, value]
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode node = p.node;
            int row = p.row;
            int col = p.col;

            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(new int[]{row, node.val});

            if (node.left != null) {
                queue.offer(new Pair(node.left, row + 1, col - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, row + 1, col + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (List<int[]> list : map.values()) {
            // Sort by row, then value
            Collections.sort(list, (a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            });

            List<Integer> column = new ArrayList<>();
            for (int[] arr : list) {
                column.add(arr[1]);
            }
            result.add(column);
        }

        return result;
    }
}