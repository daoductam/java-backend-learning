public class Q1732_Find_the_Highest_Altitude {
    public static void main(String[] args) {

    }

    public int largestAltitude(int[] gain) {
        int kc = 0, max = 0;
        for (int i = 0; i < gain.length; i++) {
            kc += gain[i];
            if (kc > max) {
                max = kc;
            }
        }

        return max;
    }
}
