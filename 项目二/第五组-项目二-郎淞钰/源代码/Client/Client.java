package Client;

class Client {

    // 客户端对象携带的随机大写字母
    private char letter;

    // 客户端编号
    private int clientNo;

    /**
     * 指定客户端编号，为其产生随机大写字母
     *
     * @param clientNo 客户端编号
     */
    Client(int clientNo) {
        this.clientNo = clientNo;
        letter = setLetter();
    }

    /**
     * 返回该客户端的字母
     *
     * @return 大写字母
     */
    char getLetter() {
        return letter;
    }

    /**
     * 返回该客户端的编号
     *
     * @return 客户端编号
     */
    int getClientNo() {
        return clientNo;
    }

    /**
     * 生成一个随机的大小字母
     *
     * @return 大写字母
     */
    private char setLetter() {

        char letter;

        /* 生成随机数，通过ASCII码转化为大写字母 */
        while (true) {
            int number = (int) (Math.random() * 100);
            if (number >= 65 && number <= 90) {
                letter = (char) number;
                break;
            }
        }

        return letter;
    }

}