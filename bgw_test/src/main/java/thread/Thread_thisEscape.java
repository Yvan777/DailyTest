package thread;

import java.io.IOException;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/5/7 16:37
 */
public class Thread_thisEscape {
    private int a = 8;
    public Thread_thisEscape(){
        new Thread(
                ()-> System.out.println(this.a)
        ).start();
    }

    public static void main(String[] args) throws IOException {
//        new Thread_thisEscape();
        new t1().run();
        new t2().run();
    }

    static class t1 extends Thread{

    }

    static class t2 implements Runnable{
        @Override
        public void run() {
            System.out.println("2");
        }
    }
}
