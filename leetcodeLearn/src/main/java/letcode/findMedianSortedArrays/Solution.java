package letcode.findMedianSortedArrays;


/**
 * @Package letcode.findMedianSortedArrays
 * @author: xule
 * @date: 2020/8/4 10:27
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1)/2;
        int right = (m + n + 2)/2;
        return (dichotomy(nums1,0,nums2,0,left) + dichotomy(nums1,0,nums2,0,right)) / 2.0;
    }

    /**
     *
     * @param nums1
     * @param i nums1起始位置
     * @param nums2
     * @param j nums2起始位置
     * @param k 每次二分法后指针移动的大小
     * @return
     */
    public int dichotomy(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) {
            // 此时说明num1被截取的数组为空数组
            return nums2[j + k - 1];
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            //
            return Math.min(nums1[i], nums2[j]);
        }
        // 如果num1的length大于移动i + k/2 -1后的长度  说明目前中位数还是在当前数组内的
        int midVal1 =(i + k/2 -1 < nums1.length) ? nums1[i + k/2 -1] : Integer.MAX_VALUE;
        // 如果num2的length大于移动j + k/2 -1后的长度  说明目前中位数还是在当前数组内的
        int midVal2 =(j + k/2 -1 < nums2.length) ? nums2[j + k/2 -1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            // 如果num1当前区间段的值小于后者 num1 右移
            return dichotomy(nums1,i + k/2, nums2, j, k - k/2);
        }else {
            // 否则num2 右移
            return dichotomy(nums1,i, nums2, j + k/2, k - k/2);
        }
    }

//    111
//    222

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode first = new ListNode(0);
        ListNode prev = null,curr = head,next = null;
        first.next = head;
        ListNode splitNode = head;
        int i = 0;
        while (splitNode != null && i < k) {
            i++;
            splitNode = splitNode.next;
        }
        int j = 0;
        if (i == k) {
            // 进行翻转
            while (curr != null &&  j < i) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                j++;
            }
            if (next != null) {
                head.next = reverseKGroup(next,k);
            }
            return prev;
        }else {
            return head;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        ListNode node = reverseKGroup(l1, 2);


        while (node != null) {
            System.out.println(node.val + "---");
            node = node.next;
        }
    }


      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }


}
