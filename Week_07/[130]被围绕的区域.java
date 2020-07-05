//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[][] dirArr = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    public void solve(char[][] board) {
        if (board == null || board.length < 1) {
            return;
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if ((r == 0 || r == board.length - 1 || c == 0 || c == board[0].length - 1) && board[r][c] == 'O') {
                    dfs(board, r, c);
                }
            }
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = board[r][c] == '#' ? 'O' : 'X';
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (inArea(board, r, c) && board[r][c] == 'O') {
            board[r][c] = '#';
            for (int[] dir : dirArr) {
                dfs(board, r + dir[0], c + dir[1]);
            }
        }
    }

    private boolean inArea(char[][] board, int r, int c) {
        return r >=  0 && r < board.length && c >= 0 && c < board[0].length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
