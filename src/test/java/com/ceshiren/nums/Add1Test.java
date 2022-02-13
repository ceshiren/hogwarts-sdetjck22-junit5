/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.nums;

import com.ceshiren.Calculator;
import com.ceshiren.entity.AData;
import com.ceshiren.entity.Add;
import com.ceshiren.entity.Add1;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Add1Test {
    public static Calculator calculator;

    private static Add1 getAdd1Yaml()  {
        Add1 add1 =null;
        try{
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            TypeReference<Add1> typeReference = new TypeReference<Add1>() {
            };
            add1 = objectMapper.readValue(new File("src/test/resources/add1.yaml"), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return add1;
    }

    @Test
    void yamlTest(){
        Add1 add1 = getAdd1Yaml();
        System.out.println(add1);
    }




    @ParameterizedTest(name = "（{0}） + （{1}） = {2}")
    //静态方法源
    @MethodSource
    @DisplayName("2个数相加/相减")
    void add1Test(int a, int b ,int re1,String message){
        List<Executable> assertAllList = new ArrayList<>();
        calculator = new Calculator("计算器的数字计算");
        if (message.startsWith("无效")) {
            Exception e = assertThrows(IllegalArgumentException.class, () -> calculator.sum(a, b));
            //请输入范围内的整数！  e.getMessage()
            assertTrue(e.getMessage().contains("输入范围内的整数"));
        }else {
            //对应测试代码
            int result1 = calculator.sum(a, b);
            assertAllList.add(() -> assertEquals(re1,result1,"2个数相加的结果不正确"));
            //软断言
            //只要有一个false 所有的就都false
            assertAll("加法和减法的运算结果失败",assertAllList.stream());
        }
    }

    static Stream<Arguments> add1Test(){
        List<AData> datas = getAdd1Yaml().getDatas();
        List<Arguments> argumentsList = new ArrayList<>();
/*        for (int i = 0; i < datas.size()-1; i++) {
            AData aData = datas.get(i);
//            Arguments.arguments(aData.getA(),aData.getB(),aData.getResult(),aData.getMessage());
            argumentsList.add(Arguments.arguments(aData.getA(),aData.getB(),aData.getResult(),aData.getMessage()));
        }*/
        datas.forEach(aData -> {
            argumentsList.add(arguments(aData.getA(),aData.getB(),aData.getResult(),aData.getMessage()));
        });
        return argumentsList.stream();

    }
}
