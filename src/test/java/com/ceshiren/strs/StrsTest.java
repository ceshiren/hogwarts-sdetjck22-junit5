/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.strs;

import com.ceshiren.basic.BaseTest;
import org.junit.jupiter.api.*;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrsTest extends BaseTest {


    @Test
    @DisplayName("2个英文字符拼接")
    @Tag("en")
    void strs1Test(TestInfo testInfo){
//        logger.info("测试方法名：{}",testInfo.getTestMethod());
//        Optional<Method> testMethod = testInfo.getTestMethod();
//        Optional<String> s = testMethod.map(Method::getName);
//        //建议大家研究一下s.filter().
//        s.filter(str -> str.contains("str")).ofNuallable().or
//        s.isPresent();//不建议使用，因为它是判断对象  null != obj
        resultStr = calculator.concatStr("Hello", "Junit5");
        assertEquals("Hello Junit5",resultStr);
    }
    @Test
    @Tags({
            @Tag("test1"),
            @Tag("test4"),
            @Tag("en")
    })
    void strs2Test(){
//        yaml
        resultStr = calculator.concatStr("This","is", "Junit5");
        assertEquals("This is Junit5",resultStr);
    }
    @Test
    @Tag("zh")
    void strs3Test(){
        resultStr = calculator.concatStr("这是", "北京");
        assertEquals("这是 北京",resultStr);
    }
}
