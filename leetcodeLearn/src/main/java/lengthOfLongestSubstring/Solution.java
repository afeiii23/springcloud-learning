package lengthOfLongestSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package PACKAGE_NAME
 * @author: xule
 * @date: 2020/8/3 11:50
 * 无重复字符的最长子串 "pwwkew"
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int max = 0; // 最大子串长度
        int left = 0; //左侧滑动窗口开始位置

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // 判断当前值是否已经存在 存在的话 取当前窗口左侧值及前一个当前值（get(s.charAt)）的后一位两者的最大值为左侧窗口位置 避免"abba"取值不在窗口内
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("235".charAt(2));
    }
}
