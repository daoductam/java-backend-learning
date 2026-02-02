import java.util.Arrays;

public class Q1929_Concatenation_of_Array {

    public static void main(String[] args) {

    }

    /*
    Dùng array.copyof 2 lần duyệt mảng O(n) O(1)
     */
    public int[] getConcatenation(int[] nums) {
        int[] rs = Arrays.copyOf(nums, nums.length*2);
        for (int i = 0; i < nums.length; i++) {
            rs[nums.length+i] = nums[i];
        }
        return rs;
    }

    /*
    Đáp án -> tối ưu nhất O(n) O(1)

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];       // copy vào nửa đầu
            ans[i + n] = nums[i];   // copy vào nửa sau
        }

        return ans;
    }
     */
}
