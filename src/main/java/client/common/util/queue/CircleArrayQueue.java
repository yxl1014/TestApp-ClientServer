package client.common.util.queue;

import pto.TestProto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 一个基于HashMap实现的循环覆盖队列
 * @author yxl
 * @date 2022/10/31 下午4:18
 */
public class CircleArrayQueue<T> {

    private int capacity;

    private int size = 0;

    private final HashMap<Integer, T> map = new HashMap<>();

    private final int[] rmIndex;

    private int index = 0;

    private int rmHead = 0;

    public CircleArrayQueue(int capacity) {
        this.capacity = capacity;
        this.rmIndex = new int[capacity];
    }


    /**
     * 向这个队列中添加节点
     * @param k key
     * @param v value
     */
    public void add(int k, T v) {
        //如果这个任务存在，则直接更新数据
        if (this.map.containsKey(k)) {
            this.map.put(k, v);
            return;
        }
        //如果这个队列还没有满
        if (this.size != this.capacity) {
            //并且将任务id存起来
            this.rmIndex[this.index++] = k;
            //则直接往map中添加
            this.map.put(k, v);
            //并且大小加一
            this.size++;
        } else {
            //如果满了
            //则先获取需要删除（覆盖）的任务
            int rm = this.rmIndex[this.rmHead];
            //将这个任务从map中删除
            this.map.remove(rm);
            //将当前任务id换成新的id，并指向下一个任务
            this.rmIndex[this.rmHead++] = k;
            //指针不溢出
            if (this.rmHead == this.capacity) {
                this.rmHead = 0;
            }
            //将这个任务添加进去
            this.map.put(k, v);
        }
    }


    /**
     * 线程安全的向这个队列中添加节点
     * @param k key
     * @param v value
     */
    public void addSync(int k,T v){
        synchronized (this.rmIndex){
            add(k,v);
        }
    }

    public T get(int k) {
        return this.map.get(k);
    }

}
