public class Q374_Guess_Number_Higher_or_Lower {
    /*
    We are playing the Guess Game. The game is as follows:
    I pick a number from 1 to n. You have to guess which number I picked.
    Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
    You call a pre-defined API int guess(int num), which returns three possible results:
    -1: Your guess is higher than the number I picked (i.e. num > pick).
    1: Your guess is lower than the number I picked (i.e. num < pick).
    0: your guess is equal to the number I picked (i.e. num == pick).
    Return the number that I picked.
    Example 1:
    Input: n = 10, pick = 6
    Output: 6
    Example 2:
    Input: n = 1, pick = 1
    Output: 1
    Example 3:
    Input: n = 2, pick = 1
    Output: 1
     */
    public static void main(String[] args) {

    }

    static int  pick ; // số đang được chọn, mô phỏng thôi

    // Đây là API đã cho sẵn
    public static int guess(int num) {
        if (num > pick) return -1;
        else if (num < pick) return 1;
        else return 0;
    }
    public static int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int result = guess(mid);

            if (result == 0) return mid;          // Đoán đúng
            else if (result == -1) right = mid - 1; // Đoán lớn quá
            else left = mid + 1;                   // Đoán nhỏ quá
        }

        return -1; // Trường hợp không xảy ra nếu pick hợp lệ

    }
}
