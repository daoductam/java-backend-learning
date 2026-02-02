public class Q1796_Second_Largest_Digit_in_a_String {
    public static void main(String[] args) {

    }

    public int secondHighest(String s) {
        int max = -1;
        int result = -1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int crr = s.charAt(i) - '0' ;
                if (crr > max) {
                    result = max;
                    max = crr;
                } else if (crr < max && crr > result) {
                    result = crr;
                }


            }
        }
        return result;
    }

    /*
    Đáp án O(n) O(1) - Dùng Set

    public int secondHighest(String s) {
        // Dùng Set để loại trùng
        Set<Integer> digits = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.add(c - '0');
            }
        }

        if (digits.size() < 2) return -1; // Không đủ 2 số khác nhau

        // Tìm số lớn nhất và số lớn thứ 2
        int first = -1, second = -1;
        for (int d : digits) {
            if (d > first) {
                second = first;
                first = d;
            } else if (d > second && d < first) {
                second = d;
            }
        }

        return second;
    }

    // cách tối ưu nhất
    boolean[] seen = new boolean[10]; // chỉ có các digit 0..9

        // Đánh dấu số đã gặp
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                seen[c - '0'] = true;
            }
        }

        int count = 0;
        // Duyệt từ 9 về 0 để tìm largest và second largest
        for (int d = 9; d >= 0; d--) {
            if (seen[d]) {
                count++;
                if (count == 2) {
                    return d; // gặp số lớn thứ 2
                }
            }
        }

        return -1; // không có số lớn thứ 2
     */
}
