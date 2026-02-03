public class Q2129_Capitalize_the_Title {
    public static void main(String[] args) {

    }

    public String capitalizeTitle(String title) {
        String[] strings = title.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() < 3) {
                strings[i] = strings[i].toLowerCase();
            } else {
                strings[i] =strings[i].substring(0,1).toUpperCase() + strings[i].substring(1).toLowerCase();

            }
        }
        return String.join(" ", strings);
    }

    /*

    Đáp án:

    public static String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (w.length() <= 2) {
                sb.append(w.toLowerCase());
            } else {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1).toLowerCase());
            }
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
     */
}
