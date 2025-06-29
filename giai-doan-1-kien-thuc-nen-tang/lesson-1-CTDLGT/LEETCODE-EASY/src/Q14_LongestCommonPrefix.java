
public class Q14_LongestCommonPrefix {
    /*
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".
    Example 1:
    Input: strs = ["flower","flow","flight"]
    Output: "fl"
    Example 2:
    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
     */
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {
                "flower","flow","flight"
        }));
    }
    /*
    Ý tưởng: space O(1) time O(mxn)
    - Lấy phần tử đầu để so sánh
    - Duyệt từ phần từ 2
    - So sánh phần tử đầu với từng phần tử -> = phần chung
    - Nếu phần chung rỗng th return lun
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String first = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int min = Math.min(first.length(), strs[i].length());
            int j = 0;
            while (j < min && first.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            first = first.substring(0, j);
            if (first.isEmpty()) return "";
        }
        return first;
    }

    /*
    Đáp án: space O(1) time O(mxn)
    - So sánh theo chiều dọc từng ký tự tại cùng vị trí i giữa các chuỗi.
    - ngắn hơn code trên 
     */

//    public static String longestCommonPrefix(String[] strs) {
//
//        if (strs.length == 0) {
//            return "";
//        }
//
//        for (int i = 0; i < strs[0].length(); i++) {
//            char a = strs[0].charAt(i);
//
//            for (int j = 1; j < strs.length ; j++) {
//                if (i == strs[j].length() || strs[j].charAt(i) != a) {
//                    return strs[0].substring(0,i);
//                }
//            }
//        }
//
//        return strs[0];
//    }
}
