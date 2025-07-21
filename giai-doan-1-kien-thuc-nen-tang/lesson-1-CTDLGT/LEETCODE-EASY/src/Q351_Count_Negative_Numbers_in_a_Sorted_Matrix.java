import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.
Example 1:
Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
Example 2:
Input: grid = [[3,2],[1,0]]
Output: 0
 */

public class Q351_Count_Negative_Numbers_in_a_Sorted_Matrix {
    public static void main(String[] args) {

    }
    /*
    Ý tưởng Time O(m x n) Space O(1) (Brute-force dùng Stream)
    Arrays.stream(grid) → Stream của các dòng (int[])
    flatMapToInt(Arrays::stream) → chuyển mỗi int[] thành Stream<Integer>, rồi gộp lại (flatten)
    .filter(s -> s < 0) → chỉ giữ lại số âm
    .count() → đếm số phần tử âm
     */
    public static int countNegatives(int[][] grid) {
        int[] arr = Arrays.stream(grid)
                .flatMapToInt(Arrays::stream)
                .filter(s -> s < 0)
                .toArray();
        return arr.length;
    }

    /*
    Đáp án Tối ưu dùng tính chất ma trận Time O(m + n) Space O(1)
    - Vì mỗi hàng/cột đều giảm dần, nên nếu gặp grid[row][col] < 0, thì tất cả các phần tử bên dưới cùng cột đó cũng sẽ < 0.
    - Nếu phần tử hiện tại âm → đếm và sang trái
    - Nếu không âm → xuống hàng tiếp theo


     */
//    public static int countNegatives(int[][] grid) {
//        int totalRows = grid.length;
//        int totalCols = grid[0].length;
//        int row = 0, col = totalCols -1, count =0;
//        while (row < totalRows && col >= 0) {
//            if (grid[row][col] < 0) {
//                col--;
//                count += totalRows - row;
//            }
//            else {
//                row ++;
//            }
//        }
//
//        return  count;
//    }

}
