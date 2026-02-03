public class Q2529_Maximum_Count_of_Positive_Integer_and_Negative_Integer {
    public static void main(String[] args) {

    }

    public int maximumCount(int[] nums) {
        int neg = 0, zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                neg++;
            } else if (nums[i] == 0) {
                zero++;
            } else {
                return Math.max(neg, nums.length - neg - zero);
            }
        }
        return neg;
    }
/*  Duyeet mang
Giải thích:

Duyệt từng phần tử:

Nếu nhỏ hơn 0 → tăng biến neg.

Nếu lớn hơn 0 → tăng biến pos.

Kết quả là max(neg, pos).

    public int maximumCount(int[] nums) {
        int neg = 0;
        int pos = 0;
        for (int x : nums) {
            if (x < 0) neg++;
            else if (x > 0) pos++;
        }
        return Math.max(neg, pos);
    }
 */
/*
    Cách 2: Dùng Binary Search (O(log n)) — Tối ưu hơn
    Vì mảng đã được sắp xếp, ta có thể:
Tìm vị trí đầu tiên của 0 để biết có bao nhiêu số âm.
Tìm vị trí đầu tiên lớn hơn 0 để biết có bao nhiêu số dương.
Java có sẵn hàm Arrays.binarySearch(), nhưng cần xử lý cẩn thận khi không tìm thấy giá trị.

public int maximumCount(int[] nums) {
        int neg = lowerBound(nums, 0); // vị trí đầu tiên >= 0
        int pos = nums.length - upperBound(nums, 0); // số phần tử > 0
        return Math.max(neg, pos);
    }

    // Tìm vị trí đầu tiên có giá trị >= target
    public int maximumCount(int[] nums) {
        int neg = lowerBound(nums, 0); // vị trí đầu tiên >= 0
        int pos = nums.length - upperBound(nums, 0); // số phần tử > 0
        return Math.max(neg, pos);
    }

    // Tìm vị trí đầu tiên có giá trị >= target
    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // Tìm vị trí đầu tiên có giá trị > target
    private int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

 */


}