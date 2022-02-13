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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.slf4j.LoggerFactory.getLogger;

public class AddDynamicTest {
    public Calculator calculator;
    final Logger logger = getLogger(lookup().lookupClass());


//课下参考代码发给大家
//    @TestFactory
//    Stream<DynamicTest> addTest(){
//        calculator = new Calculator("动态测试add用例");
//        logger.info("开始动态测试生成");
//        List<DynamicTest> dynamicTestList = new ArrayList<>();
//        //yaml文件解析
//        Add1 add1Yaml = getAdd1Yaml();
//        Stream<AData> errorStream = add1Yaml.getDatas().stream().filter(aData -> aData.getMessage().startsWith("无效"));
//
//        errorStream.map(aData -> {
//            return dynamicTestList.add(dynamicTest((aData.getA()) + "+" + (aData.getB()),
//                    () -> {
//                        Exception e = assertThrows(IllegalArgumentException.class, () -> calculator.sum(aData.getA(), aData.getB()));
//                        //请输入范围内的整数！  e.getMessage()
//                        assertTrue(e.getMessage().contains("输入范围内的整数"));
//                    }
//            ));
//                });

        /*Stream<DynamicTest> errStream = errorStream.map(aData -> {
            dynamicTestList.add(
                    dynamicTest((aData.getA()) + "+" + (aData.getB()),
                            () -> {
                                Exception e = assertThrows(IllegalArgumentException.class, () -> calculator.sum(aData.getA(), aData.getB()));
                                //请输入范围内的整数！  e.getMessage()
                                assertTrue(e.getMessage().contains("输入范围内的整数"));
                            }));
        });*/
//        return Stream.concat();
//    }



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
