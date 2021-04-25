package letcode.addtwonumbers;

/**
 * @Package hot100.letcode.addtwonumbers
 * @author: xule
 * @date: 2020/7/31 16:25
 * 两数相加
 */
public class Solution {



    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode tempNode = node;
        int temp = 0;
        int sum;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                sum = l2.val + temp;
                temp = (int) Math.floor(sum / 10);
                if (temp != 0) {
                    tempNode.next = new ListNode(sum - temp * 10);
                    tempNode = tempNode.next;
                }else {
                    tempNode.next = new ListNode(sum);
                    tempNode = tempNode.next;
                    temp = 0;
                }
            }else if (l2 == null) {
                sum = l1.val + temp;
                temp = (int) Math.floor(sum / 10);
                if (temp != 0) {
                    tempNode.next = new ListNode(sum - temp * 10);
                    tempNode = tempNode.next;
                }else {
                    tempNode.next = new ListNode(sum);
                    tempNode = tempNode.next;
                    temp = 0;
                }
            } else {
                sum = l1.val + l2.val + temp;
                temp = (int) Math.floor(sum / 10);
                if (temp != 0) {
                    tempNode.next = new ListNode(sum - temp * 10);
                    tempNode = tempNode.next;
                }else {
                    tempNode.next = new ListNode(sum);
                    tempNode = tempNode.next;
                    temp = 0;
                }
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (temp != 0) {
            tempNode.next = new ListNode(temp);
        }
        return node.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        ListNode node = addTwoNumbers(l1, l2);
        ListNode a = node;

        while (a != null) {
            System.out.println(a.val + "---");
            a = a.next;
        }
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}


