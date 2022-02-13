/*
 * @Author: 霍格沃兹测试开发学社
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.entity;

import java.util.List;

public class Add1 {
    private List<AData> datas;

    public List<AData> getDatas() {
        return datas;
    }

    public void setDatas(List<AData> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Add1{" +
                "datas=" + datas +
                '}';
    }
}
