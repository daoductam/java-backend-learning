import java.util.Arrays;

public class Q1816_Truncate_Sentence {
    public static void main(String[] args) {

    }
    /*
    Ý tưởng O(n) O(n)
    Dùng s.split(" ") để tách thành mảng từ.
     */

    public String truncateSentence(String s, int k) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            if (i > 0) sb.append(" ");
            sb.append(words[i]);
        }
        return sb.toString();
    }

    /*
     Đáp án : Duyệt thủ công (tối ưu) O(n) O(1)
     public String truncateSentence(String s, int k) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
                if (count == k) {
                    return s.substring(0, i);
                }
            }
        }
        return s; // nếu k >= số từ trong câu
    }
     */
}
