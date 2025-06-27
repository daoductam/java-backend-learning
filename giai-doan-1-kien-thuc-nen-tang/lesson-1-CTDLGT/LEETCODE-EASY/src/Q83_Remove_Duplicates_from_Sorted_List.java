import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q83_Remove_Duplicates_from_Sorted_List {
    /*
Given the head of a sorted linked list, delete all duplicates such that each element
appears only once. Return the linked list sorted as well.
Example 1:
Input: head = [1,1,2]
Output: [1,2]
Example 2:
Input: head = [1,1,2,3,3]
Output: [1,2,3]
     */
    public static void main(String[] args) {
//        //ý tưởng có thể xài set cho nhanh nhưng đề bài yêu cầu xài single linked list
//        Integer[] head = {1,1,2,3,3};
//        Set<Integer> set = new HashSet<>(Arrays.asList(head));
//        System.out.println(set);
        
    }


     public static class ListNode {
          int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

      /*
      Ý tương: Time O(n) Space O(1)
      Sử dụng con trỏ hientai để duyệt qua từng node trong danh sách.
    Nếu hientai.val == hientai.next.val, tức là có phần tử trùng lặp:
    Bỏ qua node tiếp theo bằng cách nối hientai.next = hientai.next.next
    Nếu không trùng, di chuyển con trỏ hientai đến node kế tiếp.
    Lặp cho đến khi hientai hoặc hientai.next là null.
    Trả về head (không phải hientai)
       */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode hientai = head;
        while (hientai != null && hientai.next != null) {
            if (hientai.val == hientai.next.val) {
                hientai.next = hientai.next.next;
            } else {
                hientai = hientai.next;
            }
        }
        return head;
    }
}
