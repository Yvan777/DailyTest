package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/8/30 13:51
 */
public class ListTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
//        ArrayList<Object> list = Lists.newArrayList();
//        List<String> list1 = Arrays.asList();
//        List<String> list = new ArrayList<>(list1);
        list.add("x");
        list.add("xx");
        list.add("xxx");
        System.out.println(list);
        list.forEach(
                o-> System.out.println("ssssss"+o)
        );

    }
}
