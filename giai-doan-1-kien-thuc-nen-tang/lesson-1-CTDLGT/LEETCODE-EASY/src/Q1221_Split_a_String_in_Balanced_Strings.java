public class Q1221_Split_a_String_in_Balanced_Strings {
    /*
    Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
    Given a balanced string s, split it into some number of substrings such that:
    Each substring is balanced.
    Return the maximum number of balanced strings you can obtain.
    Example 1:
    Input: s = "RLRRLLRLRL"
    Output: 4
    Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
    Example 2:
    Input: s = "RLRRRLLRLL"
    Output: 2
    Explanation: s can be split into "RL", "RRRLLRLL", each substring contains same number of 'L' and 'R'.
    Note that s cannot be split into "RL", "RR", "RL", "LR", "LL", because the 2nd and 5th substrings are not balanced.
    Example 3:
    Input: s = "LLLLRRRR"
    Output: 1
    Explanation: s can be split into "LLLLRRRR".

     */
    public static void main(String[] args) {

    }
    /*
    Ý tưởng Greedy - Chiến thuật tham lam Time O(n) space O(1)
    Ta duyệt từng ký tự trong chuỗi s, dùng một biến balance để đếm sự chênh lệch giữa số lượng L và R.
    Khi gặp 'L' → tăng balance
    Khi gặp 'R' → giảm balance
    Mỗi khi balance == 0 nghĩa là ta đã tìm được một chuỗi con cân bằng (vì số lượng L và R bằng nhau). Khi đó, tăng biến count lên 1.
     */
    public static int balancedStringSplit(String s) {
        int count = 0;
        int balance = 0;
        for (Character c : s.toCharArray()) {
            if (c == 'L') {
                balance++;
            } else  balance --;
            if (balance == 0) {
                count++;
            }

        }

        return count;
    }
    /*
    Đáp án Tương tu Time O(n) space O(1)
     */
//    public static int balancedStringSplit(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        int count = 0;
//        int left = 0, right =0;
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            if (ch=='L') {
//                left++;
//            }else {
//                right++;
//            }
//            if (left == right) {
//                count++;
//            }
//        }
//
//        return count;
//    }
}
