import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q1_TwoSum {
    public static void main(String[] args) {
        int[] a = twoSum(new int[]{2,7,11,15}, 9);
        System.out.println(Arrays.toString(a));
    }
//    My Code -> O(n^2)
//    public static int[] twoSum(int[] nums, int target) {
//        for (int i =0; i<nums.length-1;i++) {
//            for (int j = i+1; j<nums.length-1;j++) {
//                int need = target - nums[j];
//                if(nums[i] == need) {
//                    return new int[]{i,j};
//                }
//            }
//        }
//        return null;
//    }

    // Đáp án -> O(n)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("No Match");
    }


}