//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。 
//
// 示例: 
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int count, size;
    public int totalNQueens(int n) {
        count = 0;
        // 把0到n - 1位（共计n位）的所有位置为1.
        size = (1 << n) - 1;
        solve(0, 0, 0);
        return count;
    }

    private void solve(int cols, int master, int slave) {
        // 当每一位都填充为1（每一列都放置了一个皇后，且符合规则）时，则找到一个答案
        if (cols == size) {
            count++;
            return;
        }
        // cols为列被占用的情况，1为占用，0为没占用
        // master为主对角线的占用情况，在数组中可以通过相减为常数来判断，但在位运算中需要通过别的手段来记录master
        // master是从左上到右下，也就是说二维数组中，每下降一层，master中记录的位置就要右移一位
        // 二进制数数位是“从右向左”的，而数组的数位是“从左向右”的
        // 因此数组中的“右移一位”这个操作在位运算中就是“<<1（左移一位）”，同理，副对角线slave也是这样求
        // pos为本层可以放置皇后的点，1为可以放置，0为不可放置。将占用掉的列或起来取个反就可以将所有没被占用的列设为1。
        int pos = size & (~(cols | master | slave));
        while (pos != 0) {
            // 获取最低位的1及右边的所有0
            int p = pos & -pos;
            solve(cols | p, (master | p) << 1, (slave | p) >> 1);
            // 把最低位的1清为0，之后回溯回来就可以探测下一个1.
            pos &= (pos - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
