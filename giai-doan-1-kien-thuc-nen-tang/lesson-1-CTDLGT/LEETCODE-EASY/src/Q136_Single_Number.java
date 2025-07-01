import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q136_Single_Number {
    /*
    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.
Example 1:
Input: nums = [2,2,1]
Output: 1
Example 2:
Input: nums = [4,1,2,1,2]
Output: 4
Example 3:
Input: nums = [1]
Output: 1
     */
    public static void main(String[] args) {

    }
    /*
    Y tưởng: Time O(n) space O(n) -> sai voi de bai yeu vau
    - Duyệt qua mảng và đếm số lần xuất hiện của mỗi phần tử bằng HashMap
    - Sau đó duyệt lại entrySet để tìm phần tử xuất hiện đúng 1 lần
     */
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map= new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num)+1);
            } else {
                map.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }

    /*
    Dap an: ime O(n) space O(n)
    - Dùng phép XOR (^): a ^ a = 0, a ^ 0 = a
    - Các phần tử xuất hiện hai lần sẽ triệt tiêu nhau: a ^ a = 0
    - Phần tử xuất hiện một lần sẽ còn lại sau cùng.
     */
    public static int SingleNumber(int[] nums) {
        // a^a=0
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
