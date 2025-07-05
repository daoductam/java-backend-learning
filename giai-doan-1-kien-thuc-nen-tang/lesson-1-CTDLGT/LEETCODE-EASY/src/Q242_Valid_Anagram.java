import java.util.HashMap;
import java.util.Map;

public class Q242_Valid_Anagram {
//    Given two strings s and t, return true if t is an anagram of s, and
//    false otherwise.
//            Example 1:
//    Input: s = "anagram", t = "nagaram"
//    Output: true
//    Example 2:
//    Input: s = "rat", t = "car"
//    Output: false
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram","nagaram" ));
        System.out.println();
    }
    /*
    Ý tưởng: O(n) time, O(n) space
    Mỗi lần xuất hiện trong s → tăng
    Mỗi lần xuất hiện trong t → giảm
    Nếu đúng là anagram, sau khi lặp xong → tất cả giá trị trong map sẽ = 0
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
            else {
                map.put(s.charAt(i), 1);
            }
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            } else {
                map.put(t.charAt(i), -1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                System.out.println(entry.getValue());
                return false;
            }
        }
        return true;
    }
    /*
    Đáp án: O(n) time, O(1) space
     */
//    public static boolean isAnagram(String s, String t) {
//        // Nếu độ dài khác nhau thì chắc chắn không phải anagram
//        if (s.length() != t.length()) return false;
//
//        int[] count = new int[26]; // giả sử chỉ có chữ cái thường a-z
//
//        // Đếm ký tự trong chuỗi s
//        for (char c : s.toCharArray()) {
//            count[c - 'a']++;
//        }
//
//        // Trừ số lượng ký tự trong chuỗi t
//        for (char c : t.toCharArray()) {
//            count[c - 'a']--;
//            // Nếu số lượng bị âm, nghĩa là t có ký tự nhiều hơn → không phải anagram
//            if (count[c - 'a'] < 0) return false;
//        }
//
//        return true;
//    }

    /*
    Cách nữa: O(n log n) time
    Cách này vẫn đúng nhưng chậm hơn (thời gian O(n log n) vì phải sắp xếp).
     */
//    public static boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) return false;
//        char[] sArr = s.toCharArray();
//        char[] tArr = t.toCharArray();
//        Arrays.sort(sArr);
//        Arrays.sort(tArr);
//        return Arrays.equals(sArr, tArr);
//    }
}
