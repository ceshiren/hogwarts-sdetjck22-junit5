/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.nums;

import com.ceshiren.basic.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubTest extends BaseTest {

    @Test
    void sub1Test(){
         result = calculator.subtract(20, 30,10);
         assertEquals(40,result,"2个数相加");
    }
}
