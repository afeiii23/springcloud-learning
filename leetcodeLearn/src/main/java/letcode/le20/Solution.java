package letcode.le20;

import java.util.Stack;

/**
 * @Package letcode.le20
 * @author: xule
 * @date: 2020/8/10 14:05
 */
public class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char cChar: s.toCharArray()){
            if(cChar=='(') {
                stack.push(')');
            } else if(cChar=='[') {
                stack.push(']');
            } else if(cChar=='{') {
                stack.push('}');
            } else if(stack.isEmpty()||cChar!=stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
