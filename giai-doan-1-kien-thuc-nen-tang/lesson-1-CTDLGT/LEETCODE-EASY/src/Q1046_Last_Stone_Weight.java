import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1046_Last_Stone_Weight {
    /*
    You are given an array of integers stones where stones[i] is the weight of the ith stone.
    We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest
    two stones have weights x and y with x <= y. The result of this smash is:
    If x == y, both stones are destroyed, and
    If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
    At the end of the game, there is at most one stone left.
    Return the weight of the last remaining stone. If there are no stones left, return 0.
    Example 1:
    Input: stones = [2,7,4,1,8,1]
    Output: 1
    Explanation:
    We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
    we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
    we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
    we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
    Example 2:
    Input: stones = [1]
    Output: 1

     */
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: Time O(n^2) Space O(n)
   Tìm chỉ số của 2 viên đá nặng nhất: max1 (lớn nhất), max2 (lớn nhì).
    Sau đó xóa 2 viên đó khỏi danh sách.
    Nếu chúng không bằng nhau, chèn viên đá mới có trọng lượng y - x.
     */
    public int lastStoneWeight(int[] stones) {
        List<Integer> list = new ArrayList<>();
        for (int stone : stones) {
            list.add(stone);
        }

        while (list.size() > 1) {
            int max1 = -1, max2 = -1;
            for (int i = 0; i < list.size(); i++) {
                int val = list.get(i);
                if (max1 == -1 || val > list.get(max1)) {
                    max2 = max1;
                    max1 = i;
                } else if (max2 == -1 || val > list.get(max2)) {
                    max2 = i;
                }
            }

            int y = list.get(max1);
            int x = list.get(max2);

            list.remove(Math.max(max1, max2));
            list.remove(Math.min(max1, max2));

            if (y != x) {
                list.add(y - x);
            }
        }

        return list.isEmpty() ? 0 : list.get(0);
    }

    /*
    Đáp án Time O(n logn) Space O(n)
    Dùng Max-Heap (PriorityQueue giảm dần) để luôn lấy ra được 2 viên đá nặng nhất nhanh chóng.
    Lấy 2 viên đá nặng nhất (y, x).
    Nếu khác nhau, chèn lại viên có trọng lượng mới y - x.
     */
//    public int lastStoneWeight(int[] stones) {
//        // Max heap: sắp xếp giảm dần
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
//        for (int stone : stones) {
//            pq.offer(stone);
//        }
//
//        while (pq.size() > 1) {
//            int y = pq.poll(); // viên nặng nhất
//            int x = pq.poll(); // viên nặng thứ 2
//            if (y != x) {
//                pq.offer(y - x); // thêm viên mới vào
//            }
//        }
//
//        return pq.isEmpty() ? 0 : pq.poll();
//    }
}
