package java8.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/11/4 14:45
 */
public class streamTest {
    public static void main(String[] args) {
        List<VatTypeDTO> vatTypeDTOS = new ArrayList<>();
        for (int i = 1; i <=5; i++) {
            VatTypeDTO vatTypeDTO = new VatTypeDTO();
            vatTypeDTO.setVatCode(String.valueOf(i));
            vatTypeDTO.setVatName("税种"+i);
            vatTypeDTOS.add(vatTypeDTO);
        }
        List<VatTypeDTO> list = new ArrayList<>();
        VatTypeDTO vatTypeDTO1 = new VatTypeDTO();
        vatTypeDTO1.setVatCode("1");
        vatTypeDTO1.setVatName("11");
        VatTypeDTO vatTypeDTO2 = new VatTypeDTO();
        vatTypeDTO2.setVatCode("3");
        vatTypeDTO2.setVatName("33");
        list.add(vatTypeDTO1);
        list.add(vatTypeDTO2);

        System.out.println("=============按照code逆序1================");
        //按照code逆序1
        vatTypeDTOS.stream().filter(
                e -> !(list.stream().map(s -> s.getVatCode()).collect(Collectors.toList()))
                        .contains(e.getVatCode())
        ).sorted(Comparator.comparing(VatTypeDTO::getVatCode).reversed())
                .forEach(System.out::println);
        System.out.println("==============按照code逆序2===============");
        //按照code逆序2
        vatTypeDTOS.stream().
                sorted((a,b)->b.getVatCode().compareTo(a.getVatCode()))
                .forEach(System.out::println);
        System.out.println("================拿到code按照code逆序=============");
        vatTypeDTOS.stream().map(VatTypeDTO::getVatCode).sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        System.out.println("================默认顺序=============");
        //顺序
        vatTypeDTOS.stream().filter(
                e-> !(list.stream().map(VatTypeDTO::getVatCode).collect(Collectors.toList())
                    .contains(e.getVatCode()))
        ).forEach(System.out::println);
    }
}

