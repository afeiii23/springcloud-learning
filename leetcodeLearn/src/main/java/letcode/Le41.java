package letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package letcode
 * @author: xule
 * @date: 2021/4/28 下午2:42
 */
public class Le41 {

    public int firstMissingPositive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (num > 0) {
                map.put(num, true);
                max = Math.max(num, max);
            }
            
        }

        for (int i = 1; i < max; i++) {
            if (map.get(i) == null) {
                return i;
            }
        }
        return max + 1;

    /*    for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums.length < nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;*/


    }
}
