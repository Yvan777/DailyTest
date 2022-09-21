package thread;

import java.util.concurrent.CompletableFuture;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/5/7 15:30
 */
public class Thread_CompletableFuture {
    public static void main(String[] args) {
        t1 t1 = new t1();
        t2 t2 = new t2();
        CompletableFuture f =
                //进行异步调用
                CompletableFuture.supplyAsync(()->t1.runT())
                        //拿到结果,对结果进行处理
                        .thenAccept((String a) -> callback(a,t1));
        CompletableFuture f2 =
                CompletableFuture.supplyAsync(()->t2.runT())
                        .thenAccept((String a) -> callback(a,t2));
    }
    static String callback(String a,Runnable r){
        if("suc".equals(a)){
            System.out.println("======"+a);
            return a;
        } else {
            System.out.println("==="+a);
            return "back";
        }
    }
    static class t1 implements Runnable{
        @Override
        public void run() {
            System.out.println("--------");
        }
        String runT(){
            return "suc";
        }
    }
    static class t2 implements Runnable{
        @Override
        public void run() {
            System.out.println("--------");
        }
        String runT(){
            return "fail";
        }
    }
}
