package com.xuexi.util;

import java.util.HashMap;
import java.util.Map;

public class UtilsMap {

    /**
     * 返回单个Map对象
     *
     * @param k   key
     * @param v   value
     * @param <K> K
     * @param <V> V
     * @return Map
     */
    public static <K, V> Map<String, V> toMap(K k, V v) {
        return toMap(new HashMap<>(), k, v);
    }

    @SuppressWarnings("unchecked")
    private static <V> Map<String, V> toMap(Map<String, V> map, Object... data) {
        int i = 0;
        while (i < data.length) {
            map.put((String) data[i++], (V) data[i++]);
        }
        return map;
    }
}