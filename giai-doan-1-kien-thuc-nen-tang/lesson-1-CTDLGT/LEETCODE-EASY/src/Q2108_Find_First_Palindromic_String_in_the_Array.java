public class Q2108_Find_First_Palindromic_String_in_the_Array {
    public static void main(String[] args) {

    }

    public String firstPalindrome(String[] words) {
        boolean rs = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length()==1) {
                return words[i];
            }
            for (int j = 0; j < words[i].length()/2; j++) {
                if (words[i].charAt(j) != words[i].charAt(words[i].length()-1-j)) {
                    rs = false;
                    break;
                }
                rs=true;

            }
            if (rs==true) {
               return words[i];
            }

        }
        return "";
    }

    /*
    Đáp án:

    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }
        return "";
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
     */
}
