import java.util.Arrays;

public class Q1619_Mean_of_Array_After_Removing_Some_Elements {
    public static void main(String[] args) {


    }
    /*
    Ý tưởng: O(n log n) O(1)
    - Sort mảng.
    - Bỏ k phần tử đầu và k phần tử cuối.
    - Tính tổng các phần tử còn lại, chia cho (n - 2k)
     */

    public double trimMean(int[] arr) {
        int k = arr.length / 20;
        Arrays.sort(arr);
        double total =0;
        for (int i = k; i < arr.length-k; i++) {
            total+=arr[i];
        }
        return total/(arr.length-2*k);
    }

    /*
    Đáp án O(n log n) O(1)

    public static double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int k = n / 20; // 5%

        int sum = 0;
        for (int i = k; i < n - k; i++) {
            sum += arr[i];
        }

        return (double) sum / (n - 2 * k);
    }
     */
}
