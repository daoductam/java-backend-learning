public class Q2496_Maximum_Value_of_a_String_in_an_Array {
    public int maximumValue(String[] strs) {
        int max = 0;
        for (String s : strs) {
            int value;
            if(s.matches("\\d+")) {
                value = Integer.parseInt(s);
            } else {
                value = s.length();
            }
            max = Math.max(max, value);
        }

        return max;
    }
}
