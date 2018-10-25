package redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisUtil {
    private static Jedis jedis;
    public RedisUtil(){
        jedis = new Jedis("192.168.1.233",6379);
    }
    public RedisUtil(String host,int port){
        jedis = new Jedis(host, port);
    }

    /**
     * 通过key插入字符串
     * @param key
     * @param value
     */
    public void String_insert(String key,String value){
        jedis.set(key,value);
        System.out.println("插入成功！");
    }

    /**
     * 通过keyname插入hashlist
     * @param keyname
     * @param map
     */
    public void Hash_insert(String keyname, Map<String,String> map){
        jedis.hmset(keyname,map);
        String []keySets=new String[100];
        Iterator it = map.keySet().iterator();
        int i = 0;
        while (it.hasNext()){
            String key = it.next().toString();
            keySets[i++]= key;
            System.out.println(key);
        }
        String []key_sets = new String[i];
        for(int j=0;j<i;j++){
            key_sets[j]=keySets[j];
        }
        Get_Hash(keyname,key_sets);
    }

    /**
     * 获取hashlist
     * @param keyname
     * @param keys
     */
    public void Get_Hash(String keyname,String[] keys){
        for(int i=0;i<keys.length;i++){
            System.out.print(keys[i]+'\t');
        }
        System.out.println();
        List list = jedis.hmget(keyname, keys);
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i).toString()+'\t');
        }
        System.out.println();
    }

    /**
     * 插入set
     * @param set
     * @param name
     */
    public void Set_insert(Set set,String name){
        Iterator it = set.iterator();
        while(it.hasNext()){
            String value = it.next().toString();
            System.out.println(value);
            jedis.sadd(name,value);
        }
    }

    /**
     * 获取set
     * @param set_name
     */
    public void Get_Set(String set_name){
        Set<String> rs = jedis.keys(set_name);
        Iterator it = rs.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    public void List_insert(List<String> list,String keyname){
        Iterator it = list.iterator();
        while (it.hasNext()){
            String value = it.next().toString();
            System.out.println(value);
            jedis.lpush(keyname,value);
        }
    }
    public void List_get(String keyname,long start,long end){
        List list = jedis.lrange(keyname,start,end);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }
}
