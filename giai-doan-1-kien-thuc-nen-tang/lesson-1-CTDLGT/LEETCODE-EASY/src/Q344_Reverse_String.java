public class Q344_Reverse_String {
    /*
    Write a function that reverses a string. The input string is given as an array of characters s.
    You must do this by modifying the input array in-place with O(1) extra memory.
    Example 1:
    Input: s = ["h","e","l","l","o"]
    Output: ["o","l","l","e","h"]
    Example 2:
    Input: s = ["H","a","n","n","a","h"]
    Output: ["h","a","n","n","a","H"]
     */
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        reverseString(s);
        System.out.println(s);
    }
    /*
    Ý tưởng time O(n) space O(1)
    Duyệt từ i = 0 đến giữa (s.length/2)
    Đổi chỗ s[i] với phần tử đối xứng là s[s.length - i - 1]
    Không cần biến end, chỉ tính đối xứng trực tiếp
    - Nhược điểm nhỏ: s.length - i - 1 tính lại mỗi vòng lặp
     */
    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length/2; i++) {
            char tmp = s[i];
            s[i] = s[s.length-i-1];
            s[s.length-i-1] = tmp;
        }
    }
    /*
    Đáp án 1: time O(n) space O(1)
    start và end là 2 con trỏ, bắt đầu từ 2 đầu
    Mỗi vòng lặp: đổi chỗ 2 phần tử → tiến 2 con trỏ vào giữa
    Dừng khi start >= end
     */
//    public static void reverseString(char[] s) {
//        int start = 0;
//        int end = s.length - 1;
//        while (start < end) {
//            char ch = s[start];
//            s[start] = s[end];
//            s[end] = ch;
//            start++;
//            end--;
//        }
//    }

    /*
    Đap án 2:
    Giống ý tưởng 1, nhưng dùng for và biến j thay vì s.length - i - 1
    Mỗi vòng i tăng từ 0 → giữa, j giảm từ cuối về giữa
     */
//    public void reverseString(char[] s) {
//        int j = s.length-1;
//        for(int i = 0; i<s.length/2; i++){
//            char temp = s[i];
//            s[i]=s[j];
//            s[j]=temp;
//            j--;
//        }
//    }
}
