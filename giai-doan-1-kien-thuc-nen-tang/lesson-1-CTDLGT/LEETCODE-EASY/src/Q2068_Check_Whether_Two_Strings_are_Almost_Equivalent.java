import java.util.HashMap;
import java.util.Map;

public class Q2068_Check_Whether_Two_Strings_are_Almost_Equivalent {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng:
    Sử dụng HashMap O(n) O(1)

     */
    public boolean checkAlmostEquivalent(String word1, String word2) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            map.put(word1.charAt(i), map.getOrDefault(word1.charAt(i), 0)+1);
        }
        for (int i = 0; i < word2.length(); i++) {
            map.put(word2.charAt(i), map.getOrDefault(word2.charAt(i), 0)-1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (Math.abs(entry.getValue()) > 3) {
                return false;
            }
        }

        return true;

    }

    /*
    Đáp án Sử dụng mảng int[26] -> nhanh hơn chút vì truy cập mảng nhanh hơn HashMap
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] freq = new int[26];
        for (char c : word1.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            freq[c - 'a']--;
        }
        for (int f : freq) {
            if (Math.abs(f) > 3) {
                return false;
            }
        }
        return true;
    }
     */
}
