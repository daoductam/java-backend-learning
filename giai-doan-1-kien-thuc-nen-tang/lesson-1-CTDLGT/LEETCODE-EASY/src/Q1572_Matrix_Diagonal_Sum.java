public class Q1572_Matrix_Diagonal_Sum {
    public static void main(String[] args) {


    }

    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            // cộng đường chéo chính
            sum += mat[i][i];
            // cộng đường chéo phụ (trừ khi trùng với chéo chính)
            if (i != n - 1 - i) {
                sum += mat[i][n - 1 - i];
            }
        }

        return sum;
    }
}
