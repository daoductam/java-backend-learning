import java.util.HashMap;
import java.util.Map;

public class Q1748_Sum_of_Unique_Elements {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: Dung HashMap O(n) O(n)
     */
    public int sumOfUnique(int[] nums) {
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num,0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue()==1) {
                total+=entry.getKey();
            }
        }

        return total;
    }

    /*
    Đáp án nêú cho giới hạn < 100 O(n+K) O(K) -> tối ưu hơn
    public int sumOfUnique(int[] nums) {
        int[] freq = new int[101]; // vì đề LeetCode giới hạn nums[i] ≤ 100
        for (int num : nums) {
            freq[num]++;
        }

        int sum = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 1) sum += i;
        }

        return sum;
    }
     */
}
