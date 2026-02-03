import java.util.Arrays;

public class Q2574_Left_and_Right_Sum_Differences {
    public static void main(String[] args) {
        leftRightDifference(new int[]{10,4,8,3});
    }

    public static int[] leftRightDifference(int[] nums) {
        int[] a = new int[nums.length];
        int[] b = new int[nums.length];
        int[] rs = new int[nums.length];
        int suma=0;
        int sumb=0;
        for (int i = 1; i < nums.length; i++) {
            suma+=nums[i-1];
            a[i] = suma;
            System.out.println(a[i]);
        }

        for (int i = nums.length-1; i >0; i--) {
            sumb+=nums[i-1];
            b[i] = sumb;
            System.out.println(b[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            rs[i] = Math.abs(a[i]-b[i]);
        }
        return rs;

    }
}
