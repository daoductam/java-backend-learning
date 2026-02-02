public class Q1822_Sign_of_the_Product_of_an_Array {
    public static void main(String[] args) {

    }

    public int arraySign(int[] nums) {
        int rs = -1;
        int count = 0;
        for (int num : nums) {

            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                count++;
            }

        }
        if (count%2== 0) {
            rs = 1;
        }

        return rs;
    }

    /*
    Đáp ans ngắn gọn hơn O(n) O(1)
    public int arraySign(int[] nums) {
        int countNeg = 0;

        for (int num : nums) {
            if (num == 0) return 0;  // có số 0 thì product = 0
            if (num < 0) countNeg++;
        }

        return (countNeg % 2 == 0) ? 1 : -1;
    }
     */

}
