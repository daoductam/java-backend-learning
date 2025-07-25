import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q1528_Shuffle_String {
    /*
You are given a string s and an integer array indices of the same length. The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.

Return the shuffled string.
Example 1:
Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
Output: "leetcode"
Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
Example 2:
Input: s = "abc", indices = [0,1,2]
Output: "abc"
Explanation: After shuffling, each character remains in its position.
     */
    public static void main(String[] args) {

    }

    /*
    Ý tưởng Time O(n) Space O(n) -> nên dùng
    - Dùng một mảng char[] để lưu kết quả.
    - Sau đó, chuyển mảng chars thành chuỗi bằng:

     */
    public String restoreString(String s, int[] indices) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]] = s.charAt(i);
        }
        return new String(chars);
    }

    /*
    Đáp án Time O(n) Space O(n)
    - Việc dùng rs += chars[i] sẽ tạo nhiều chuỗi tạm thời, gây tốn bộ nhớ và chậm vì String là immutable.
    - Với chuỗi lớn, độ phức tạp thời gian sẽ gần O(n²).
     */

//    public String restoreString(String s, int[] indices) {
//        if (s == null || s.length() == 0) {
//            return "";
//        }
//
//        if (s.length() != indices.length) {
//            return "";
//        }
//
//        char[] chars = new char[s.length()];
//        for (int i = 0; i < indices.length; i++) {
//            int pos= indices[i];
//            chars[pos] = s.charAt(i);
//        }
//
//        String rs = "";
//        for (int i = 0; i < chars.length; i++) {
//            rs += chars[i];
//        }
//        return rs;
//    }
}
