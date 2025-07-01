

public class Main {
    /*
    A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward
     Alphanumeric characters include letters and numbers.
Given a string , return if it is a palindrome, or otherwise.struefalse
Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
     */
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));

    }

    /*
    Ý tưởng: Time O(n) Space O(n)
    - toLowerCase() chuẩn hóa toàn bộ về chữ thường.
    - replaceAll("[^a-z0-9]", "") xóa hết ký tự không phải chữ/số.
    - Chia đôi và So sánh từ ngoài vào trong bằng charAt() để kiểm tra tính đối xứng.
     */
    public static boolean isPalindrome(String s) {
        String a = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        for (int i = 0; i < (a.length()/2); i++) {
            if (a.charAt(i) != a.charAt(a.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

    /*
    Đáp án: Time O(n) Space O(n)
    - Dùng StringBuilder và Character.isLetter/isDigit()
    - Không dùng đến regex
     */
//    public static boolean checkPalindrome(String s) {
//        int left = 0;
//        int right = s.length() - 1;
//        while (left < right) {
//            if (s.charAt(left) != s.charAt(right)) {
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
//    }
//
//    public static boolean isPalindrome(String s){
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            if (Character.isLetter(ch) || Character.isDigit(ch)) {
//                sb.append(ch);
//            }
//        }
//        String rs = sb.toString();
//        rs= rs.toLowerCase();
//        if (checkPalindrome(rs)) {
//            return true;
//        }
//        return false;
//    }

    /*
    Cach chat gpt kiểm tra palindrome bằng 2 con trỏ mà không tạo chuỗi mới
    space O(1) time O(n)
     */
//    public static boolean isPalindrome(String s) {
//        int left = 0;
//        int right = s.length() - 1;
//
//        while (left < right) {
//            // Bỏ qua ký tự không phải chữ/số
//            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
//                left++;
//            }
//            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
//                right--;
//            }
//
//            // So sánh không phân biệt hoa thường
//            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
//                return false;
//            }
//
//            left++;
//            right--;
//        }
//
//        return true;
//    }
}