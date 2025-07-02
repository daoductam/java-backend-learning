import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q217_Contains_Duplicate {
    /*
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
Example 1:
Input: nums = [1,2,3,1]
Output: true
Explanation:
The element 1 occurs at the indices 0 and 3.
Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation:
All elements are distinct.
Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true


     */

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1,2,3,4}));
    }

    /*
    Ý tưởng : Space O(n) Time O(n)
    - Sửu dụng stream để loại bỏ trung lặp
    - So sánh 2 mang truoc va sau khi loai bỏ
     */
    public static boolean containsDuplicate(int[] nums) {
        int[] tmp = Arrays.stream(nums)
                .distinct()
                .toArray();
        return nums.length != tmp.length;

    }
    /*
    Đáp án: Space O(n) Time O(n)
    - Duyệt qua từng phần tử của mảng nums.
    - Nếu phần tử đó đã có trong set → đây là phần tử trùng → trả về true.
    - Nếu chưa có → thêm vào set để theo dõi.
    - Sau khi duyệt hết mảng, nếu không có phần tử trùng nào → trả về false.
    -> nên dùng cách này vì dừng sớm khi gặp phần tử trùng, cách trên phải duệt toaàn bộ. Cách trên Tốn thêm mảng mới, hiệu năng kém hơn
    

     */
//    public static boolean containsDuplicate(int[] nums) {
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (set.contains(nums[i])) {
//                return true;
//            }
//            set.add(nums[i]);
//        }
//        return false;
//
//    }
}
