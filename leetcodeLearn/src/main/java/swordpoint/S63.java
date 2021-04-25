package com.curry.warrior.letcode.swordpoint;

/**
 * @Package com.curry.warrior.swordpoint
 * @author: xule
 * @date: 2021/3/30 20:24
 */
public class S63 {
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int i = 0, j = 0, max = 0;
        while (j < prices.length) {
            if (prices[i] > prices[j]) {
                i = j;
                j++;
                continue;
            }
            max = Math.max(max, prices[j] - prices[i]);
            j++;
        }
        return max;

//        if (prices.length < 2) {
//            return 0;
//        }
//        int cost = prices[0];
//        int[] dp = new int[prices.length];
//        dp[0] = 0;
//        for (int i = 1; i < prices.length; i++) {
//            dp[i] = Math.max(dp[i -1], prices[i] - cost);
//            cost = Math.min(cost, prices[i]);
//        }
//        return dp[prices.length - 1];
    }

    public static void main(String[] args) {
        int[] a = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(a));
    }
}
