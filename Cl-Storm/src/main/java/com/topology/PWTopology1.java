package com.topology;

import com.spout.PWSpout;
import com.storm.PrintBolt;
import com.storm.WriteBolt;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class PWTopology1 {
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		Config config = new Config();
		config.setNumAckers(2);//设置几个jvm
		config.setDebug(true);//设置debug
		
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		topologyBuilder.setSpout("spout", new PWSpout());//创建spout拓扑
		topologyBuilder.setBolt("print-bolt", new PrintBolt()).shuffleGrouping("spout");//将spout装入bolt中
		topologyBuilder.setBolt("write-bolt", new WriteBolt()).shuffleGrouping("print-bolt");//将上一个bolt节点装进来
		
		
		
		//1 本地模式
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("top1", config, topologyBuilder.createTopology());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cluster.killTopology("top1");
		cluster.shutdown();
		
		//2 集群模式
//		StormSubmitter.submitTopology("top1", config, topologyBuilder.createTopology());
		
	}
}
