package letcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/9 09:20
 */
public class Test {
//    boolean[][] f;
//    List<List<String>> ret = new ArrayList<List<String>>();
//    List<String> ans = new ArrayList<String>();
//    int n;
//
//    public List<List<String>> partition(String s) {
//        n = s.length();
//        f = new boolean[n][n];
//        for (int i = 0; i < n; ++i) {
//            Arrays.fill(f[i], true);
//        }
//
//        for (int i = n - 1; i >= 0; --i) {
//            for (int j = i + 1; j < n; ++j) {
//                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
//            }
//        }
//
//        dfs(s, 0);
//        return ret;
//    }
//
//    public void dfs(String s, int i) {
//        if (i == n) {
//            ret.add(new ArrayList<String>(ans));
//            return;
//        }
//        for (int j = i; j < n; ++j) {
//            if (f[i][j]) {
//                ans.add(s.substring(i, j + 1));
//                dfs(s, j + 1);
//                ans.remove(ans.size() - 1);
//            }
//        }
//    }
    public static volatile AtomicInteger a = new AtomicInteger(0);
    public static void main(String[] args) {
//        TreeMap<Integer,Integer> map = new TreeMap<>();
//        map.put(2,2);
//        map.put(3,3);
//        map.put(3,5);
//        map.put(4,4);

//        for (int i = 0; i < 50000; i++) {
//            new Thread(() -> System.out.println(a.getAndIncrement())).start();
//        }
        System.out.println(a);
        System.out.println(a);
//        Socket


//        Executors.newSingleThreadExecutor()

    }

}
