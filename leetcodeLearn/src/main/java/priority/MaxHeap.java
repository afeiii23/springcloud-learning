package priority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Package priority
 * @author: xule
 * @date: 2020/8/17 15:35
 */
public class MaxHeap<E extends Comparable<E>> {
    private List<E> array;
    public MaxHeap(int capacity) {
        array = new ArrayList<>(capacity);
    }
    public MaxHeap() {
        array = new ArrayList<>();
    }

    public MaxHeap(Collection<E> arr){
        array = (List<E>) arr;
        // 从最后一个非叶子结点开始进行Sift Down操作
        for (int i = parent(arr.size() - 1); i >= 0 ; i--) {
            siftDown(i);
        }
    }

    public Boolean isEmpty(){
        return array.isEmpty();
    }

    /**
     * 查找父节点
     * @param index
     * @return
     */
    public int parent(int index) {
        if (index == 0) {
            return -1;
        }
        return (index-1) / 2;
    }

    public int length(){
        return array.size();
    }

    /**
     * 左孩子
     * @param index
     * @return
     */
    public int leftChild(int index) {
        return 2 * index +1;
    }

    /**
     * 右孩子
     * @param index
     * @return
     */
    public int rightChild(int index) {
        return 2 * index +2;
    }

    /**
     * 获取最大的元素
     * @return
     */
    public E findMax(){
        if (array.size() == 0) {
            throw new IllegalArgumentException("cant find the max in empty heap");
        }
        return array.get(0);
    }

    /**
     * 获得最大元素并移除
     * @return
     */
    public E popMax(){
        if (array.size() == 0) {
            throw new IllegalArgumentException("cant find the max in empty heap");
        }
        E max = array.get(0);
        array.set(0,array.get(array.size() - 1));
        array = array.subList(0,array.size() - 1);
        siftDown(0);
        return max;
    }

    public E replace(E e) {
        E max = findMax();
        array.set(0, e);
        siftDown(0);
        return max;
    }



    public void add(E e) {
        ArrayList<E> list = new ArrayList<>(array);
        list.add(e);
        array = list;
        shiftUp(array.size() - 1);
//        if (array.size() > 0) {
//        }
    }

    /**
     * 上浮
     * @param index
     */
    public void shiftUp(int index) {
        while (parent(index) >= 0 && array.get(index).compareTo(array.get(parent(index))) > 0){
            swap(index,parent(index));
            index = parent(index);
        }
    }

    /**
     *下沉
     * @param index
     */
    public void siftDown(int index) {
        while (leftChild(index) < array.size()) {
            // 左儿子
            int j = leftChild(index);
            if (j + 1 < array.size() && array.get(j + 1).compareTo(array.get(j)) > 0) {
                // 此时j为右儿子
                j++;
            }
            if (array.get(index).compareTo(array.get(j)) > 0) {
                break;
            }
            swap(j,index);
            index = j;
        }
    }

    private void swap(int index, int change) {
        E temp = array.get(index);
        array.set(index,array.get(change));
        array.set(change,temp);
    }
}
