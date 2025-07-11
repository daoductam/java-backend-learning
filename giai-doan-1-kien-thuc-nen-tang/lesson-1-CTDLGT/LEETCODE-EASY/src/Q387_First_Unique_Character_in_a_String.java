import java.util.*;

public class Q387_First_Unique_Character_in_a_String {
    /*
    Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
    Example 1:
    Input: s = "leetcode"
    Output: 0
    Explanation:
    The character 'l' at index 0 is the first character that does not occur at any other index.
    Example 2:
    Input: s = "loveleetcode"
    Output: 2
    Example 3:
    Input: s = "aabb"
    Output: -1
     */
    public static void main(String[] args) {

    }
    /*
    Ý tưởng: Time O(n) Space O(1)
    1. Duyệt qua chuỗi `s` và lưu số lần xuất hiện của mỗi ký tự vào một `Map<Character, Integer>`.
    2. Duyệt lại chuỗi từ trái qua phải:
    - Với mỗi ký tự `s.charAt(i)`, nếu `map.get(s.charAt(i)) == 1` thì trả về `i` ngay lập tức.
    3. Nếu không có ký tự nào xuất hiện đúng 1 lần, trả về -1.
     */
    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c,map.get(c) + 1);
            } else {
                map.put(c,1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
    /*
    Đáp án
    Nếu  chuỗi chỉ chứa ký tự từ 'a' đến 'z',có thể dùng mảng 26 phần tử thay vì HashMap để tiết kiệm bộ nhớ và tăng tốc độ:
     */
//    public static int firstUniqChar(String s) {
//        int[] count = new int[26];
//        for (char c : s.toCharArray()) {
//            count[c - 'a']++;
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            if (count[s.charAt(i) - 'a'] == 1) {
//                return i;
//            }
//        }
//        return -1;
//    }

}
