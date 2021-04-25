package swordpoint;

/**
 * @Package com.curry.warrior.swordpoint
 * @author: xule
 * @date: 2021/3/23 17:47
 */
public class S04 {

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            int temp = matrix[i][j];
            if (temp == target) {
                return true;
            }else if (temp > target) {
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {{5},{6}};
        System.out.println(findNumberIn2DArray(a, 6));
    }
}
