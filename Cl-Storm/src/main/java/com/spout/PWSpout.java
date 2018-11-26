package com.spout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class PWSpout extends BaseRichSpout {
private static final long serialVersionUID = 1L;
private SpoutOutputCollector collector;
private static final Map<Integer,String> map = new HashMap<Integer, String>();

static{//数据源
	map.put(0, "java");
	map.put(1, "php");
	map.put(2, "groovy");
	map.put(3, "python");
	map.put(4, "ruby");
}
	public void nextTuple() {
		//主要逻辑处理方法
		//随机发送一个单词
		final Random r = new Random();
		int nextInt = r.nextInt(4);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.collector.emit(new Values(map.get(nextInt)));
	}

	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		// TODO Auto-generated method stub
		this.collector = arg2;//对spout进行初始化
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		//进行声明
		arg0.declare(new Fields("print"));
	}

}
