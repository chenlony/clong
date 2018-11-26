package com.storm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import ring.util.codec__init;

public class PrintBolt extends BaseBasicBolt {
	private static final Log log = LogFactory.getLog(PrintBolt.class);
	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		// 获取上一个组件声明得Field
		String print = arg0.getStringByField("print");
		log.info("print:"+print);
		//进行传递下一个bolt
		arg1.emit(new Values(print));
		
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		arg0.declare(new Fields("write"));
	}

}
