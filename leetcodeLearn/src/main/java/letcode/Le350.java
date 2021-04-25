package letcode;

import java.util.Arrays;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/2/8 10:31
 */
public class Le350 {

    public int[] intersect(int[] nums1, int[] nums2) {

        int k = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                nums1[k] = nums1[i];
                i++;
                j++;
                k++;
            }
        }
        return Arrays.copyOf(nums1, k);
    }
}
