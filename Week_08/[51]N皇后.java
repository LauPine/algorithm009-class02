//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private static ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
    private static List<List<String>> r ;
    private static int lim ;
    private static int[] isqueen;
    private static int n;
    public List<List<String>> solveNQueens(int nn) {
        n=nn;
        lim = (1<<n)-1;//防止越界
        isqueen = new int [n];
        test(0,0,0,0);
        ArrayList<List<String>> cntt = new ArrayList<List<String>>();
        for(ArrayList<String> a:ans){//ArrayList<List<String>>
            List<String> cnt = a;
            cntt.add(cnt);
        }
        r = cntt;//List<List<String>>
        ans.clear();//清空链表
        return r;
    }
    public void test(int row,int left,int right,int k){//row上为1的位表示已有皇后的列，left表示当前行对应的列是受正斜线影响，right是反斜线
        int pos,p;
        if(row!=lim){
            pos = lim & ~(row|left|right);//能放的位置
            while(pos!=0){
                p = pos & -pos;//找到最右边的可行位置
                pos -= p;
                isqueen[k] = p;//记录下当前选择的位置
                test(row+p,(left+p)<<1,(right+p)>>1,k+1);
            }
        }else {//位置填满则记录下当前选择
            char[] c  = new char [n];
            ArrayList<String> s = new ArrayList<String>();
            for(int i=0;i<n;i++){
                int clo = isqueen[i];
                for(int j=0;j<n;j++,clo>>=1){//因为对称所以可以反序填
                    if((clo&1)==1){
                        c[j]='Q';
                    }else{
                        c[j]='.';
                    }
                }
                s.add(String.valueOf(c));
            }
            ans.add(s);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
