import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q2465_Number_of_Distinct_Averages {
    public static void main(String[] args) {

    }

    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Double> set = new HashSet<>();
        for (int i = 0; i < nums.length/2 ; i++) {
            double ave =(double) (nums[i] + nums[nums.length-1-i]) / 2;
            set.add(ave);
        }
        return set.size();
    }
}
