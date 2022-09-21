package lamdba;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/18 4:47 下午
 */
interface Eatable {
    void eat();
}

class EatableImpl implements Eatable{
    @Override
    public void eat() {
        System.out.println("一天一苹果，医生远离我");
    }
}

public class Testlamdba {
    public static void main(String[] args) {
        //无参
        //1.实现类方法
        //在主方法中调用useEatable方法
        Eatable e = new EatableImpl();
        useEatable(e);

        //2.匿名内部类
        useEatable(new Eatable() {
            @Override
            public void eat() {
                System.out.println("一天一苹果，医生远离我");
            }
        });

        //3.lambda
        useEatable(
                ()->{
                    System.out.println("一天一苹果，医生远离我");
                }
        );

    }
    //无参
    private static void useEatable(Eatable e){
        e.eat();
    }


}

