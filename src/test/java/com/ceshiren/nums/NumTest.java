/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.nums;

import com.ceshiren.Calculator;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.slf4j.LoggerFactory.getLogger;

public class NumTest {
    public static Calculator calculator;

    @ParameterizedTest(name = "（{0}） + （{1}） = {2}/（{0}） - （{1}） = {3}")
    //静态方法源
    @MethodSource("sumIntArrays")
    @DisplayName("2个数相加/相减")
    void numTest(int a, int b ,int re1, int re2){
        calculator = new Calculator("计算器的数字计算");
        //对应测试代码
        int result1 = calculator.sum(a, b);
        // expected:期望值，我们自己定义,  actual：实际值，测试代码运行的结果,
        // message：断言失败后显示的错误原因
        //硬断言
        int result2 = calculator.subtract(a,b);
        try {
            Allure.addAttachment("动态上传图片", "image/jpeg",new FileInputStream("./桌面背景.jpeg"),".jpeg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        assertEquals(re1,result1,"2个数相加的结果不正确");

//        assertEquals(re2,result2,"2个数相减的结果不正确");
        //软断言
        assertAll("加法和减法的运算结果失败",
//                () -> assertEquals(re1,result1,"2个数相加的结果不正确"),
                () -> assertEquals(re2,result2,"2个数相减的结果不正确"),
                //hamcrest
                ()->assertThat("2个数相加的结果不正确",re1, equalTo(result1))

        );

        assertAll("加法和减法的运算结果失败",
//                () -> assertEquals(re1,result1,"2个数相加的结果不正确"),
                () -> assertEquals(re2,result2,"2个数相减的结果不正确"),
                //hamcrest
                ()->assertThat("2个数相加的结果不正确",re1, equalTo(result1))

        );

    }

    /**
     * 下单订单
     * 订单状态成功
     * 买的东西 title
     * 数量
     * 最终订单金额
     * 送货地址
     */



    static Stream<Arguments> sumIntArrays(){
        return Stream.of(
                arguments(11,11,20, 0),
                arguments(21,28,49,-7),
                arguments(90,90,180,0)
        );
    }


    @ParameterizedTest(name = "（{0}） + （{1}） = {2}/（{0}） - （{1}） = {3}")
    //静态方法源
    @MethodSource("sumIntArrays")
    @DisplayName("2个数相加/相减")
    void num1Test(int a, int b ,int re1, int re2){
        List<Executable> assertAllList = new ArrayList<>();
        calculator = new Calculator("计算器的数字计算");
        //对应测试代码
        int result1 = calculator.sum(a, b);
        assertAllList.add(() -> assertEquals(re1,result1,"2个数相加的结果不正确"));
        // expected:期望值，我们自己定义,  actual：实际值，测试代码运行的结果,
        // message：断言失败后显示的错误原因
        //硬断言
        int result2 = calculator.subtract(a,b);
//        assertEquals(re1,result1,"2个数相加的结果不正确");
        assertAllList.add(() ->assertEquals(re2,result2,"2个数相减的结果不正确"));
//        assertEquals(re2,result2,"2个数相减的结果不正确");

        Object o = assertAllList;
        //软断言
        //只要有一个false 所有的就都false
        assertAll("加法和减法的运算结果失败",assertAllList.stream());

    }

}
