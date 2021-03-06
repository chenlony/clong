package comb.kafka.example;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
/**
 * kafka生产端代码
 * @author CL
 *
 */
public class KafkaProducer {  
	  
	public static final String topic = "test";
	
    public static void main(String[] args) throws Exception {  
        Properties properties = new Properties();  
        properties.put("zookeeper.connect", "192.168.1.114:2181,192.168.1.115:2181,192.168.1.116:2181");	//声明zk  
        properties.put("serializer.class", StringEncoder.class.getName());  
        properties.put("metadata.broker.list", "192.168.1.114:9092");	// 声明kafka broker 
        properties.put("request.required.acks", "1");
        Producer producer = new Producer<Integer, String>(new ProducerConfig(properties));
        for(int i=0; i < 10; i++){//循环10次消息，发送10次消息
        	producer.send(new KeyedMessage<Integer, String>(topic, "hello kafka" + i)); 
        	System.out.println("send message: " + "hello kafka" + i);
        	TimeUnit.SECONDS.sleep(1);  
        }
        producer.close();
    }  
       
}  