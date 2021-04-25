package letcode;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/2/8 14:38
 */
public class Le189 {

    public void rotate(int[] nums, int k) {
        int[] temp = new int[nums.length];
        int lastIndex = nums.length - 1;
        for (int i = lastIndex; i >= 0; i--) {
            if (lastIndex - i <= k) {
                temp[k - 1 - lastIndex + i] = nums[i];
                nums[i] = nums[i - k];
            } else {
                nums[i] = temp[i];
            }
        }
    }
}
