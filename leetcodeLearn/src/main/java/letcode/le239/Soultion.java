package letcode.le239;

import java.util.LinkedList;

/**
 * @Package letcode.le239
 * @author: xule
 * @date: 2020/8/12 14:21
 */
public class Soultion {
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int[] result = new int[nums.length - k +1];
        for (int i = 0; i < nums.length; i++) {
            while (!list.isEmpty() && nums[list.peekLast()] < nums[i]) {
                // 出队列
                list.pollLast();
            }
            list.addLast(i);
            if (list.peek() <= i - k) {
                // 当前最大值是在窗口外的话需要出队列
                list.poll();
            }
            if (i - k + 1 >= 0) {
                // 已经到了窗口期 开始保存
                result[i - k + 1] = nums[list.peek()];
            }

        }
        return result;

    }
}
