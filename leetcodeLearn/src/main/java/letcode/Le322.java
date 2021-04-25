package letcode;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回-1。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/13 10:45
 */
public class Le322 {

    /**
     * @param coins  能够使用的硬币面值
     * @param amount 需要凑出来的金额
     * @return
     */
    public static int coinChange(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if(i < coin) {
                    continue;
                }
                if (i == coin) {
                    dp[i] = 1;
                }
                int pre = dp[i - coin];
                if (pre <= 0) {
                    // 子集求解无结果
                    continue;
                }
                dp[i] = Math.min(dp[i], pre + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];

//        if (amount == 0) {

    }

    private int findDp(int[] coins, int n, int[] dp) {
        if (n == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }
        if (dp[n] > 0) {
            return dp[n];
        }
        // 先给一个初始值
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (n == coin) {
                return 1;
            }
            int preTimes = findDp(coins, n - coin, dp);
            if (preTimes == -1) {
                continue;
            }
            res = Math.min(res, preTimes + 1);
        }
        dp[n] = res == Integer.MAX_VALUE ? -1 : res;
        return dp[n];
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2,5,7};
//        int i = coinChange(ints, 7);
        int i = coinChange1(ints, 7);
        System.out.println(i);
    }

    /**
     * 你有三种硬币，2元、5元、7元，每种硬币足够多，买一本书需要27元，用最少的硬币组合
     * A硬币数组，M总金额 ， 返回硬币个数
     * @param A
     * @param M
     * @return
     */
    public  static  int coinChange1(int [] A, int M ) {
        int[] dp = new int[M + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= M; i++) {
            for (int coin : A) {
                if (i - coin < 0) {
                    continue;
                }
                int preCoin = dp[i - coin];
                if (preCoin == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i], preCoin + 1);
            }
        }
        return dp[M] == Integer.MAX_VALUE ? -1 : dp[M];
    }
}
