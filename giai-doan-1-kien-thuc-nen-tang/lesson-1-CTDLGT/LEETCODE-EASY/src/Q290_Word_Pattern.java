import java.util.HashMap;
import java.util.Map;

public class Q290_Word_Pattern {
    public static void main(String[] args) {

    }

    public boolean wordPattern(String pattern, String s) {

        Map<Character, String> map = new HashMap<>();
        String[] strings = s.split(" ");
        if (pattern.length() != strings.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(strings[i])) {
                    return false;
                }
            } else {
                map.put(pattern.charAt(i),strings[i]);
            }
        }
        String k = "";
        int co = 0;
        
        for (Map.Entry<Character, String> entry : map.entrySet()) {
            if (co==0) {
                k = entry.getValue();
                co=1;
            } else if (k.equals(entry.getValue())){
                return false;
            }
            
        }
        return true;
    }
}
