package letcode.le739;

import java.util.Stack;

/**
 * @Package letcode.le739
 * @author: xule
 * @date: 2020/8/11 17:13
 */
public class Solution {

    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int t = stack.peek();
                stack.pop();
                result[t] = i - t;
            }
            stack.push(i);
        }
        return result;



    }

}
