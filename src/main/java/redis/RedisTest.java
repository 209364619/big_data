package redis;

import java.util.*;

public class RedisTest {
    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
//        redisUtil.insert_str("user","redis");
//        Set<String> set = new HashSet<String>();
//        set.add("wukong");
//        set.add("wufan");
//        set.add("wutian");
//        set.add("guixianren");
//        redisUtil.Set_insert(set,"longzhu");
//        List<String> list = new ArrayList<String>();
//        list.add("zhangsan");
//        list.add("lisi");
//        list.add("wangwu");
//        list.add("zhaoliu");
//        redisUtil.List_insert(list,"user_list");
//        redisUtil.List_get("xiyouji",0,100);
        Map map = new HashMap();
        map.put("field1","value1");
        map.put("field2","value2");
        map.put("field3","value3");
        map.put("field4","value4");
        redisUtil.Hash_insert("hash_test",map);
    }

}
