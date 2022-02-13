/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.entity;

import java.util.List;

public class ReseultList {
    private List<ShellResult> resultList;


    public List<ShellResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ShellResult> resultList) {
        this.resultList = resultList;
    }


    @Override
    public String toString() {
        return "ReseultList{" +
                "resultList=" + resultList +
                '}';
    }
}
