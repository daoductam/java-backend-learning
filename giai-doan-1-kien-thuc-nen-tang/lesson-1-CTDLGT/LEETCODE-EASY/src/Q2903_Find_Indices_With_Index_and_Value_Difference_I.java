public class Q2903_Find_Indices_With_Index_and_Value_Difference_I {
    public static void main(String[] args) {

    }
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int maxIdx = -1, minIdx = -1;
        int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            // Cập nhật vùng i hợp lệ
            if (j >= indexDifference) {
                int i = j - indexDifference;
                if (nums[i] > maxVal) {
                    maxVal = nums[i];
                    maxIdx = i;
                }
                if (nums[i] < minVal) {
                    minVal = nums[i];
                    minIdx = i;
                }
            }

            // Kiểm tra điều kiện với max và min
            if (maxIdx != -1 && Math.abs(nums[j] - maxVal) >= valueDifference)
                return new int[]{maxIdx, j};
            if (minIdx != -1 && Math.abs(nums[j] - minVal) >= valueDifference)
                return new int[]{minIdx, j};
        }

        return new int[]{-1, -1};
    }
}
