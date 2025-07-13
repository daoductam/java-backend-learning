import java.util.Arrays;
import java.util.List;

public class Q557_Reverse_Words_in_a_String_III {
    /*
    Given a string s, reverse the order of characters in each word within a
    sentence while still preserving whitespace and initial word order.
    Example 1:
    Input: s = "Let's take LeetCode contest"
    Output: "s'teL ekat edoCteeL tsetnoc"
    Example 2:
    Input: s = "Mr Ding"
    Output: "rM gniD"

     */
    public static void main(String[] args) {

    }

    /*
    Ý tưởng Time O(n) Space O(n)
    Tách chuỗi s thành các từ riêng biệt bằng split(" ").
    Duyệt từng từ và đảo ngược nó bằng StringBuilder.reverse().
    Dùng String.join(" ", arr) để nối lại thành chuỗi cuối cùng, cách nhau bằng khoảng trắng.
     */
    public static String reverseWords(String s) {
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new StringBuilder(arr[i]).reverse().toString();
        }
        return String.join(" ", arr);
    }

    /*
    Đáp án Time O(n) Space O(n) dùng StringBuilder tổng thể
    Ưu điểm: hiệu suất tốt hơn một chút vì tránh tạo nhiều chuỗi trung gian.
     */

//    public String reverseWords(String s) {
//        String[] arr=s.split(" ");
//        StringBuilder ans=new StringBuilder();
//        for (int i = 0; i < arr.length; i++) {
//            ans.append(new StringBuffer(arr[i]).reverse());
//            if (i< arr.length-1){
//                ans.append(" ");
//            }
//        }
//        return ans.toString();
//    }
}
