package java8;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/18 2:42 下午
 */
public class TestJava8 {
    public static void main(String args[]) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

interface Vehicle {
    //默认方法
    default void print() {
        System.out.println("I am a vehicle!");
    }
    //静态方法
    static void blowHorn() {
        System.out.println("Blowing horn!!!");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("I am a four wheeler!");
    }
}

interface CarTe extends Vehicle,FourWheeler {

    @Override
    default void print() {
        //继承含相同默认方法的父类时需要重写默认方法
        System.out.println("我来重写了");
        //同时重写时也可直接调用父类方法
        Vehicle.super.print();
    }
    //这个抽象方法被忽略了,因为CarTeTe写了同名默认方法
    void print2();
}

interface CarTeTe extends  CarTe,Vehicle {
    @Override
    default void print2(){
        //默认方法优先于抽象方法
    }
}

class Car implements Vehicle, FourWheeler {
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("I am a car!");
    }
}
