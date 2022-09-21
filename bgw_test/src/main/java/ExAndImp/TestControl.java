package ExAndImp;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/15 3:03 下午
 */
public class TestControl {
    public static void main(String[] args) {
        //statc
//        new InnerClass();
//        InnerClass inner=new ExAndImp.TestControl.InnerClass();
//        inner.InnerMethod();
        //向上转型
//        ExAndImp.xx.TestBaba bx = new ExAndImp.xx.TestEr1();
//        bx.baba();
//        ExAndImp.xx.TestBa bax = new ExAndImp.xx.TestEr3();
//        bax.babaSay();
        //向下转型(还原
//        ExAndImp.xx.TestEr3 bax3 = (ExAndImp.xx.TestEr3)bax;
        //多态
//        TestBa a = new TestEr3();
//        a.babaSay();
//        TestBa bas2 = new TestEr2();
        TestBa bas3 = new TestEr3();

//        TestEr2 er2 = (TestEr2) bas2;
//        er2.babaSay();
//        er2.erz2Say();
        TestEr3 er3 = (TestEr3) bas3;
        er3.babaSay();
        er3.erz3Say();
    }


    public static class InnerClass{
        InnerClass(){ System.out.println("====== 静态内部类======"); }
        public void InnerMethod() { System.out.println("===== 静态内部方法=====");}
    }

}
