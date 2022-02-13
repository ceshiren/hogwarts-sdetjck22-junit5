/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.basic;

import com.ceshiren.Calculator;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.Optional;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BaseTest {
    public static final Logger logger = getLogger(lookup().lookupClass());
    public static Calculator calculator;
    public int result;
    //字符串的运算结果
    public String resultStr;
    //static修饰  beforeall只运行一次，在最开始的时候
    //数据的初始化、对应的安装环境准备都在这里
    @BeforeAll
    static void beforeAll(){
        logger.info("BaseTest--beforeAll");

        //打开计算器，初始化对象
        calculator = new Calculator("计算器");
    }

    //void
    //在每个测试方法之前运行，有多少个测试方法就运行多少次
    @BeforeEach
    void beforeEach(){
        logger.info("BaseTest--beforeEach");

        //在测试方法钱生成ID唯一标识
        calculator.initId();
        //log打印：开始进行计算
        logger.info("开始进行计算");

    }
    //void
    //在每个测试方法之后运行  有多少个测试方法就运行多少次
    @AfterEach
    void afterEach(TestInfo testInfo,TestReporter testReporter){
        Optional<Method> testMethod = testInfo.getTestMethod();
        System.out.println("方法名："+testMethod.map(Method::getName));
        //空指针异常Null
        String str = testMethod.map(Method::getName).filter(s -> s.startsWith("str"))
                .ofNullable(resultStr)
                .orElseGet(() -> String.valueOf(result));
        /**
         * if(s.startsWith("str")) resultStr
         * else    result
         */

        //有一个顺序，先加载类tag的标签，再加载方法上的tag标签，最后是一个数组的内容
        logger.info("BaseTest--afterEach");
        logger.info("计算结果：{}",str);

        //resultStr
        //销毁ID
        calculator.destroyId();
    }

    //不论测试方法断言是否失败，都会执行
    //void static修饰
    //afterall最后执行
    @AfterAll
    static void afterAll(){
        logger.info("BaseTest--afterAll");

        //清空测试数据。。。。
        //关闭计算器app
        calculator.close();
    }
}
