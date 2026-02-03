public class Q1550_Three_Consecutive_Odds {
    public static void main(String[] args) {

    }

    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int j : arr) {

            if (j % 2 == 1) {
                count++;

            } else {
                count = 0;
            }

            if (count == 3) {
                return true;
            }
        }
        return false;
    }
}
