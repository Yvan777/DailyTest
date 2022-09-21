package ExAndImp;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/15 3:00 下午
 */
public class TestEr1 extends TestBa implements TestBaba {

    @Override
    public void baba() {
        System.out.println("erzi1");
    }

    @Override
    public void babaSay(){
        System.out.println("erzi1Say");
    }
}
