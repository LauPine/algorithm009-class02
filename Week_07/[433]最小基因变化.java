//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
//


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int count = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> vistied = new HashSet<>();
        dfs(0,start,end,bank,vistied);
        return count == Integer.MAX_VALUE?-1:count;
    }

    private void dfs(int minStepCount, String start, String end, String[] bank, HashSet<String> vistied) {
        if (start.equals(end)) {
            count=Math.min(count,minStepCount);
            return;
        }
        for (int i=0;i<bank.length;i++){
            int diff=0;
            for (int j=0;j<8;j++){
                if (bank[i].charAt(j)!=start.charAt(j))diff++;
                if (diff>1)break;
            }
            if (diff==1&&!vistied.contains(bank[i])){
                vistied.add(bank[i]);
                dfs(minStepCount+1,bank[i],end,bank,vistied);
                vistied.remove(bank[i]);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
