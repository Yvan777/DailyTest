package ExAndImp;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 13:48
 */
public class Son  extends  Father{
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类非静态代码块");
    }

    Son() {
        System.out.println("子类构造方法");
    }
}

