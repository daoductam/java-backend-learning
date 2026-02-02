public class Q2540_Minimum_Common_Value {
    public static void main(String[] args) {

    }

    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j =0;
        int maxLength =nums1.length+nums2.length;
        if (nums1[0] > nums2[nums2.length-1] || nums2[0] > nums1[nums1.length-1]) {
            return -1;
        }
        for (int k = 0; k < maxLength-1; k++) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return -1;
    }

    /*
    Cách 1: Two Pointers (O(n + m)) — Cách tối ưu nhất
     public int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i]; // phần tử chung nhỏ nhất
            } else if (nums1[i] < nums2[j]) {
                i++; // tăng con trỏ nhỏ hơn
            } else {
                j++;
            }
        }
        return -1; // không có phần tử chung
    }
     */

    /*
    Cách 2: Dùng HashSet (O(n + m), nhưng tốn bộ nhớ hơn)
    public int getCommon(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums1) set.add(x);
        for (int x : nums2) {
            if (set.contains(x)) return x;
        }
        return -1;
    }
     */
}
