package Server;

public class Task {

    // 任务编号
    private int taskNo;

    // 当前任务实例携带的字符串信息
    private String info;

    // 当前任务将要打印的行的数量
    private int sleepTime;

    /**
     * 接收一个客户端请求，将该请求转换为 Task 对象
     * 并为该对象赋予需要打印的信息 info 和将要打印的行数 linesAmount
     *
     * @param request 套接字对象
     */
    Task(String request, int taskNo) {

        this.taskNo = taskNo;

        /* 处理请求，储存为 String 型的属性 */
        info = "Request: " + request;

        /* 随机生成 1001 - 9999 之间的一个数，作为随机睡眠时间 */
        while (true) {
            int num = (int) (Math.random() * 10000);
            if (num > 1000 && num < 10000) {
                this.sleepTime = num;
                break;
            }
        }

    }

    public String getInfo() {
        return info;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public int getTaskNo() {
        return taskNo;
    }
}
