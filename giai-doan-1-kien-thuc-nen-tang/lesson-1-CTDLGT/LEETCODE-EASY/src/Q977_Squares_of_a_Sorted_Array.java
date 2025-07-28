import java.util.Collections;

public class Q977_Squares_of_a_Sorted_Array {
    /*
    Given an integer array nums sorted in non-decreasing order,
    return an array of the squares of each number sorted in non-decreasing order.
    Example 1:
    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After sorting, it becomes [0,1,9,16,100].
    Example 2:
    Input: nums = [-7,-3,2,3,11]
    Output: [4,9,9,49,121]
     */
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: Time O(n) Space O(1)
    Ta dùng kỹ thuật 2 con trỏ (two pointers):
    left trỏ từ đầu mảng
    right trỏ từ cuối mảng
    So sánh bình phương tại hai đầu, gán số lớn hơn vào cuối mảng kết quả (điền từ phải → trái)
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int pos = n - 1;
        while (left <= right) {
            int leftVal = nums[left] * nums[left];
            int rightVal = nums[right] * nums[right];

            if (leftVal > rightVal) {
                result[pos] = leftVal;
                pos--;
                left++;
            } else {
                result[pos] = rightVal;
                pos--;
                right--;
            }
        }

        return result;

    }
    /*
    Đáp án Time O(n) Space O(1) tương tự
     */
//    public static int[] sortedSquares(int[] nums) {
//        int[] result = new int[nums.length];
//        int l = 0;
//        int r = nums.length-1;
//        for (int i = result.length -1; i >=0; i--) {
//            int leftSquare = nums[l] * nums[l];
//            int rightSquare = nums[r] * nums[r];
//            if (leftSquare > rightSquare) {
//                result[i] = leftSquare;
//                l++;
//            } else{
//                result[i] = rightSquare;
//                r--;
//            }
//        }
//        return result;
//    }
}
