/**
 * @Package PACKAGE_NAME
 * @author: xule
 * @date: 2020/8/10 10:31
 * 父子线程数据传递
 */
//package me.zebe.cat.java.lambda;

/**
 * ThreadLocal的Lambda构造方式：withInitial
 *
 * @author Zebe
 */
class ThreadLocalLambdaDemo {

    /**
     * 运行入口
     *
     * @param args 运行参数
     */
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            safeDeposit();
        }

        //notSafeDeposit();
    }

    /**
     * 线程安全的存款
     */
    private static void safeDeposit() {
        SafeBank bank = new SafeBank();
        Thread thread1 = new Thread(() -> bank.deposit(200), "张**");
        Thread thread2 = new Thread(() -> bank.deposit(200), "马*");
        Thread thread3 = new Thread(() -> bank.deposit(500), "马**");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 非线程安全的存款
     */
    private static void notSafeDeposit() {
        NotSafeBank bank = new NotSafeBank();
        Thread thread1 = new Thread(() -> bank.deposit(200), "张**");
        Thread thread2 = new Thread(() -> bank.deposit(200), "马*");
        Thread thread3 = new Thread(() -> bank.deposit(500), "马**");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}

/**
 * 非线程安全的银行
 */
class NotSafeBank {

    /**
     * 当前余额
     */
    private int balance = 1000;

    /**
     * 存款
     *
     * @param money 存款金额
     */
    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " -> 当前账户余额为：" + this.balance);
        this.balance += money;
        System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 线程安全的银行
 */
class SafeBank {

    /**
     * 当前余额
     */
    private ThreadLocal<Integer> balance = ThreadLocal.withInitial(() -> 1000);

    /**
     * 存款
     *
     * @param money 存款金额
     */
    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " -> 当前账户余额为：" + this.balance.get());
        this.balance.set(this.balance.get() + money);
        System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance.get());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance.remove();
    }
}

