public class Q1859_Sorting_the_Sentence {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: O(n) O(n)
    - split " "
    - tạo 1 mảng khác để chứa
    - tìm sẽ cuối của từ là số nào rồi bảo ứng với index cx mảng tên
     */
    public String sortSentence(String s) {
        String[] list = s.split(" ");
        String[] result = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            int lastCharIndex = list[i].length()-1;
            int lastCharValue = list[i].charAt(lastCharIndex) - '0';
            result[lastCharValue-1] = list[i].substring(0, lastCharIndex);
        }
        return String.join(" ", result);
    }

    /*
    Đáp án: ngắn gon hơn O(n) O(n)

    public String sortSentence(String s) {
        String[] parts = s.split(" ");
        String[] sorted = new String[parts.length];

        for (String word : parts) {
            int index = word.charAt(word.length() - 1) - '0'; // lấy số ở cuối
            sorted[index - 1] = word.substring(0, word.length() - 1); // bỏ số
        }

        return String.join(" ", sorted);
    }
     */
}
