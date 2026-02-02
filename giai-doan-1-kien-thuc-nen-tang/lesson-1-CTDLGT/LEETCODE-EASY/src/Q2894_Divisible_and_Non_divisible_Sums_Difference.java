public class Q2894_Divisible_and_Non_divisible_Sums_Difference {
    public static void main(String[] args) {

    }

    public int differenceOfSums(int n, int m) {
        int di= 0, ndi = 0;
        for (int i = 1; i <= n; i++) {
            if (i%m==0) {
                di += i;
            } else {
                ndi += i;
            }
        }
        return ndi - di;
    }
}
