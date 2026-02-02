import java.util.Arrays;

public class Q1491_Average_Salary_Excluding_the_Minimum_and_Maximum_Salary {
    public static void main(String[] args) {

    }
    // Ý tưởng Time O(n log n) Space(n)
    public double average(int[] salary) {
        Arrays.sort(salary);
        double average =0;
        for (int i = 1; i < salary.length-1; i++) {
            average+=salary[i];
        }
        return average/(salary.length);
    }

    // Đáp án Time O(n) Space(1) -> ko cần sắp xeeps
//    public double average(int[] salary) {
//        int min = Integer.MAX_VALUE;
//        int max = Integer.MIN_VALUE;
//        int sum = 0;
//
//        for (int s : salary) {
//            sum += s;
//            if (s < min) min = s;
//            if (s > max) max = s;
//        }
//        return (double)(sum - min - max) / (salary.length - 2);
//    }
}
