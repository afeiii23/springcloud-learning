package letcode;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/2/8 14:20
 */
public class Le122 {

    public int maxProfit(int[] prices) {
        int[] maxMoney = new int[prices.length];
        maxMoney[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i -1]) {
                maxMoney[i] = prices[i] - prices[i -1] + maxMoney[i-1];
            }else {
                maxMoney[i] = maxMoney[i-1];
            }
        }
        return maxMoney[prices.length - 1];
    }
}
