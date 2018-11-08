package KafkaStorm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

public class SentenceCountBolt extends BaseRichBolt {
    private OutputCollector outputCollector;
    private int[]upper_character = new int[26];//大写计数 ascii 65-90
    private int[]low_character = new int[26];//小写计数 ascii 97-122
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    public void execute(Tuple tuple) {
        String sentence = tuple.getStringByField("sentence");
        char []characters = sentence.toCharArray();
        for(char c:characters){
            int ascii_code = (int)c;
            if(ascii_code>=65&&ascii_code<=90){
                upper_character[ascii_code-65]++;
            }else if(ascii_code>=97&&ascii_code<=122){
                upper_character[ascii_code-97]++;
            }
        }
        System.out.println("大写字母：");
        for(int i=0;i<upper_character.length;i++){
            System.out.println((char)(i+65)+'\t'+upper_character[i]);
        }
        System.out.println("小写字母：");
        for(int i=0;i<low_character.length;i++){
            System.out.println((char)(i+97)+'\t'+low_character[i]);
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
