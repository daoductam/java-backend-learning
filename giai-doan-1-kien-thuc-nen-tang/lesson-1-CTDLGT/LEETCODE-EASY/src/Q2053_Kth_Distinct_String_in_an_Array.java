import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Q2053_Kth_Distinct_String_in_an_Array {
    public static void main(String[] args) {

    }
    /*
    Ý tưởng: Dùng LinkedHashMap để vừa đếm vừa giữ thứ tự O(n) O(n)
     */

    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String a : arr) {
            map.put(a, map.getOrDefault(a, 0)+1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue()==1) {
                k--;
            }
            if (k==0) {
                return entry.getKey();
            }

        }
        return "";
    }

    /*
    Đáp án: Dùng HashMap + duyệt lại mảng O(n) O(b) -> tối ưu tốc độ
    public String kthDistinct(String[] arr, int k) {
        // Đếm tần suất mỗi string
        Map<String, Integer> freq = new HashMap<>();
        for (String s : arr) {
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        }

        // Duyệt lại theo thứ tự ban đầu, đếm những string có freq == 1
        int count = 0;
        for (String s : arr) {
            if (freq.get(s) == 1) {
                count++;
                if (count == k) {
                    return s;
                }
            }
        }

        // Không đủ distinct string
        return "";
    }
     */
}
