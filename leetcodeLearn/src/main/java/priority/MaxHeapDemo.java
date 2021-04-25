package priority;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Package priority
 * @author: xule
 * @date: 2020/8/17 16:25
 */
public class MaxHeapDemo {

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap<>(Arrays.asList(5, 2, 1, 10));

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.popMax() + ", ");
        }
        System.out.println("----------");

        maxHeap.add(5);
        maxHeap.add(19);
        maxHeap.add(28);
        maxHeap.add(7);
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.popMax() + ", ");
        }
        System.out.println("----------");
        // 默认为最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(5);
        pq.add(2);
        pq.add(1);
        pq.add(10);
        pq.add(3);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll() + ", ");
        }
        System.out.println();
        System.out.println("————————————————————————");
// 使用Lambda表达式传入自己的比较器转换成最大堆
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> b - a);
        pq2.add(5);
        pq2.add(2);
        pq2.add(1);
        pq2.add(10);
        pq2.add(3);
        while (!pq2.isEmpty()) {
            System.out.println(pq2.poll() + ", ");
        }
    }
}
