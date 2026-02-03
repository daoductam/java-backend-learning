import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q2085_Count_Common_Words_With_One_Occurrence {
    public static void main(String[] args) {

    }

    /*
    Ý tưởng: Dùng 1 map
     */
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (String string : words1) {
            map.put(string, map.getOrDefault(string, 0) + 1);
        }
        map.entrySet().removeIf(entry -> entry.getValue() != 1);
        for (String s : words2) {
            if (map.containsKey(s) && map.get(s)==0) {
                count--;
                map.remove(s);
            }
            if (map.containsKey(s)) {
                count++;
                map.put(s, 0);
            }
        }

        return count;

        /*
        Đáp án Dùng 2 map

        public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> freq1 = new HashMap<>();
        Map<String, Integer> freq2 = new HashMap<>();

        for (String w : words1) {
            freq1.put(w, freq1.getOrDefault(w, 0) + 1);
        }
        for (String w : words2) {
            freq2.put(w, freq2.getOrDefault(w, 0) + 1);
        }

        int count = 0;
        for (Map.Entry<String, Integer> entry : freq1.entrySet()) {
            String word = entry.getKey();
            if (entry.getValue() == 1 && freq2.getOrDefault(word, 0) == 1) {
                count++;
            }
        }

        return count;
    }
         */
    }
}
