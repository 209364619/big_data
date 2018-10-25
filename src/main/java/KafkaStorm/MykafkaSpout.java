package KafkaStorm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

import java.util.ArrayList;
import java.util.List;

public class MykafkaSpout {

    public static void main(String[] args)  {
        // TODO Auto-generated method stub

        String topic = "data" ;
        ZkHosts zkHosts = new ZkHosts("192.168.1.231:2181,192.168.1.232:2181,192.168.1.233:2181");
        SpoutConfig spoutConfig = new SpoutConfig(zkHosts, topic,
                "",
                "MyTrack") ;
//        List<String> zkServers = new ArrayList<String>() ;
//        zkServers.add("192.168.1.231");
//        zkServers.add("192.168.1.232");
//        zkServers.add("192.168.1.233");
//        spoutConfig.zkServers = zkServers;
//        spoutConfig.zkPort = 2181;
        spoutConfig.socketTimeoutMs = 60 * 1000 ;
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme()) ;

        TopologyBuilder builder = new TopologyBuilder() ;
        builder.setSpout("spout", new KafkaS pout(spoutConfig) ,1) ;
        builder.setBolt("bolt1", new MyKafkaBolt(), 1).shuffleGrouping("spout") ;

        Config conf = new Config ();
        conf.setDebug(false) ;

        if (args.length > 0) {
            try {
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        }else {
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("mytopology", conf, builder.createTopology());
        }

    }

}
