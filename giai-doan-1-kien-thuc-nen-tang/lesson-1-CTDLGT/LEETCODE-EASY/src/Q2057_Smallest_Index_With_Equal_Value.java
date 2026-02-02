public class Q2057_Smallest_Index_With_Equal_Value {
    public static void main(String[] args) {

    }

    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }

        return -1;
    }
}
