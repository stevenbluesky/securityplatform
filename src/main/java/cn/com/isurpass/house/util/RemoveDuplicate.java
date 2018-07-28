package cn.com.isurpass.house.util;

import java.util.HashSet;
import java.util.List;

public class RemoveDuplicate {

    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
