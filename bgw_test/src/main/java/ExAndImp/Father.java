package ExAndImp;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 13:48
 */
public class Father {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类非静态代码块");
    }

    Father() {
        System.out.println("父类构造方法");
    }
}
