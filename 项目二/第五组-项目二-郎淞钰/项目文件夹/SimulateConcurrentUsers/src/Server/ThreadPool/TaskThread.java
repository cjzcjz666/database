package Server.ThreadPool;

import Server.ServerTest;
import Server.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaskThread extends Thread {

    private Task task;

    private int threadNo;

    private int sleepTime;

    private int executingTime;

    boolean whetherStart;

    boolean whetherComplete;

    TaskThread(Task task, int threadNo) {
        this.task = task;
        this.threadNo = threadNo;
        this.sleepTime = task.getSleepTime();
        this.executingTime = 0;
        this.whetherStart = false;
        this.whetherComplete = false;
    }

    public void run() {

        /* 记录运行前的系统时间 */
        int t1 = currentTime();

        /* 随机睡眠 */
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* 记录运行后的系统时间 */
        int t2 = currentTime();

        /* 计算并储存运行的时间 */
        executingTime = t2 - t1;

        whetherComplete = true;
    }


    /**
     * 获取当前时间，换算为秒
     *
     * @return 秒数
     */
    private int currentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String[] strArray = dateFormat.format(calendar.getTime()).split(":");

        int[] intArray = new int[3];
        for (int i = 0; i < strArray.length; i++)
            intArray[i] = Integer.parseInt(strArray[i]);

        return intArray[0] * 60 * 60 + intArray[1] * 60 + intArray[2];
    }
}
