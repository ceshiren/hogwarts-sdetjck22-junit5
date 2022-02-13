/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class Calculator {
    //获得具有所需名称的记录器
    static final Logger logger = getLogger(lookup().lookupClass());

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static int result = 0;

    //用例名
    String name;//计算器1
    //唯一ID标识
    String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calculator(String name) {
        this.name = name;
        logger.info("创建 {} ", name);
    }


    public void initId(){
        id = UUID.randomUUID().toString();
        logger.info("生成ID：{} 并绑定", id);
        threadLocal.set(id);
    }
    public void destroyId() {
       /* if (id == null) {
            throw new IllegalArgumentException(name + " 没有初始化对应ID");
        }
        logger.info("ID: {} 释放", id);
        id = null;*/
        threadLocal.remove();
    }


    public void close() {
        logger.info("关闭 {} ", name);
    }


    //连续添加
    @Step("连续加 {numbers}")
    public int sum(int... numbers) {
            logger.info("sum...");

            //[-99,99]
            if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
                logger.warn("请输入范围内的整数");
                throw new IllegalArgumentException("请输入范围内的整数！");

            }else {
                //a+b+c+d
                //numbers[0]+numbers[1]+...numbers[index-1]
                try {
                    Allure.addAttachment("动态上传图片", "image/jpeg",new FileInputStream("./桌面背景.jpeg"),".jpeg");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                return IntStream.of(numbers).sum();
            }





    }


    //从100进行减法
    public int subtract(int... numbers) {
        //[-99,99] int
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            logger.warn("请输入范围内的整数");
            throw new IllegalArgumentException("100连续减的方法内请输入范围内的整数！");
        }else {
            //100-2-3-4-5-6
            return IntStream.of(numbers).reduce(100, (a, b) -> a-b);
            //a=2 2-numbers[0]-numbers[1]-...numbers[index-1]
            //2-1-3-2-1
        }
    }

    @Step
    public int subtract(int x,int y) {
        logger.info("sub...");
        //[-99,99]
        if(x>99 | x<-99 | y>99 | y<-99){
            logger.warn("请输入范围内的整数");
            return 0;
        }else {
            return x-y;

        }

    }


    //平均值 average
    public double average(int... numbers) {
        //[-99,99]
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            logger.warn("请输入范围内的整数");
            return 0;
        }else {
            //8 9 7 6 5 3
            return IntStream.of(numbers).average().getAsDouble();
        }
    }

    //连续拼接
    //String x1,String y1,...
    public String concatStr(String... words) {
        //hello,junit5   hello junit5
        return String.join(" ", words);

    }

}
