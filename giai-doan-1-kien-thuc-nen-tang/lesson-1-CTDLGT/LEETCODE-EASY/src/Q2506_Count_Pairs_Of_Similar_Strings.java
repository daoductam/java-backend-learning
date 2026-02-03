import java.util.*;

public class Q2506_Count_Pairs_Of_Similar_Strings {
    public static void main(String[] args) {

    }

    public int similarPairs(String[] words) {
        Map<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            // tạo set ký tự
            Set<Character> set = new HashSet<>();
            for (char c : word.toCharArray()) {
                set.add(c);
            }

            // chuyển set thành chuỗi key (sắp xếp để đảm bảo thứ tự cố định)
            List<Character> list = new ArrayList<>(set);
            Collections.sort(list);

            StringBuilder sb = new StringBuilder();
            for (char c : list) {
                sb.append(c);
            }
            String key = sb.toString();

            // cập nhật tần suất key
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        int result = 0;
        for (int count : freq.values()) {
            result += count * (count - 1) / 2; // C(k, 2)
        }

        return result;

    }
}
