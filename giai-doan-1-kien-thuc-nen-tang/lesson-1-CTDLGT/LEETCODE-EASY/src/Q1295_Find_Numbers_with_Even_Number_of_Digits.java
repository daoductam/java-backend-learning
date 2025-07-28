public class Q1295_Find_Numbers_with_Even_Number_of_Digits {
    /*
    Given an array nums of integers, return how many of them contain an even number of digits.
    Example 1:
    Input: nums = [12,345,2,6,7896]
    Output: 2
    Explanation:
    12 contains 2 digits (even number of digits).
    345 contains 3 digits (odd number of digits).
    2 contains 1 digit (odd number of digits).
    6 contains 1 digit (odd number of digits).
    7896 contains 4 digits (even number of digits).
    Therefore only 12 and 7896 contain an even number of digits.
    Example 2:
    Input: nums = [555,901,482,1771]
    Output: 1
    Explanation:
    Only 1771 contains an even number of digits.
     */
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: Time O(n x k) Space O(1)
    - Duyệt qua từng phần tử trong mảng `nums`
    - Với mỗi số, đếm xem nó có bao nhiêu chữ số (bằng hàm `countDigits`)
    - Nếu số chữ số là chẵn (chia hết cho 2), thì tăng biến `count`
    - Trả về tổng số các số có số chữ số chẵn
     */
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (countDigits(nums[i]) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static int countDigits(int n) {
        n = Math.abs(n);
        int count = 0;
        do {
            count++;
            n /= 10;
        } while (n != 0);
        return count;
    }

    /*
    Đáp án
    - ko tách như ý tưởng
    - Có — nums[i] = nums[i] / 10; làm mất dữ liệu
    - Khó tái sử dụng
     */
//    public int findNumbers(int[] nums) {
//        if (nums.length == 0) {
//            return 0;
//        }
//
//        int count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int numOfDigits = 0;
//            while (nums[i] != 0) {
//                nums[i] = nums[i]/10;
//                numOfDigits++;
//            }
//            if (numOfDigits % 2 ==0 ) {
//                count ++;
//            }
//        }
//        return count;
//    }
}
