package Server.ThreadPool;

import Server.Task;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    // 线程池的最大容量
    private int maxSize;

    // 线程池当前正在执行的线程数目
    private int executingAmount;

    // 线程池等待队列中的任务数目
    private int waitingAmount;

    // 等待队列的最大容量
    private int waitingMax;

    // 指定线程数的线程对象数组
    TaskThread[] poolThreads;

    // 超出最大数量时的等待队列
    LinkedBlockingQueue<Task> waitingQueue;

    public ThreadPool(int maxSize) {
        this.maxSize = maxSize;
        executingAmount = 0;
        waitingAmount = 0;
        poolThreads = new TaskThread[maxSize];
        waitingQueue = new LinkedBlockingQueue<>();
        waitingMax = 200;

        for (int i = 0; i < maxSize; i++) {
            poolThreads[i] = null;
        }

    }

    // 获取线程池的最大容量
    public int getMaxSize() {
        return maxSize;
    }

    //获取线程池的当前线程数
    int getExecutingAmount() {
        return executingAmount;
    }

    // 获取等待队列的任务数目
    int getWaitingAmount() {
        return waitingAmount;
    }

    // 获取等待队列的任务数目
    int getWaitingMax() {
        return waitingMax;
    }

    // 当前线程数增加 1
    void addCurrentAmount() {
        executingAmount++;
    }

    // 当前线程数减少 1
    void reduceCurrentAmount() {
        executingAmount--;
    }

    // 等待任务数增加 1
    void addWaitingAmount() {
        waitingAmount++;
    }

    // 等待任务减少 1
    void reduceWaitingAmount() {
        waitingAmount--;
    }
}
