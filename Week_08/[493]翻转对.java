//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        return mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    int mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        int count = mergeSort(nums, left, mid, tmp) + mergeSort(nums, mid + 1, right, tmp);

        int i = left, j = mid + 1;
        while (i <= mid) {
            double numi = (double) nums[i++] / 2.0d;
            // 左侧：left ~ i ~ mid
            // 右侧：mid+1 ~ j ~ right
            // 因为【从大到小】排序
            // 1、当i、j不满足nums[i]>2nums[j]条件时，则i~mid、j这部分数据必然也不满足上述条件，此时为了减少多余循环，i~mid复用已经累加过的j即可。
            // 2、当i、j满足nums[i]>2nums[j]条件时，则当前i、j~right这部分数据必然也满足上述条件，此时无需后续循环，count直接加上j到right数量即可。
            while (j <= right && numi <= (double) nums[j]) j++;
            count += (right - j + 1);
        }

        merge(nums, left, mid, right, tmp);
        return count;
    }

    void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            tmp[k++] = (nums[i] >= nums[j]) ? nums[i++] : nums[j++]; // 注意这里是【从大到小】排序
        }
        while (i <= mid) tmp[k++] = nums[i++];
        while (j <= right) tmp[k++] = nums[j++];
        System.arraycopy(tmp, 0, nums, left, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
