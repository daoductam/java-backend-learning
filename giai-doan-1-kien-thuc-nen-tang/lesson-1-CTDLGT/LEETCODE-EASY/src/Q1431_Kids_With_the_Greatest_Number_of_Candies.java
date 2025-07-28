import java.util.ArrayList;
import java.util.List;
/*
There are n kids with candies. You are given an integer array candies, where
each candies[i] represents the number of candies the ith kid has, and an integer
extraCandies, denoting the number of extra candies that you have.
Return a boolean array result of length n, where result[i] is true if,
after giving the ith kid all the extraCandies, they will have the greatest
number of candies among all the kids, or false otherwise.
Note that multiple kids can have the greatest number of candies.
Example 1:
Input: candies = [2,3,5,1,3], extraCandies = 3
Output: [true,true,true,false,true]
Explanation: If you give all extraCandies to:
- Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
- Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
- Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
- Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
- Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
Example 2:
Input: candies = [4,2,1,1,2], extraCandies = 1
Output: [true,false,false,false,false]
Explanation: There is only 1 extra candy.
Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
Example 3:
Input: candies = [12,1,12], extraCandies = 10
Output: [true,false,true]

 */
public class Q1431_Kids_With_the_Greatest_Number_of_Candies {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng brute force - Time O (n^2) Space O(n)
    Với mỗi đứa trẻ, cộng extraCandies vào số kẹo của nó.
    So sánh với từng đứa trẻ khác. Nếu vẫn nhỏ hơn bất kỳ ai khác thì đánh dấu false, ngược lại là true
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < candies.length; i++) {
            int givenKid = candies[i] + extraCandies;
            boolean greater = true;
            for (int j = 0; j < candies.length; j++) {
                if (i!=j) {
                    if (givenKid < candies[j]) {
                        greater = false;
                        break;
                    }
                }
            }
            list.add(greater);
        }

        return list;
    }
    /*
    Đáp án Time O (n) Space O(n)
    - Chỉ duyệt 2 lần mảng.
    - tối ưu hơn
     */
//    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
//
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < candies.length; i++) {
//            if (candies[i] > max) {
//                max = candies[i];
//            }
//        }
//
//        List<Boolean> result = new ArrayList<>();
//        for (int i = 0; i < candies.length; i++) {
//            if (candies[i] + extraCandies >= max) {
//                result.add(true);
//            } else {
//                result.add(false);
//            }
//        }
//
//        return result;
//    }
}
