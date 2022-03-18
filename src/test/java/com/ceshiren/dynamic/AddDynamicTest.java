/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.dynamic;

import com.ceshiren.Calculator;
import com.ceshiren.entity.AData;
import com.ceshiren.entity.Add1;
import com.ceshiren.entity.ReseultList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.slf4j.LoggerFactory.getLogger;

public class AddDynamicTest {
    public Calculator calculator;
    final Logger logger = getLogger(lookup().lookupClass());


    @TestFactory
    Stream<Object> addTest(){
        calculator = new Calculator("动态测试add用例");
        logger.info("开始动态测试生成");
        List<DynamicTest> dynamicTestList = new ArrayList<>();

        //yaml文件解析
        Add1 add1Yaml = getAdd1Yaml();
        Stream<AData> errorStream = add1Yaml.getDatas().stream().filter(aData -> aData.getMessage().startsWith("无效"));
        Stream<DynamicTest> dynamicTestStream = errorStream.map(m -> dynamicTest(m.getMessage(), () -> {

            //超过边界值的加法运算
            Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> calculator.sum(m.getA(), m.getB()));
            // expected:期望值,  actual:运算的实际值
            assertTrue(illegalArgumentException.getMessage().contains("请输入范围内的整数"));
        }));

        Stream<AData> right = add1Yaml.getDatas().
                stream().filter(m -> m.getMessage().startsWith("有效"));
        Stream<DynamicTest> dynamicTestStream1 = right.map(data -> dynamicTest(data.getMessage(), () -> {

            //加法运算
            double sum = calculator.sum(data.getA(), data.getB());
            logger.info("Addition result：{}",sum);
            // expected:期望值,  actual:运算的实际值
            assertEquals(data.getResult(), sum);
        }));
        return Stream.concat(dynamicTestStream,
                dynamicTestStream1);
    }





    private Add1 getAdd1Yaml()  {
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

}
