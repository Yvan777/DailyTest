package thread;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/5/5 17:04
 */
public class Thread_cust_prod {
    private static int count = 0;
    private int maxCount = 5;
    ReentrantLock lock =  new ReentrantLock();
    Condition prodCondition = lock.newCondition();
    Condition custCondition = lock.newCondition();

    public static void main(String[] args) throws IOException {
        Thread_cust_prod thread_cust_prod = new Thread_cust_prod();
        new Thread(thread_cust_prod.new Produce(),"生1").start();
        new Thread(thread_cust_prod.new Produce(),"生2").start();
        new Thread(thread_cust_prod.new Customer(),"消1").start();
        new Thread(thread_cust_prod.new Customer(),"消2").start();
    }

    class Produce implements Runnable{
        @Override
        public void run() {
            for (int i=0; i<10; i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count>=maxCount){
                        System.out.println("满了");
                        prodCondition.await();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName()+"生产,目前共有"+count);
                    custCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }
    class Customer implements Runnable{
        @Override
        public void run() {
            for (int i=0; i<10; i++){
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count<=0){
                        custCondition.await();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName()+"消费,目前还剩"+count);
                    prodCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }
}
