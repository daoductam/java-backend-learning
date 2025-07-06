import java.util.Arrays;

public class Q283_Move_Zeroes {
    /*
    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    Note that you must do this in-place without making a copy of the array.
    Example 1:
    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]
    Example 2:
    Input: nums = [0]
    Output: [0]
     */
    public static void main(String[] args) {
        int[] nums = new int[] {0,1,0,3,12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
    /*
    Ý tưởng:
    - Duyệt qua mảng một lần.
    - Giữ một con trỏ index để đánh dấu vị trí đặt phần tử khác 0 tiếp theo.
    - Nếu gặp phần tử khác 0, gán nó vào nums[index], sau đó tăng index.
    - Sau vòng lặp đầu tiên, tất cả các phần tử khác 0 đã được "dồn về đầu mảng".
    - Vòng lặp thứ hai: từ index đến hết mảng, gán các phần tử còn lại thành 0.
     */
    public static void moveZeroes(int[] nums) {

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (int i = index; i < nums.length ; i++) {
            nums[i] = 0;
        }
    }
    /*
    Đáp án: Y hệt ý tưởng
     */
}
