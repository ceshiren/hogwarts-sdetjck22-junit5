/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.nums;

import com.ceshiren.Calculator;
import com.ceshiren.entity.AData;
import com.ceshiren.entity.Add;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AddTest {
    public static Calculator calculator;


    @ParameterizedTest
    //静态方法源
    @MethodSource("sumIntArrays")
    @DisplayName("2个数相加/相减")
    void addTest(AData aData){
        List<Executable> assertAllList = new ArrayList<>();
        calculator = new Calculator("计算器的数字计算");
        //对应测试代码
        int result1 = calculator.sum(aData.getA(), aData.getB());
        assertAllList.add(() -> assertEquals(aData.getResult(),result1,"2个数相加的结果不正确"));
        //软断言
        //只要有一个false 所有的就都false
        assertAll("加法和减法的运算结果失败",assertAllList.stream());

    }

    static Stream<AData> sumIntArrays(){

        List<AData> datas = getAddYaml().getDatas();
        Stream<AData> stream = datas.stream();
        return stream;
    }


    @ParameterizedTest(name = "（{0}） + （{1}） = {2}")
    //静态方法源
    @MethodSource
    @DisplayName("2个数相加/相减")
    void add1Test(int a, int b ,int re1){
        List<Executable> assertAllList = new ArrayList<>();
        calculator = new Calculator("计算器的数字计算");
        //对应测试代码
        int result1 = calculator.sum(a, b);
        assertAllList.add(() -> assertEquals(re1,result1,"2个数相加的结果不正确"));
        //软断言
        //只要有一个false 所有的就都false
        assertAll("加法和减法的运算结果失败",assertAllList.stream());

    }

    static Stream<Arguments> add1Test(){

        List<AData> datas = getAddYaml().getDatas();
        List<Arguments> argumentsList = new ArrayList<>();
        datas.forEach(aData -> {
            argumentsList.add(arguments(aData.getA(),aData.getB(),aData.getResult()));
        });
        return argumentsList.stream();

    }



    @Test
    void yamlTest(){
        Add add = getAddYaml();
        System.out.println(add);
    }

    private static Add getAddYaml()  {
        Add add =null;
        try{
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            TypeReference<Add> typeReference = new TypeReference<Add>() {
            };
            add = objectMapper.readValue(new File("src/test/resources/add.yaml"), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return add;
    }

}
