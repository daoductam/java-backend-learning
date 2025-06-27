import java.util.*;

public class Q121_Best_Time_to_Buy_and_Sell_Stock {
    /*
   You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

     */
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }
    /*
    Ý tưởng: Time O(n) Space O(1)
    - buy là giá mua thấp nhất từng thấy trước thời điểm i
    - Duyệt qua mảng prices từ i = 1 đến prices.length - 1
    - Nếu giá hiện tại < buy, thì cập nhật buy
    - Tính lợi nhuận nếu bán hôm nay: tmp = prices[i] - buy
    - Nếu lợi nhuận cao hơn max, cập nhật max
     */
    public static int maxProfit(int[] prices) {
        int buy = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            }
            int tmp=prices[i] - buy;
            if (tmp > max) {
                max= tmp;
            }
        }
        return max;

    }

    /*
    Đáp án: Time O(n) Space O(1)
    - Khởi tạo min = Integer.MAX_VALUE để luôn tìm được giá mua thấp nhất đầu tiên
    - Cập nhật min nếu prices[i] nhỏ hơn
    - Tính lợi nhuận: prices[i] - min
    - Cập nhật maxProfit nếu lớn hơn
     */
//    public static int maxProfit(int[] prices) {
//        int min = Integer.MAX_VALUE;
//        int maxProfit = 0;
//        for (int i = 0; i < prices.length; i++) {
//            if (prices[i] < min) {
//                min = prices[i];
//
//            }
//            int currentProfit = prices[i] - min;
//            if (currentProfit > maxProfit) {
//                maxProfit = currentProfit;
//            }
//        }
//        return maxProfit;
//    }

}
