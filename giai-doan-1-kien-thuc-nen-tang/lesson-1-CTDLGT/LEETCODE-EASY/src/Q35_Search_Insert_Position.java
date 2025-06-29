import java.util.LinkedList;
import java.util.List;

public class Q35_Search_Insert_Position {
    /*
Given a sorted array of distinct integers and a target value, return
the index if the target is found. If not, return the index where it
would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity.
Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:
Input: nums = [1,3,5,6], target = 7
Output: 4

     */
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
    }
    /*
    Ý tưởng: sử dụng thuật toán tìm kiếm nhị phân ->  O(log n)
     */
    public static int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        while (l<= r) {
            int mid = (l+r)/2;
            if (nums[mid]==target) {
                return mid;
            } else if (target > nums[mid]) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return l;
    }

    /*
    Đáp án: tương tự như ý tưởng nhưng mid nên = left + (right - left) / 2 vì nếu giá trị lớn
    (right - left) giúp không vượt quá Integer.MAX_VALUE và không bị tràn vì chỉ cộng thêm
    phần hiệu nhỏ vào left
     */
}
