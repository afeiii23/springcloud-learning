package letcode;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/2/8 10:50
 */
public class Le14 {

    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        String baseStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String compareStr = strs[i];
            while (!compareStr.startsWith(baseStr)) {
                baseStr = baseStr.substring(0, baseStr.length() - 1);
                if (baseStr.length() == 0) {
                    return "";
                }
            }
        }
        return baseStr;

    }
}
