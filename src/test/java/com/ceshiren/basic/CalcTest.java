/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.basic;

import com.ceshiren.Calculator;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.slf4j.LoggerFactory.getLogger;

@DisplayName("计算器测试用例")
//@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CalcTest extends BaseTest{

    //可以有多个
    //void修饰
    //测试方法入口
//    @Test

    //注意：参数化的时候Test注解去掉，换成ParameterizedTest
    //自定义参数化显示名 name   "[{index}] {argumentsWithNames}"
    //[{index}]  [1]  [2]   [3]
    //argumentsWithNames 相当于一个数组 直接拿取下标即可，下标从0开始
    //这个是方法上的注解  ParameterizedTest + MethodSource
    @ParameterizedTest(name = "{0} + {1} = {2}")
    //静态方法源
    @MethodSource("sumIntArrays")
    @DisplayName("2个数相加")
    @Order(3)
    void sum1Test(int a, int b, int re, List<String> listStr){
        logger.info("listStr:{}",listStr);
        //对应测试代码
        result = calculator.sum(a, b);
        // expected:期望值，我们自己定义,  actual：实际值，测试代码运行的结果,
        // message：断言失败后显示的错误原因
        assertEquals(re,result,"2个数相加的结果不正确");
    }

    static Stream<Arguments> sum1Test(){
        return Stream.of(
//                Arguments.arguments()
                arguments(1,1,2, Arrays.asList("junit5","one")),
                arguments(5,8,13,Arrays.asList("junit52","one2")),
                arguments(99,99,198,Arrays.asList("junit53","one3"))
        );
    }
    static Stream<Arguments> sumIntArrays(){
        return Stream.of(
//                Arguments.arguments()
                arguments(11,11,22, Arrays.asList("1sumIntArrays","1one")),
                arguments(21,28,49,Arrays.asList("2sumIntArrays","2one2")),
                arguments(90,90,180,Arrays.asList("3sumIntArrays","3one3"))
        );
    }


    @Test
    @DisplayName("3个数相加")
    @Order(13)
    void sum2Test(){
        //对应测试代码
        result = calculator.sum(1, 1,9);
        // expected:期望值，我们自己定义,  actual：实际值，测试代码运行的结果,
        // message：断言失败后显示的错误原因
        assertEquals(11,result,"3个数相加的结果不正确");
    }
    @DisplayName("2个数边界值的相加")
    @Test
    @Order(5)
    void sum3Test(){
        //对应测试代码
//        result = calculator.sum(100,99);
        //Class<T> expectedType, 抛出异常的class类 必须.class
        //Executable executable, 业务逻辑内容
        //Supplier<String> messageSupplier  如果断言失败显示的错误原因
        Exception e = assertThrows(IllegalArgumentException.class, () -> calculator.sum(100, 99));
        //请输入范围内的整数！  e.getMessage()
        assertTrue(e.getMessage().contains("输入范围内的整数"));


    }


    @BeforeAll
    static void bf(){
        logger.info("CalcTest--beforeAll");
    }


    @BeforeEach
    void be(){
        logger.info("CalcTest---beforeEach");
    }

    @AfterEach
    void ae(){
        logger.info("CalcTest---afterEach");
    }

    @AfterAll
    static void aa(){
        logger.info("CalcTest---afterAll");
    }


}
//beforeall  ---beforeeach---test---aftereach
//           ---beforeeach---test---aftereach
//           ---beforeeach---test---aftereach
//afterall