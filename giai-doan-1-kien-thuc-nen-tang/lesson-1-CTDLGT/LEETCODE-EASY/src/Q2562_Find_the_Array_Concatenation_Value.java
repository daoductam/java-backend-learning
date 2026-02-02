import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q2562_Find_the_Array_Concatenation_Value {
    public static void main(String[] args) {

    }

    public long findTheArrayConcVal(int[] nums) {
        List<Integer> list = Arrays.stream(nums)
                .boxed().toList();
        long total =0;

        for (int i = 0; i < list.size()/2; i++) {
            String a = list.get(i) +""+ list.get(list.size() - 1-i);
            total += Long.parseLong(a);
        }
        if (list.size()%2==1) {
            total+=list.get(list.size()/2);
        }
        return total;
    }

    /*
    public long findTheArrayConcVal(int[] nums) {
        int i = 0, j = nums.length - 1;
        long sum = 0;

        while (i <= j) {
            if (i == j) {
                sum += nums[i]; // chỉ còn 1 phần tử
            } else {
                // Ghép 2 số lại
                String concat = String.valueOf(nums[i]) + String.valueOf(nums[j]);
                sum += Long.parseLong(concat); // chuyển thành số và cộng
            }
            i++;
            j--;
        }

        return sum;
    }
     */

    /*
    Cải tiến (không dùng String)

Nếu bạn muốn tối ưu hiệu năng hơn (tránh tạo chuỗi tạm):
        public long findTheArrayConcVal(int[] nums) {
        int i = 0, j = nums.length - 1;
        long sum = 0;

        while (i <= j) {
            if (i == j) {
                sum += nums[i];
            } else {
                int a = nums[i], b = nums[j];
                int multiplier = 1;
                // nhân để "dịch" phần b qua phải
                while (b >= multiplier) multiplier *= 10;
                sum += (long)a * multiplier + b;
            }
            i++;
            j--;
        }

        return sum;
     */
}
