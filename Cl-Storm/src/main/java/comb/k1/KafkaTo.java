package comb.k1;

import com.storm.PrintBolt;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import comb.storm.kafka.example.SentenceBolt;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

public class KafkaTo {
	public static void main(String[] args) {
		//消费端
		ZkHosts zkHosts = new ZkHosts("192.168.25.128.2181");
		//创建storm与kafka的连接
		SpoutConfig spoutConfig = new SpoutConfig(zkHosts, "word_topic", "", "id7");
		
		SchemeAsMultiScheme schemeAsMultiScheme = new SchemeAsMultiScheme(new StringScheme());
		spoutConfig.forceFromStart = true;
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("KafkaSpout", new KafkaSpout(spoutConfig));
		builder.setBolt("SentenceBolt", new SentenceBolt(),1).globalGrouping("KafkaSpout");
		builder.setBolt("PrinterBolt", new PrintBolt(),1).globalGrouping("SentenceBolt");
		LocalCluster localCluster = new LocalCluster();
		Config config = new Config();
		
		localCluster.submitTopology("KafkaToplogy", config, builder.createTopology());
		try {
			// Wait for some time before exiting
			System.out.println("Waiting to consume from kafka");
			Thread.sleep(10000);
		} catch (Exception exception) {
			System.out.println("Thread interrupted exception : " + exception);
		}

		// kill the KafkaTopology
		localCluster.killTopology("KafkaToplogy");

		// shut down the storm test cluster
		localCluster.shutdown();
	}
}
