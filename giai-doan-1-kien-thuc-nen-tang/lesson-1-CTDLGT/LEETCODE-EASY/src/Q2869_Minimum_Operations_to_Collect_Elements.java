import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2869_Minimum_Operations_to_Collect_Elements {
    public static void main(String[] args) {

    }

    public int minOperations(List<Integer> nums, int k) {
        int rs = 0;
        int n = k;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= k; i++) {
            map.put(i, 0);
        }
        for (int i = nums.size()-1; i >-1 ; i--) {
            if (n==0) {
                break;
            }

            if (map.containsKey(nums.get(i))) {
                if (map.get(nums.get(i))==0) {
                    n--;
                }
                map.put(nums.get(i), map.get(nums.get(i))+1);

            } else {
                rs++;
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            rs += entry.getValue();
        }


        return rs;
    }


    /*
    class Solution {
    public int minOperations(List<Integer> nums, int k) {
        Set<Integer> collected = new HashSet<>();
        int count = 0;

        // Duyệt từ cuối mảng lên đầu
        for (int i = nums.size() - 1; i >= 0; i--) {
            count++;
            int x = nums.get(i);

            // Nếu x thuộc [1..k], thêm vào bộ sưu tập
            if (x <= k) {
                collected.add(x);
            }

            // Khi đã đủ 1..k → dừng lại
            if (collected.size() == k) {
                break;
            }
        }

        return count;
    }
}

     */
}
