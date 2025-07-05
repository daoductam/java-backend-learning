public class Q234_Palindrome_Linked_List {
    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(2,
                                new ListNode(1))));
        System.out.println(isPalindrome(head));
    }
    /*
    Ý tưởng: tham khảo Space O(1) TimeO(n)
    - Tìm điểm giữa của danh sách bằng hai con trỏ (slow, fast).
    - Đảo ngược nửa sau của danh sách.
    - So sánh nửa đầu và nửa sau.
    - Nếu giống nhau ⇒ ✅ là palindrome.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    static boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;

        // Tìm giữa
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Đảo ngược nửa sau
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // So sánh
        ListNode left = head, right = prev;
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }
}
