public class Q2544_Alternating_Digit_Sum {
    public static void main(String[] args) {

    }

    public int alternateDigitSum(int n) {
        int total = 0;
        String s = n + "";
        for (int i = 0; i < s.length(); i++) {
            int tmp = s.charAt(i) - '0';
            if (i % 2 ==0) {
                total += tmp;
            } else {
                total -= tmp;
            }
        }

        return total;
    }
    /*
    Cách 1: Dùng String
    String s = String.valueOf(n);
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0'; // chuyển ký tự sang số
            if (i % 2 == 0) {  // vị trí chẵn (0-based) là dấu +
                sum += digit;
            } else {           // vị trí lẻ là dấu -
                sum -= digit;
            }
        }

        return sum;
     */

    /*
    2️⃣ Cách tối ưu nhất (không dùng String)
    public int alternateDigitSum(int n) {
        int temp = n;
        int len = 0;

        // Đếm số chữ số
        while (temp > 0) {
            len++;
            temp /= 10;
        }

        int sign = (len % 2 == 0) ? -1 : 1; // nếu số chữ số chẵn thì chữ số cuối là âm
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += sign * digit;
            sign = -sign;  // đảo dấu xen kẽ
            n /= 10;
        }

        return sum;
    }
     */
}
