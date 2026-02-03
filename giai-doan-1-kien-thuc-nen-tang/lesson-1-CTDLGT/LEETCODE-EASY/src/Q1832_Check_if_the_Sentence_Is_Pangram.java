import java.util.HashSet;
import java.util.Set;

public class Q1832_Check_if_the_Sentence_Is_Pangram {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng Dùng Set
     */
    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < sentence.length(); i++) {
            set.add(sentence.charAt(i));
        }

        return set.size() == 26;
    }

    /*
    Đáp án O(n) O(1)
    public boolean checkIfPangram(String sentence) {
    boolean[] seen = new boolean[26];
    for (char c : sentence.toCharArray()) {
        seen[c - 'a'] = true;
    }
    // kiểm tra đủ 26 chữ cái chưa
    for (boolean b : seen) {
        if (!b) return false;
    }
    return true;
}
     */
}
