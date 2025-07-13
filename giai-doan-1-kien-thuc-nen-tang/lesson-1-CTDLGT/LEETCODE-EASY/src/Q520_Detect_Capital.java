public class Q520_Detect_Capital {
    /*
    We define the usage of capitals in a word to be right when one of the following cases holds:
    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital, like "Google".
    Given a string word, return true if the usage of capitals in it is right.
    Example 1:
    Input word = "USA"
    Output: true
    Example 2:
    Input: word = "FlaG"
    Output: false
     */
    public static void main(String[] args) {

    }
    /*
    Ý tưởng: Time  O(n) Space O(n)
    Nếu chữ đầu tiên là chữ thường, thì toàn bộ chuỗi phải là viết thường → word.equals(word.toLowerCase())
    Ngược lại, chữ đầu tiên là viết hoa, thì phần còn lại (từ vị trí 1 trở đi) phải:
    hoặc toàn bộ viết hoa → VD: "USA"
    hoặc toàn bộ viết thường → VD: "Google"


     */
    public static boolean detectCapitalUse(String word) {
        if (word.charAt(0) != Character.toUpperCase(word.charAt(0))) {
            return word.equals(word.toLowerCase());
        } else {
            String a = word.substring(1);
            return a.equals(a.toUpperCase()) || a.equals(a.toLowerCase());
        }
    }

    /*
    Đáp án không dùng chuỗi phụ Time  O(n) Space O(1)
    Đếm số chữ cái viết hoa (isUpperCase).
    Kiểm tra 3 trường hợp đúng:
    Tất cả viết hoa: ví dụ "USA" ⇒ count == n
    Tất cả viết thường: ví dụ "leetcode" ⇒ count == 0
    Chỉ chữ cái đầu tiên viết hoa: ví dụ "Google" ⇒ count == 1 và ký tự đầu viết hoa.
     */
//    public static boolean detectCapitalUse(String word) {
//        int uppercaseCount = 0;
//        int n = word.length();
//
//        for (int i = 0; i < n; i++) {
//            if (Character.isUpperCase(word.charAt(i))) {
//                uppercaseCount++;
//            }
//        }
//        return uppercaseCount == n ||
//                uppercaseCount == 0 ||
//                (uppercaseCount == 1 && Character.isUpperCase(word.charAt(0)));
//    }

}
