import java.util.HashMap;
import java.util.Map;

public class Q2180_Count_Integers_with_Even_Digit_Sum {
    public int countEven(int num) {
        int count =0;
        for (int i = 1; i <= num; i++) {
            int sum = 0;
            if (i > 9) {

                sum = sumDigit(i);
            } else {
                sum+=i;
            }
            if (sum%2==0) {
                count++;
            }

        }
        return count;
    }

    public static int sumDigit(int num) {
        if (num == 0) return 0;
        return (num % 10) + sumDigit(num / 10);
    }
}
