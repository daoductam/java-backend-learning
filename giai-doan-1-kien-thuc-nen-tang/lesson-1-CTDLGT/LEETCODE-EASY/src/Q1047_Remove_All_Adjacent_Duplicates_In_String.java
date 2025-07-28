import java.util.*;
import java.util.stream.Collectors;

public class Q1047_Remove_All_Adjacent_Duplicates_In_String {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng Space O(n) Time O(n)
    - Kiểm tra phần tử vừa thêm vào gần nhất (để xem có trùng với ký tự mới không).
    - Loại bỏ ký tự đó nếu trùng

     */
    public static String removeDuplicates(String s) {
        List<Character> list = new ArrayList<>();

        for (Character c : s.toCharArray()) {
            if(!list.isEmpty() && list.getLast() == c) {
                list.removeLast();
            }
            else {
                list.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : list) {
            sb.append(ch);
        }

        return sb.toString();
    }

    /*
    Đáp án Dùng stack Space O(n) Time O(n)
     */
//        public static String removeDuplicates(String s) {
//            Stack<Character> stack = new Stack<>();
//
//            for (char c : s.toCharArray()) {
//                // Nếu ký tự giống với ký tự trên cùng của stack → loại bỏ
//                if (!stack.isEmpty() && stack.peek() == c) {
//                    stack.pop(); // Loại bỏ cặp trùng nhau
//                } else {
//                    stack.push(c); // Thêm vào stack nếu không trùng
//                }
//            }
//
//            // Ghép các ký tự trong stack thành chuỗi kết quả
//            StringBuilder sb = new StringBuilder();
//            for (char c : stack) {
//                sb.append(c);
//            }
//
//            return sb.toString();





}
