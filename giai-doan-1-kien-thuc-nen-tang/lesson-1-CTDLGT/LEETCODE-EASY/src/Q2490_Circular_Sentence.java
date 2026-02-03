public class Q2490_Circular_Sentence {
    public static void main(String[] args) {
        System.out.println("RS: "+isCircularSentence("Leetcode eisc cool"));
    }

    public static boolean isCircularSentence(String sentence) {
        String[] arr = sentence.split(" ");
        if (arr[0].charAt(0)!=arr[arr.length-1].charAt(arr[arr.length-1].length()-1)) {

            return false;
        }
        for (int i = 0; i < arr.length-1; i++) {
           if (arr[i].charAt(arr[i].length() -1) != arr[i+1].charAt(0)) {
                return false;
           }
        }

        return true;
    }
}
