import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
/*
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
Example 1:
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
 */
public class Q338_Counting_Bits {
    public static void main(String[] args) {

    }
    /*
    Ý tưởng O(n log n)
    - Duyệt từng số từ 0 đến n
    - Chuyển số thành chuỗi nhị phân (binary string)
    - Đếm số lượng ký tự '1' trong chuỗi
     */
    public static int[] countBits(int n) {
        int[] ans = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            String so = Integer.toBinaryString(i);
            int count = 0;
            for (int j = 0; j < so.length(); j++) {
                if (so.charAt(j) == '1') {
                    count++;
                }
            }
            ans[i] = count;
        }
        return ans;
        
    }

    /*
    Đáp án  O(n) (Dynamic Programming)
    Sử dụng quy luật nhị phân và lập trình động:
    - i >> 1 tức là i chia 2 → giống như bỏ bớt 1 bit cuối
    - (i & 1) là phần bit cuối cùng (nếu là 1 thì cộng thêm 1)

     */

//        public static int[] countBits(int n) {
//            int[] ans = new int[n + 1];
//            ans[0] = 0; // số 0 có 0 bit 1
//
//            for (int i = 1; i <= n; i++) {
//                ans[i] = ans[i >> 1] + (i & 1);
//            }
//
//            return ans;
//        }


}
