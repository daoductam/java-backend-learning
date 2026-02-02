public class Q2784_Check_if_Array_is_Good {
    public static void main(String[] args) {

    }

    public boolean isGood(int[] nums) {
        int n = 0;
        for (int num : nums) n = Math.max(n, num);

        // phải đúng n + 1 phần tử
        if (nums.length != n + 1) return false;

        int[] count = new int[n + 1];
        for (int num : nums) {
            if (num > n) return false;      // không thể có số lớn hơn n
            count[num]++;
        }

        // kiểm tra từ 1 đến n - 1
        for (int i = 1; i < n; i++) {
            if (count[i] != 1) return false;
        }

        // số n xuất hiện phải là 2 lần
        return count[n] == 2;
    }

}
