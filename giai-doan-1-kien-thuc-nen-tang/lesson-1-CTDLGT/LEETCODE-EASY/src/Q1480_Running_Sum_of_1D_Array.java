public class Q1480_Running_Sum_of_1D_Array {
    public static void main(String[] args) {

    }

    public int[] runningSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sum+nums[i];
            sum = nums[i];
        }
        return nums;
    }

    // Đáp án: tiết kiệm thời gian hơn
//    public int[] runningSum(int[] nums) {
//
//        for(int i=1;i<nums.length;i++){
//            nums[i]=nums[i-1]+nums[i];
//
//        }
//        return nums;
//    }
}
