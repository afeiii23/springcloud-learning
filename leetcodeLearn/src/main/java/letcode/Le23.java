package letcode;

import swordpoint.ListNode;

import java.util.Arrays;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * @Package letcode
 * @author: xule
 * @date: 2021/4/29 上午10:48
 */
public class Le23 {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length <= 0) {
            return null;
        }
        // 哨兵指针
        ListNode sentinel = new ListNode(0);
        // 前驱指针
        ListNode preNode = sentinel;
        ListNode[] nextNodeList = Arrays.copyOf(lists, lists.length);
        boolean needBreak = false;
        while (!needBreak) {
            ListNode min = new ListNode(Integer.MAX_VALUE);
            int minIndex = 0;
            boolean allBreak = true;
            for (int i = 0; i < nextNodeList.length; i++) {
                allBreak = allBreak && (nextNodeList[i] == null);
                if (nextNodeList[i] == null) {
                    continue;
                }
                min = min.val < nextNodeList[i].val ? min : nextNodeList[i];
                minIndex = min.val < nextNodeList[i].val ? minIndex : i;
            }
            if (!allBreak) {
                preNode.next = min;
                nextNodeList[minIndex] = min.next;
                min.next = null;
                preNode = min;
            }

            needBreak = allBreak;
        }
        return sentinel.next;
    }


    public static void main(String[] args) {
        ListNode n13 = new ListNode(5);
        ListNode n12 = new ListNode(4);
        n12.next = n13;
        ListNode n11 = new ListNode(1);
        n11.next = n12;

        ListNode n23 = new ListNode(4);
        ListNode n22 = new ListNode(3);
        n22.next = n23;
        ListNode n21 = new ListNode(1);
        n21.next = n22;
        ListNode n32 = new ListNode(6);
        ListNode n31 = new ListNode(2);
        n31.next = n32;

        ListNode listNode = mergeKLists(Arrays.asList(n11, n21, n31).toArray(new ListNode[3]));
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;

        }
    }

}
