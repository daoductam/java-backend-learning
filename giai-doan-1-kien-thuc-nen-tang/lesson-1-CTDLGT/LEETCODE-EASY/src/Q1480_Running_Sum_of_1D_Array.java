public class Q1480_Running_Sum_of_1D_Array {
    /*
Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
Return the running sum of nums.
Example 1:
Input: nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
Example 2:
Input: nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
Example 3:
Input: nums = [3,1,2,10,1]
Output: [3,4,6,16,17]
     */
    public static void main(String[] args) {

    }
    /*
    Ý tưởng: Space O(1) Time O(n)
    Tính tổng tích lũy từ trái qua phải và cập nhật ngay vào chính mảng nums.
     */
    public int[] runningSum(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total+=nums[i];
            nums[i]=total;
        }

        return nums;
    }

    /*
    Đáp án:Space O(1) Time O(n)
     */

//    public int[] runningSum(int[] nums) {
//        int total = 0;
//        for (int i = 1; i < nums.length; i++) {
//            total=nums[i]+nums[i-1];
//            nums[i]=total;
//        }
//
//        return nums;
//    }
}
