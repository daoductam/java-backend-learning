import java.util.Map;

public class Q13_RomanToInteger {
    /*
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
Example 1:
Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

     */
    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }
/*
    Ý tưởng: space O(1) time O(N)
    - Duyệt chuỗi từ phải sang trái
    - Nếu giá trị nhỏ hơn giá trị trước đó → trừ đi và ngược lại
    - Dùng witch case để lấy gi trị

 */
public static int romanToInt(String s) {
    int total = 0;
    int truoc = 0;

    for (int i = s.length() - 1; i >= 0; i--) {
        int current;
        switch (s.charAt(i)) {
            case 'I': current = 1; break;
            case 'V': current = 5; break;
            case 'X': current = 10; break;
            case 'L': current = 50; break;
            case 'C': current = 100; break;
            case 'D': current = 500; break;
            case 'M': current = 1000; break;
            default: current = 0; // xử lý nếu có ký tự sai
        }

        if (current < truoc) {
            total -= current;
        } else {
            total += current;
        }

        truoc = current;
    }

    return total;
}


    /*
    Đáp án: space O(1) time O(N)
    - Dùng Map thay vì witch-case như trên
    - Dùng Map gọn hơn và linh hoạt hơn
     */
//    public static int romanToInt(String s) {
//        Map<Character, Integer> map = Map.of(
//                'I',1,
//                'V', 5,
//                'X',10,
//                'L',50,
//                'C',100,
//                'D',500,
//                'M',1000
//        );
//        int total =0;
//        for (int i = 0; i < s.length()  ; i++) {
//            if (i + 1 < s.length() &&
//                    ((s.charAt(i) == 'I' && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')) ||
//                            (s.charAt(i) == 'X' && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) ||
//                            (s.charAt(i) == 'C' && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')))) {
//
//                total -= map.get(s.charAt(i));
//            }
//            else {
//                total += map.get(s.charAt(i));
//
//            }
//
//        }
//        return total;
//    }
}
