import java.util.HashSet;
import java.util.Set;

public class Q2670_Find_the_Distinct_Difference_Array {
    public static void main(String[] args) {

    }

    /*
    Bạn được cho một mảng nums đánh chỉ số từ 0 và có độ dài n.

Mảng distinct difference của nums là một mảng diff có độ dài n, trong đó:

diff[i] = (số lượng phần tử *khác nhau* trong đoạn prefix nums[0, ..., i])
          − (số lượng phần tử *khác nhau* trong đoạn suffix nums[i + 1, ..., n − 1])


Hãy trả về mảng distinct difference của nums.
     */

    /*
    Ý tưởng giải nhanh gọn

Duyệt từ trái sang phải để tính số lượng phần tử khác nhau trong mỗi prefix và lưu vào prefixCount[i].

Duyệt từ phải sang trái để tính số lượng phần tử khác nhau trong mỗi suffix và lưu vào suffixCount[i].

Cuối cùng:

diff[i] = prefixCount[i] - suffixCount[i]
     */
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] diff = new int[n];

        // Tính số phần tử khác nhau ở prefix
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            seen.add(nums[i]);
            prefix[i] = seen.size();
        }

        // Tính số phần tử khác nhau ở suffix
        seen.clear();
        for (int i = n - 1; i >= 0; i--) {
            seen.add(nums[i]);
            suffix[i] = seen.size();
        }

        // Tính diff[i] = prefix[i] - suffix[i+1]
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                diff[i] = prefix[i];    // suffix rỗng → 0
            } else {
                diff[i] = prefix[i] - suffix[i + 1];
            }
        }

        return diff;
    }
}
