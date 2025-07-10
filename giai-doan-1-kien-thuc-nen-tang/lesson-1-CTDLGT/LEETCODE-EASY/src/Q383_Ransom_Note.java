import java.util.HashMap;
import java.util.Map;

public class Q383_Ransom_Note {
    /*
    Given two strings ransomNote and magazine, return true if ransomNote
     can be constructed by using the letters from magazine and false otherwise.
    Each letter in magazine can only be used once in ransomNote.
    Example 1:
    Input: ransomNote = "a", magazine = "b"
    Output: false
    Example 2:
    Input: ransomNote = "aa", magazine = "ab"
    Output: false
    Example 3:
    Input: ransomNote = "aa", magazine = "aab"
    Output: true
     */
    public static void main(String[] args) {
        System.out.println(canConstruct("aa","aab"));
    }
    /*
    Y tuong Time O(n + m) Space O(n)
    Đếm số lần xuất hiện mỗi ký tự trong ransomNote → lưu vào map.
    Duyệt magazine và "gỡ bỏ" từng ký tự khỏi map nếu tìm thấy.
    Nếu cuối cùng map rỗng → nghĩa là tất cả các ký tự trong ransomNote đều tìm được trong magazine
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : ransomNote.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }

        for (int i = 0; i < magazine.length(); i++) {
            char a = magazine.charAt(i);
            if (map.containsKey(a)) {
                map.put(a, map.get(a) - 1);
                if (map.get(a) == 0) {
                    map.remove(a);
                }
            }
        }
        return map.isEmpty();
    }

    /*
    Đáp án Chỉ hoạt động với chữ cái tiếng Anh thường (a-z). O(n + m) Space O(1)
    Tạo 2 mảng đếm kích thước 26 (chữ cái a-z).
    Đếm số lần xuất hiện từng ký tự trong cả 2 chuỗi.
    So sánh: nếu tại vị trí nào mà ransomNote cần nhiều hơn magazine có → false.
     */
//    public boolean canConstruct(String ransomNote, String magazine) {
//        int[] ransomNoteMap = new int[26];
//        int[] magazineNote = new int[26];
//        for(char s : ransomNote.toCharArray()){
//            ransomNoteMap[s - 'a']++;
//        }
//
//        for (char s : magazine.toCharArray()){
//            magazineNote[ s - 'a']++;
//        }
//
//        for(int i =0 ; i < ransomNoteMap.length ; i++){
//            if( ransomNoteMap[i] != 0 ){
//                if(magazineNote[i] == 0 ||  ransomNoteMap[i] > magazineNote[i]){
//                    return false;
//                }
//
//            }
//        }
//        return true;
//
//    }

}
