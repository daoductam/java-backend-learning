public class Q1678_Goal_Parser_Interpretation {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: Dùng replace O(n) O(n)
     */

    public String interpret(String command) {
        String characters = command.replace("()","o");
        String rs = characters.replace("(al)","al");
        return rs;
    }

    /*
    Đáp án: Duyệt từng ký tự (parser) O(n) O(n)
    - Sử dụng StringBuilder để nối chuỗi → tránh tạo ra nhiều string trung gian.

    public String interpret(String command) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            if (c == 'G') {
                result.append('G');
            } else if (c == '(') {
                if (command.charAt(i + 1) == ')') {
                    result.append('o');
                    i++; // bỏ qua ')'
                } else {
                    result.append("al");
                    i += 3; // bỏ qua "al)"
                }
            }
        }
        return result.toString();
    }
     */
}
