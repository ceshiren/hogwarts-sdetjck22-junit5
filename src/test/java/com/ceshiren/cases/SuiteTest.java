/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.cases;


import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        com.ceshiren.nums.SumTest.class,
        com.ceshiren.strs.StrsTest.class
})
@SuiteDisplayName("加法、字符串拼接")
//测试用例的时候，区分环境会比较多
//test1,
@IncludeTags({
        "en",
        "true"
})
public class SuiteTest {
}
