import java.util.Arrays;

public class Q1491_Average_Salary_Excluding_the_Minimum_and_Maximum_Salary {
    /*
You are given an array of unique integers salary where salary[i] is
the salary of the ith employee.
Return the average salary of employees excluding the minimum and maximum salary.
 Answers within 10-5 of the actual answer will be accepted.
Example 1
Input: salary = [4000,3000,1000,2000]
Output: 2500.00000
Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
Example 2:
Input: salary = [1000,2000,3000]
Output: 2000.00000
Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
Average salary excluding minimum and maximum salary is (2000) / 1 = 2000

     */
    public static void main(String[] args) {

    }
    /*
    Ý tưởng: Dùng Arrays.sort() Time O(n log n) Space (1)
     */
    public double average(int[] salary) {
        Arrays.sort(salary);
        int total = 0;
        for (int j : salary) {
            total += j;
        }
        return (double) (total- salary[0] - salary[salary.length-1])/(salary.length-2);
    }

    /*
    Đáp án   Time O(n log n) Space (1)
    Duyệt một lần:
    Tính tổng sum
    Tìm min và max
     */
//    public double average(int[] salary) {
//        int n=salary.length;
//        int min=Integer.MAX_VALUE;
//        int max=Integer.MIN_VALUE;
//        double sum=0;
//        for(int i=0;i<n;i++){
//            sum+=salary[i];
//            min=Math.min(min,salary[i]);
//            max=Math.max(max,salary[i]);
//        }
//        double avg=(sum-min-max)/(n-2);
//        return avg;
//    }
}
