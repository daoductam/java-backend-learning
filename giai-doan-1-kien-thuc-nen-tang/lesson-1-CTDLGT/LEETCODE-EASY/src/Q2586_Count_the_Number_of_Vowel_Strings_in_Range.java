public class Q2586_Count_the_Number_of_Vowel_Strings_in_Range {
    public static void main(String[] args) {

    }

    public int vowelStrings(String[] words, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            String w = words[i];
            if (isVowel(w.charAt(0)) && isVowel(w.charAt(w.length() - 1))) {
                count++;
            }
        }
        return count;
    }

    private boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
