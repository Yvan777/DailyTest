package ExAndImp;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/15 3:00 下午
 */
public  class TestAbEr2 implements TestAb, TestAb2 {


    @Override
    public void ab1() {
        System.out.println("=====1");
    }

    @Override
    public void ab2() {
        System.out.println("=====2");
    }

    @Override
    public void ab3() {
        System.out.println("=====3");
    }

    public static void main(String[] args) {

        TestAbEr2 testAbEr2 = new TestAbEr2();

        testAbEr2.ab1();
        testAbEr2.ab2();
        testAbEr2.ab3();

    }
}
