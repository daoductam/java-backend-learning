import java.util.HashMap;
import java.util.Map;

public class Q2815_Max_Pair_Sum_in_an_Array {
    public static void main(String[] args) {

    }

    public int maxSum(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {


        }
    }

    public static int findMax(int n) {
        int max = 0;
        while (n > 0) {
            int digit = n %10;
            if (digit > max) {
                max = digit;
            }
            n = n /10;
        }
        return max;
    }
}


class Solution {

    /**
     * Hàm chính để giải bài toán
     */
    public int maxSum(int[] nums) {
        // Map để lưu: <Chữ số lớn nhất (key), Số lớn nhất (value) tương ứng>
        // Có thể dùng mảng int[10] để tối ưu, nhưng HashMap rõ ràng hơn
        Map<Integer, Integer> maxNumForDigit = new HashMap<>();

        int maxSum = -1; // Kết quả cuối cùng

        for (int num : nums) {
            // 1. Tìm chữ số lớn nhất của số hiện tại
            int largestDigit = getLargestDigit(num);

            // 2. Kiểm tra xem đã có số nào cùng nhóm 'largestDigit' chưa
            if (maxNumForDigit.containsKey(largestDigit)) {
                // 3. Nếu có, ta tìm được một cặp

                // Lấy số lớn nhất đã lưu cho nhóm này
                int otherNum = maxNumForDigit.get(largestDigit);

                // Tính tổng của cặp hiện tại
                int currentSum = num + otherNum;

                // Cập nhật maxSum toàn cục
                maxSum = Math.max(maxSum, currentSum);

                // 4. Quan trọng: Cập nhật lại map để nó luôn giữ số LỚN NHẤT
                // Vì 'num' hiện tại có thể lớn hơn 'otherNum' đã lưu
                maxNumForDigit.put(largestDigit, Math.max(num, otherNum));

            } else {
                // 5. Nếu chưa có, đây là số đầu tiên của nhóm này
                // Chỉ cần thêm nó vào map
                maxNumForDigit.put(largestDigit, num);
            }
        }

        // 6. Trả về kết quả
        return maxSum;
    }

    /**
     * Hàm phụ để tìm chữ số lớn nhất của một số n
     */
    public int getLargestDigit(int n) {
        if (n == 0) {
            return 0;
        }

        n = Math.abs(n);
        int maxDigit = 0;

        while (n > 0) {
            int digit = n % 10;
            if (digit > maxDigit) {
                maxDigit = digit;
            }
            n = n / 10;
        }

        return maxDigit;
    }
}
