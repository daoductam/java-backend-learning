import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Q1941_Check_if_All_Characters_Have_Equal_Number_of_Occurrences {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: O(n) O(k) k-entry
    - tạo map
    - vào từng entry rồi so sánh giá trị value của ký tự thú nhát với toàn bộ value của key
     */
    public boolean areOccurrencesEqual(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        int value = map.get(s.charAt(0));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (value != entry.getValue()) {
                return false;
            }

        }
        return true;
    }

    /*
    Đáp án O(n) O(1) nhanh hơn mà ko dùng map
    public boolean areOccurrencesEqual(String s) {
        int[] freq = new int[26]; // giả định chỉ chứa 'a'-'z'

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int expected = 0;
        for (int f : freq) {
            if (f > 0) {
                if (expected == 0) {
                    expected = f;       // đặt chuẩn ban đầu
                } else if (f != expected) {
                    return false;      // lệch chuẩn
                }
            }
        }
        return true;
     */
}
