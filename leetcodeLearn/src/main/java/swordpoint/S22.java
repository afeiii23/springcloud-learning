package swordpoint;

/**
 * @Package swordpoint
 * @author: xule
 * @date: 2021/4/13 下午2:32
 */
public class S22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
//        int i = 0,j = 0;
//        ListNode res = head;
//        while (head != null) {
//            if (j - i > k - 1) {
//                i++;
//                res = res.next;
//            }
//            j++;
//            head = head.next;
//        }
//        return j - i > k -1 ? res : null;

        ListNode slow  = head;
        ListNode fast  = head;
        int i = 0;
        while (fast != null) {
            if (i >= k) {
                slow = slow.next;
            }
            i++;
            fast = fast.next;
        }
        return slow;
    }
}
