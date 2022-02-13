/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.dynamic;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.slf4j.Logger;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.slf4j.LoggerFactory.getLogger;

public class DynamicDemoTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    //测试工厂：生产测试方法
    //动态测试用例：返回的必须是DynamicTest的 Collection，stream，Iterable，Iterator
    //不能是private
    //不能是static
    //动态方法中没有beforeEach afterEach

    /**
     * TestFactory去告诉JUnit5框架 ：这是一个创建动态测试的工厂
     * @return
     */
    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                //运行runtime的时候生产测试用例
                //2部分组成：第一部分displayName
                //第二部分就是对应的业务流 Executable
                dynamicTest("1st dynamic test", () ->
                {
                    assertTrue(true);
                    logger.info("动态测试第一个用例");
                }),
                dynamicTest("2nd dynamic test", () -> {
                                                            System.out.println("动态测试第二个用例");
                                                            logger.info("动态测试第2个用例");
                                                        })
        );
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () ->
                {
                    assertTrue(true);
                    logger.info("动态测试第一个用例");
                }),
                dynamicTest("2nd dynamic test", () -> {
                    System.out.println("动态测试第二个用例");
                    logger.info("动态测试第2个用例");
                })
        ).stream();
    }
}
/**
 * 有一个测试的结果文件，合并到JUnit5里面生成测试报告
 * yaml
 * 关键字驱动的测试框架 用动态生成测试用例
 */

/*@TestFactory
Stream<DynamicTest> testcases() {
    return Arrays.asList(
            dynamicTest("自动化用例动态生成", () ->
            {
//                run();
            })

    ).stream();
}
}*/

