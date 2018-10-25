package storm;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import java.util.Map;

public class MyKafkaBolt implements IBasicBolt {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public void cleanup() {
        // TODO Auto-generated method stub

    }


    public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub

        String kafkaMsg = input.getString(0) ;
        System.err.println("bolt:"+kafkaMsg);
    }


    public void prepare(Map stormConf, TopologyContext context) {
        // TODO Auto-generated method stub

    }


    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub

    }


    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

}
