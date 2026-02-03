import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q1636_Sort_Array_by_Increasing_Frequency {
    public static void main(String[] args) {

    }

    // Đáp án
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(arr, (a,b) -> {
            int fa = map.get(a);
            int fb = map.get(b);
            if (fa != fb) {
                return fa-fb;
            } else {
                return b-a;
            }
        });

        return Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
    }
}
