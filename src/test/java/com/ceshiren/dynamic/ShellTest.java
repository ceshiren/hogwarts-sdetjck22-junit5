/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.dynamic;

import com.ceshiren.entity.ReseultList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class ShellTest {

    @Test
    void shellYamlTest(){
        getShellYaml();
    }

    private ReseultList getShellYaml() {
        ReseultList reseultList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            TypeReference<ReseultList> reseultListTypeReference = new TypeReference<ReseultList>() {
            };
            reseultList = objectMapper.readValue(new File("src/test/resources/shell_test_result.yaml"), reseultListTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(reseultList);
        return reseultList;
    }


    /**
     * 参数化
     * 区别点：
     * 参数化的都是直接写好的，在编译前创建的测试
     * 动态测试是编译之后，边运行边生成测试用例
     * @return
     */
    @TestFactory
    Stream<DynamicTest> shellDynamicTest(){
        List<DynamicTest> dynamicTestList = new ArrayList<>();
        ReseultList shellYaml = getShellYaml();
        shellYaml.getResultList().forEach(shellResult -> {
            dynamicTestList.add(dynamicTest(shellResult.getCaseName(),()-> assertTrue(shellResult.isResult())));
        });

        return dynamicTestList.stream();
    }

}
