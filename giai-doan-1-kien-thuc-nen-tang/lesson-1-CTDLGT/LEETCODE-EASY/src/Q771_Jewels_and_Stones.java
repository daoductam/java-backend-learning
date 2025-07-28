import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Q771_Jewels_and_Stones {
    /*
    You're given strings jewels representing the types of stones that are jewels,
    and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
    Letters are case sensitive, so "a" is considered a different type of stone
    from "A".
    Example 1:
    Input: jewels = "aA", stones = "aAAbbbb"
    Output: 3
    Example 2:
    Input: jewels = "z", stones = "ZZ"
    Output: 0
     */
    public static void main(String[] args) {

    }
    /*
    Ý tưởng:Time O(n2) Space O(n)
    -  tách chuỗi stones thành mảng các ký tự
    - Với mỗi ký tự, dùng jewels.contains(c) để kiểm tra xem có phải đá quý không.
    - Nhược: jewels.contains(c) chạy O(n) mỗi lần (vì String.contains() duyệt từng ký tự).
        Tổng thời gian: O(m * n) với m = stones.length() và n = jewels.length()
     */
    public static int numJewelsInStones(String jewels, String stones) {
        int total =0;

        for (String c : stones.split("")) {
            if (jewels.contains(c)) {
                total+=1;
            }
        }

        return total;
    }
    /*
    Đáp án: O(n) Space O(n)
    HashSet.contains() có độ phức tạp O(1)
    Độ phức tạp tổng thể: O(m + n) (rất tối ưu)
    O(n) để thêm jewels vào set
    O(m) để duyệt stones
     */
//    public static int numJewelsInStones(String jewels, String stones) {
//        int count =0;
//        Set<Character> set = new HashSet<>();
//        for (char c : jewels.toCharArray()) {
//            set.add(c);
//        }
//
//        for (char c : stones.toCharArray()) {
//            if (set.contains(c)) {
//                count++;
//            }
//        }
//
//        return count;
//    }
}
