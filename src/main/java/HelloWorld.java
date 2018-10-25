import redis.clients.jedis.Jedis;

public class HelloWorld {
    public static void main(String[] args) {
        String []keySets = new String[100];
        for (int i=0;i<10;i++){
            keySets[i] = i+"";
        }
        for (String i:keySets){
            System.out.println(i);
        }
    }
}
