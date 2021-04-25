package letcode;

/**
 * 一条包含字母A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A" ，从而得到 "AAA" ，或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。注意，"06" 不能映射为 "F" ，因为 "6" 和 "06" 不同。
 * <p>
 * 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/20 14:33
 */
public class Le91 {

    public static int numDecodings(String s) {
        if ("0".equals(s)) {
            return 0;
        }
        if (s.length() <= 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6'))) {
                dp[i + 1] = chars[i] == '0' ? dp[i - 1] : dp[i] + dp[i - 1];
            }else {
                dp[i + 1] = chars[i] == '0' ? 0 : dp[i];
            }

        }
        return dp[s.length()];



      /*  if("0".equals(s)) {
            return 0;
        }
        if(s.length() <= 1) {
            return 1;
        }
        char beforeTheLast = s.charAt(s.length() - 2);
        char last = s.charAt(s.length() - 1);
        if(beforeTheLast == '1' || (beforeTheLast == '2' && last <= '6')) {
            if (last == '0') {
                return numDecodings(s.substring(0, s.length() - 2));
            }
            return numDecodings(s.substring(0, s.length() - 1)) +
                    numDecodings(s.substring(0, s.length() - 2));
        }else {
            if (last == '0') {
                return 0;
            }
            return numDecodings(s.substring(0, s.length() - 1));
        }*/

    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
    }
}
