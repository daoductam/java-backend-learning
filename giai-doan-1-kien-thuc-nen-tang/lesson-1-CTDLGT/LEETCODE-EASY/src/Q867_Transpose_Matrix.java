public class Q867_Transpose_Matrix {
    /*
    Given a 2D integer array matrix, return the transpose of matrix.
    The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
    Example 1:
    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [[1,4,7],[2,5,8],[3,6,9]]
    Example 2:
    Input: matrix = [[1,2,3],[4,5,6]]
    Output: [[1,4],[2,5],[3,6]]

     */
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: Time O(n.m) Space O(n.m)
    - Đảo cột thành hàng haàng thành cột
     */
    public static int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] rs = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rs[j][i] = matrix[i][j];
            }
        }

        return rs;
    }
    /*
    Đáp án: Y hệt
     */
}
