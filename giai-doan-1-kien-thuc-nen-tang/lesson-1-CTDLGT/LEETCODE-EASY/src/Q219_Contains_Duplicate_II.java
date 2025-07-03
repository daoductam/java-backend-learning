import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Q219_Contains_Duplicate_II {
    /*
    Given an integer array nums and an integer k, return true if there are two distinct indices
    i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:
Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
     */
    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1,2,3},2));
    }
    /*
    Ý tưởng: Time O(n) Space )(n)
    - Dùng HashMap để lưu: key = nums[i], value = chỉ số gần nhất (i) mà giá trị này xuất hiện
    - nums[i] đã tồn tại trong HashMap → kiểm tra khoảng cách i - index_cũ.
    - Nếu khoảng cách <= k → return true.
    - Ngược lại, cập nhật chỉ số mới cho phần tử đó.
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int prevIndex = map.get(nums[i]) ;
                if (i - prevIndex <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /*
    Đáp án  Time O(n) Space )(n) Tương tự ý tuỏng
     */
//    public static boolean containsNearbyDuplicate(int[] nums, int k) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i <nums.length; i++) {
//            if (!map.containsKey(nums[i])) {
//                map.put(nums[i], i);
//            } else {
//                int diff = Math.abs(map.get(nums[i])-i);
//                if (diff <= k) {
//                    return true;
//                } else {
//                    map.put(nums[i],i);
//                }
//            }
//
//        }
//        return false;
//    }
}
