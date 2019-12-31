package com.zhl.basic.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lenovo
 * @title: CollectionTest
 * @projectName basic
 * @description: TODO
 * @date 2019/11/28 16:28
 */
public class CollectionTest {
    public static void main(String[] args) {
        ArrayList<String> str = new ArrayList<>();
        Map<String,String> obj = new HashMap<>();
        Map<String,String> dev = new Hashtable<>();
        Map<String,String> dev1 = new ConcurrentHashMap<>();
        List list = new Vector();

        Iterator<String> it = str.iterator();
        str.ensureCapacity(100);
    }
}
