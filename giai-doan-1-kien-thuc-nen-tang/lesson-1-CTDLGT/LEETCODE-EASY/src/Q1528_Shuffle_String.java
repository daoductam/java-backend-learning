import java.util.Arrays;

public class Q1528_Shuffle_String {
    public static void main(String[] args) {
        System.out.println(restoreString("codeleet", new int[]{4,5,6,7,0,2,1,3}));;
    }

    /*
    Ý tưởng: O(n) O(n)
    - Tạo 1 mảng char[] result có cùng độ dài với s.
    - Duyệt qua từng ký tự của s:
       - Ký tự ở vị trí i trong s sẽ được đặt vào result[indices[i]].
    - Cuối cùng, chuyển result thành String.
     */

    public static String restoreString(String s, int[] indices) {
        char[] result = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            result[indices[i]] = s.charAt(i);
        }

        return new String(result) ;
    }

}
