package swordpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package swordpoint
 * @author: xule
 * @date: 2021/4/13 下午3:08
 */
public class S57 {
    public int[][] findContinuousSequence(int target) {
        int i = 1;
        int j = 1;
        int sum = 0;
        List<int[]> res = new ArrayList<>();
        while (i <= target / 2) {
            if (sum < target) {
                // 右指针右移
                sum += j;
                j++;
            } else if (sum > target) {
                // 左指针右移
                sum -= i;
                i++;
            } else {
                // 说明相等 处理数据
                int[] temp = new int[j - i];
                for (int k = i; k < j; k++) {
                    temp[k - i] = k;
                }
                res.add(temp);
                // 左指针右移
                sum -= i;
                i++;

            }
        }
        return res.toArray(new int[res.size()][]);

    }

    private int sum(int i, int j) {
        int a = 0;
        for (int k = i; k <= j; k++) {
            a += k;
        }
        return a;
    }
}
