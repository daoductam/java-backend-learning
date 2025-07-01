import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q169_Majority_Element {
    /*
    Given an array of size , return the majority element.numsn
The majority element is the element that appears more than times. You may assume that the majority element always exists in the array.⌊n / 2⌋
Example 1:
Input: nums = [3,2,3]
Output: 3
Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
     */
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] {2,2,1,1,1,2,2}));
    }
    /*
     Ý tưởng: Time O(n) Space O(n)
     - Sử dụng HashMap<Integer, Integer> để lưu cặp phần tử → số lần xuất hiện
     - Duyệt lại map.entrySet() để tìm phần tử có số lần xuất hiện lớn hơn n / 2
     */

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            if (map.containsKey(num)) {
                map.put(num,map.get(num)+1);
            } else {
                map.put(num, 1);
            }

        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length/2) {
                return entry.getKey();
            }
        }

        return -1;
    }
    /*
    Đáp án: Tương tự code trên
     */

    /*
    chat gpt: Ý tưởng Boyer-Moore Voting Algorithm Time O(n) Space O(1)
    - Khởi tạo candidate = null (ứng viên hiện tại), count = 0 (số phiếu hiện tại)
    - Duyệt qua mảng nums:
    - Nếu count == 0, chọn phần tử hiện tại làm candidate
    - Nếu phần tử hiện tại == candidate, tăng count
    - Ngược lại, giảm count
    - Sau vòng lặp, candidate chính là phần tử chiếm đa số (do đề bài đảm bảo tồn tại).


     */
//    public static int majorityElement(int[] nums) {
//        int candidate = 0, count = 0;
//
//        for (int num : nums) {
//            if (count == 0) {
//                candidate = num;
//            }
//            count += (num == candidate) ? 1 : -1;
//        }
//
//        return candidate;
//    }
}
