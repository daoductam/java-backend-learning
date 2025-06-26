import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q88_Merge_Sorted_Array {
    /*
    You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.
The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
Example 1:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     */
    public static void main(String[] args) {
        int[] num1 = {1};;
        int[] num2 = {};
        merge(num1,1,num2,0);
        System.out.println(Arrays.toString(num1));
    }
    /*
    Ý tưởng: Space O(1) Time O(m+n)
    - Duyệt từ cuối về đầu
    - So sánh nums1[i] và nums2[j].
    - Sau vòng lặp, nếu nums2 còn dư thì chép nốt vào đầu nums1.
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;

        while (i >=0 && j >= 0) {
            if (nums1[i]>nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /*
    Đáp án: tương tu nhu trên nhưng có trick element1 = (p1 >= 0) ? nums1[p1] : Integer.MIN_VALUE;
    Khi p1 < 0 (hết phần tử trong nums1), ta giả sử element1 = -∞
    Điều này đảm bảo ưu tiên lấy từ nums2 nếu nums1 đã hết
    Tương tự với element2.
     */
//    public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        int p1 = m-1;
//        int p2 = n-1;
//        int p3 = nums1.length-1;
//
//        while (p3 >= 0 ) {
//            int element1;
//            int element2;
//            element1 = (p1>=0) ? nums1[p1] : Integer.MIN_VALUE;
//            element2= (p2>=0) ? nums2[p2] : Integer.MIN_VALUE;
//            if (element1>element2) {
//                nums1[p3] = element1;
//                p3--;p1--;
//            } else {
//                nums1[p3] = element2;
//                p3--;p2--;
//            }
//        }
//
//    }
}
