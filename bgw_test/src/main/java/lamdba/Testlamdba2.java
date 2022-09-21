package lamdba;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/18 4:47 下午
 */
interface Flyable {
    void fly(String s);
}

public class Testlamdba2 {
    public static void main(String[] args) {
        //有参
        //1.匿名内部类
        useFlyable(new Flyable() {
            @Override
            public void fly(String s) {
                System.out.println(s);
                System.out.println("出去玩");
            }
        });

        //2.lambda
        useFlyable((String s)->{
            System.out.println(s);
            System.out.println("出去玩");
        });

    }

    static void useFlyable(Flyable f){
        f.fly("风和日丽");
    }


}

