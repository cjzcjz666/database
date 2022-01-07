package Server.ThreadPool;

import Server.ServerMgr;
import Server.Task;

public class AddNewTask implements Runnable {

    /**
     * 添加新任务
     */
    public void run() {
        ThreadPool tp = ServerMgr.threadPool;

        while (true) {
            ServerMgr smg = new ServerMgr();

            /* 线程池当前线程数小于最大线程数，可向线程执行数组中加入新任务 */
            if (tp.getExecutingAmount() < tp.getMaxSize()) {

                /* 等待队列不为空时，先从等待队列中取出任务交给线程执行数组 */
                if (tp.getWaitingAmount() != 0) {

                    int counter = 0;
                    while (counter < 50) { // counter < 50 防止并发可能导致的数组越界问题

                        if (tp.poolThreads[counter] == null) {
                            // 为新任务创建线程，将该线程移交给线程执行数组
                            Task task = tp.waitingQueue.poll();
                            tp.poolThreads[counter] = new TaskThread(task, counter);
                            // 线程池等待任务数减少 1
                            tp.reduceWaitingAmount();
                            // 线程池当前线程数增加 1
                            tp.addCurrentAmount();
                            break;
                        } else counter++;

                    }
                }

                // 等待队列为空，而 ServerMgr.taskList 不为空，则将新任务直接移交给线程执行数组
                else if (smg.taskIterator.hasNext()) {

                    int counter = 0;
                    while (counter < 50) { // 防止并发带来的潜在错误

                        if (tp.poolThreads[counter] == null) {
                            // 为新任务创建线程，将该线程移交给线程执行数组
                            Task temp = smg.taskIterator.next();
                            tp.poolThreads[counter] = new TaskThread(temp, counter);
                            ServerMgr.taskList.remove();

                            // 线程池当前线程数自增
                            tp.addCurrentAmount();
                            break;
                        } else counter++;
                    }
                }
            }

            // 线程池当前线程已满，让新任务进入等待队列
            else {
                if (smg.taskIterator.hasNext() && tp.getWaitingAmount() < tp.getWaitingMax()) {
                    try {
                        tp.waitingQueue.put(smg.taskIterator.next());
                        tp.addWaitingAmount();
                        ServerMgr.taskList.remove();
                        for (int i = 0; i < 1000; i++) {
                            System.out.println("-------成功加入新任务------");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("池中无可用线程，且等待队列已满");
                }
            }
        }
    }
}
