package Server.ThreadPool;

import Server.ServerMgr;

public class ExecuteCurrentTask implements Runnable {
    // 运行线程池
    public void run() {
        ThreadPool tp = ServerMgr.threadPool;
        while (true) {
            // 检查线程数组的每个元素，不为空时，启动该元素的线程对象，并将其从数组中取出
            for (int counter = 0; counter < tp.getMaxSize(); counter++) {
                if (tp.poolThreads[counter] != null && !tp.poolThreads[counter].whetherStart) {
                    tp.poolThreads[counter].whetherStart = true;
                    tp.poolThreads[counter].start();
                }
                if (tp.poolThreads[counter] != null && tp.poolThreads[counter].whetherComplete) {
                    tp.poolThreads[counter] = null;
                    tp.reduceCurrentAmount();
                }
            }
        }
    }
}
