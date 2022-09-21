package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/25 11:37 上午
 */
class Thread_lockSupport {
    //LockSupport
    static Thread t1 , t2;
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("1","2","3");
        List<String> list2 = Arrays.asList("a","b","c");
        t1 = new Thread(()->{
            list1.forEach(o ->
                    {
                        System.out.println(o);
                        LockSupport.unpark(t2);//唤醒t2
                        LockSupport.park();//t1阻塞
                    }
            );
        },"t1");
        t2 = new Thread(()->{
                    list2.forEach(o ->
                        {
                            LockSupport.park();//t2阻塞,直接让行,让其他线程先执行
                            System.out.println(o);
                            LockSupport.unpark(t1);//唤醒t1
                        }
                    );
        },"t2");
        t1.start();
        t2.start();
    }
}

class Thread_reentLock {
    //ReentrantLock  不能保证执行顺序
    static Thread t1 , t2;
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("1","2","3");
        List<String> list2 = Arrays.asList("a","b","c");
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        t1 = new Thread(()->{
            lock.lock();//相当于synchorized
            try {
                for (String o : list1) {
                    System.out.println(o);
                    condition.signal();//相当于notify
                    condition.await();//wait
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1");
        t2 = new Thread(()->{
            lock.lock();
            try {
                for (String o : list2) {
                    System.out.println(o);
                    condition.signal();
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}

class Thread_transferQueue {
    //使用特殊同步对列
    static Thread t1 , t2;
    public static void main(String[] args) throws InterruptedException {
        char[] chars1 = "123456".toCharArray();
        char[] chars2 = "abcdef".toCharArray();
        //这是一个容量为0的同步队列,一定要等线程take(),才能重新transfer(新的数据)
        //类似于一进一出
        //LinkedTransferQueue是TransferQueue的实现类
        LinkedTransferQueue<Character> queue = new LinkedTransferQueue<>();

//        //会响应中断，会一直阻塞直到取得元素或当前线程中断
//        System.out.println(queue.take());
//        //会响应中断，会阻塞，阻塞时间参照方法里参数timeout.timeUnit，当阻塞时间到了还没取得元素会返回null
//        System.out.println(queue.poll());

        t1 = new Thread(()->{
            try {
                for (char o : chars1) {
                    queue.transfer(o);
                    System.out.println("t1=="+queue.take());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1");
        t2 = new Thread(()->{
            try {
                for (char o : chars2) {
                    System.out.println("t2=="+queue.take());
                    queue.transfer(o);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}

class Thread_CAS {
    //使用CAS自旋
    static Thread t1 , t2;
    enum MONITOR{MONITOR1,MONITOR2};
    static volatile MONITOR m = MONITOR.MONITOR1;
    public static void main(String[] args) throws InterruptedException {
        char[] chars1 = "123456".toCharArray();
        char[] chars2 = "abcdef".toCharArray();
        t1 = new Thread(()->{
                for (char o : chars1) {
                    // while循环空实现，进行自旋
                    while (m != MONITOR.MONITOR1){}
                    System.out.println(o);
                    m = MONITOR.MONITOR2;
                }
        },"t1");
        t2 = new Thread(()->{
            try {
                for (char o : chars2) {
                    while (m != MONITOR.MONITOR2){}
                    System.out.println(o);
                    m = MONITOR.MONITOR1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}



class Thread_basic {
    //经典写法
    //经典写法基础 无法确保线程t1 t2哪个先执行
    static Thread t1 , t2;
    final static Object lock = new Object();
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("1","2","3");
        List<String> list2 = Arrays.asList("a","b","c");
        t1 = new Thread(()->{
            synchronized (lock){
                list1.forEach(o ->
                    {
                        System.out.println(o);
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                );
                lock.notify();//必须写,否则无法停止程序
            }

        },"t1");
        t2 = new Thread(()->{
            synchronized (lock){
                list2.forEach(o ->
                    {
                        System.out.println(o);
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                );
                lock.notify();
            }

        },"t2");
        t1.start();
        t2.start();
        //获取线程名字的方法
        //System.out.println(t1.getName());
    }
}

class Thread_basic_countDownLatch {
    //经典写法基础 无法确保线程t1 t2哪个先执行
    static Thread t1 , t2;
    final static Object lock = new Object();
    //加一个门栓 可保证顺序
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        List<String> list2 = Arrays.asList("1","2","3");
        List<String> list1 = Arrays.asList("a","b","c");
        t1 = new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){
                list1.forEach(o ->
                    {
                        System.out.println(o);
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                );
                lock.notify();
            }

        },"t1");
        t2 = new Thread(()->{
            synchronized (lock){
                list2.forEach(o ->
                    {
                        System.out.println(o);
                        countDownLatch.countDown();
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                );
                lock.notify();
            }

        },"t2");
        t1.start();
        t2.start();
    }
}
class Thread_volatile {
    //经典写法基础 无法确保线程t1 t2哪个先执行
    static Thread t1 , t2;
    final static Object lock = new Object();
    //volatile修饰变量 可保证顺序
    private static volatile boolean isFirst = false;
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("a","b","c");
        List<String> list2 = Arrays.asList("1","2","3");
        t1 = new Thread(()->{
            while (true) {
                if (isFirst) {
                    synchronized (lock) {
                        list1.forEach(o ->
                                {
                                    System.out.println(o);
                                    try {
                                        lock.notify();
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );
                        lock.notify();
                    }
                    return;
                }
            }
        },"t1");
        t2 = new Thread(()->{
            synchronized (lock){
                list2.forEach(o ->
                    {
                        System.out.println(o);
                        isFirst = true;
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                );
                lock.notify();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}

class Thread_join {
    //join()方法保证线程执行顺序
    static Thread t1 , t2 , t3;
    public static void main(String[] args) {
        t1= new Thread(
                ()->System.out.println("线程1")
        );
        t2= new Thread(
                ()->
                {
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程2");
                }
        );
        t3= new Thread(
                ()->
                {
                    try {
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程3");
                }
        );
        t1.start();
        t3.start();
        t2.start();
    }
}

class Thread_newSingleThread {
    //Executors.newSingleThreadExecutor()
    static Thread t1 , t2 , t3;
    public static void main(String[] args) {
        t1= new Thread(
                ()->System.out.println("线程1")
        );
        t2= new Thread(
                ()->System.out.println("线程2")
        );
        t3= new Thread(
                ()->System.out.println("线程3")
        );
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3);
        executorService.shutdown();
    }
}