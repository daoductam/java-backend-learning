import java.util.*;

public class Q9_Palindrome_Number {
//    Given an integer x, return true if x is a palindrome, and false otherwise.
//            Example 1:
//
//    Input: x = 121
//    Output: true
//    Explanation: 121 reads as 121 from left to right and from right to left.
//            Example 2:
//
//    Input: x = -121
//    Output: false
//    Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
//    Example 3:
//
//    Input: x = 10
//    Output: false
//    Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
    public static void main(String[] args) {

        System.out.println(isPalindrome(121));
    }

    /*
    Ý tưởng
     - Chuyển số thành chuỗi rồi thành mảng các ký tự
     - Tạo danh sách từ mảng đó
     - Đảo ngược danh sách
     - So sánh danh sách ban đầu và danh sách đảo ngược
     */
    private static boolean isPalindrome(int x) {
        String[] a = String.valueOf(x).split("");
        List<String> b = new ArrayList<>(Arrays.asList(a)) ;
        List<String> c = new ArrayList<>(b);
        Collections.reverse(c);
        return b.equals(c);
    }

    /*
    Đáp án
     Tại sao lại tiếp cận theo cách này?
        Hiệu quả hơn vì chỉ cần so sánh trực tiếp các ký tự trong chuỗi, không cần tạo mảng hay danh sách phụ.
        Không tạo thêm biến không cần thiết (như mảng `a`, List `b`, List `c`...)
     */

//    public static boolean isPalindrome(int x) {
//        if (x < 0) {
//            return false;
//        }
//        String num = x +"";
//
//
//        return checkPalindrome(num);
//
//    }
//
//    public static boolean checkPalindrome(String num) {
//        int l =0;
//        int r = num.length()-1;
//        while (l<r) {
//            if (num.charAt(l) != num.charAt(r)) {
//                return false;
//            }
//            l++;
//            r--;
//        }
//        return true;
//    }
}
