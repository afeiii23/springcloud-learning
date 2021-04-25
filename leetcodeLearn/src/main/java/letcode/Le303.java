package letcode;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/2 09:05
 */
public class Le303 {
}
class NumArray {

    private int[] sum;

    public NumArray(int[] nums) {
        if (nums.length <= 0) {
            return;
        }
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i > sum.length || j > sum.length) {
            return 0;
        }
        if (i == 0) {
            return sum[j];
        }else {
            return sum[j] - sum[i-1];
        }

    }
}