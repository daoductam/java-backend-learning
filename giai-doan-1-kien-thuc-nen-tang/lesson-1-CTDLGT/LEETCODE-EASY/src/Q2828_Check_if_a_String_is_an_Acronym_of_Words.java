import java.util.List;

public class Q2828_Check_if_a_String_is_an_Acronym_of_Words {
    public static void main(String[] args) {

    }

    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != words.get(i).charAt(0)) {
                return false;
            }
        }
        return true;
    }
    /*

    Đáp án
     public boolean isAcronym(List<String> words, String s) {
        StringBuilder acronym = new StringBuilder();

        // Bước 1: duyệt qua từng từ
        for (String word : words) {
            acronym.append(word.charAt(0)); // lấy ký tự đầu tiên
        }

        // Bước 2: so sánh với s
        return acronym.toString().equals(s);
    }
     */
}
