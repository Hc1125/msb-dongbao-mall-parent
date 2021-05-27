package com.msb.dongbao.portal.web.controller.api;

import com.msb.msbdongbaocommonutil.MD5Util;
import com.msb.msbdongbaocommonutil.Sha256Utils;

import java.util.*;

public class CheckUtils {

    // app secret 和 appid一一对应
    public static String appSecret = "aaa";



    //根据map生成签名
    public static String generatorSign(Map<String, Object> map) {
        map.remove("sign");
        // 排序
        Map<String, Object> stringObjectMap = sortMapByKey(map);

        // 转格式： name = 张三 & age = 10, : name,张三,age,10
        Set<Map.Entry<String, Object>> entries = stringObjectMap.entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : entries) {
            sb.append(entry.getKey() + ", " + entry.getValue()).append("#");
        }

        // 组装secret  在参数的后面 添加 secret
        sb.append("secret").append(appSecret);
        // md5 生成签名
        return MD5Util.md5(sb.toString());
        // sha256 生成签名
        //return Sha256Utils.getSHA256(sb.toString());
    }

    public static Map<String, Object> sortMapByKey(Map<String ,Object> map) {
        // 判断一下map是否为空，自己写

        Map<String, Object> sortMap = new TreeMap<>(new MyMapComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    static class MyMapComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return -o2.compareTo(o1);
        }
    }

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appId", 1);
        map.put("name", 2);
//        map.put("timestamp", 1622128219000L);
        String s = generatorSign(map);
        // 36c7186312dfab330052581d2a888b88
        // sha256: ac2441774a55f0114b6519135d72c6ad3f2962947fd7733bc9b4bc1cd9f5f575
        System.out.println(s);
    }

}
