package com.storm;

import java.io.FileWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class WriteBolt extends BaseBasicBolt{
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(WriteBolt.class);
	
	private FileWriter writer ;
	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		//获取上一个组件所声明的Field
				String text = arg0.getStringByField("write");
				try {
					if(writer == null){
						if(System.getProperty("os.name").equals("Windows 10")){
							writer = new FileWriter("D:\\099_test\\" + this);
						} else if(System.getProperty("os.name").equals("Windows 8.1")){
							writer = new FileWriter("D:\\099_test\\" + this);
						} else if(System.getProperty("os.name").equals("Windows 7")){
							writer = new FileWriter("D:\\099_test\\" + this);
						} else if(System.getProperty("os.name").equals("Linux")){
							System.out.println("----:" + System.getProperty("os.name"));
							writer = new FileWriter("/usr/local/temp/" + this);
						}
					}
					log.info("【write】： 写入文件");
					writer.write(text);
					writer.write("\n");
					writer.flush();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

}
