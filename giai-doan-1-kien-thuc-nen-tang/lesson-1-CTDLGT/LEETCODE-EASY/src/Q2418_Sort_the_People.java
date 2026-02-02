import java.util.*;

public class Q2418_Sort_the_People {
    public static void main(String[] args) {

    }

    public String[] sortPeople(String[] names, int[] heights) {
        Map<Integer, String> map = new LinkedHashMap<>();
        for (int i = 0; i < heights.length; i++) {
            map.put(heights[i], names[i]);
        }
        String[] rs = new String[names.length];
        Arrays.sort(heights);
        for (int i = 0; i < heights.length; i++) {
            rs[i] = map.get(heights[heights.length-1-i]);
        }
        return rs;

    }
}
