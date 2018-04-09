package level3.kafka;

import level3.kafka.util.DataObject;
import level3.rest.engine.async.logger.Logger;
import level3.rest.engine.kafka.api.KafkaHandler;
import level3.rest.engine.kafka.api.MessagingAction;
import level3.rest.engine.kafka.api.MessagingConsumer;
import level3.rest.engine.kafka.api.MessagingProducer;
import level3.rest.engine.utils.PropertiesUtil;

public class KafkaConsumer implements MessagingAction {
	
	//private static final Logger LOG = Logger.getLogger(KafkaConsumer.class.getName(), KafkaConsumer.class);
	static String topic1 = "realtime-orange1";
	static String topic2 = "realtime-orange2";
	static MessagingConsumer mc0 =null;
	static MessagingConsumer mc1 =null;
	
	/*public KafkaConsumer(DataObject pDO) {
        this.dataObject = pDO;
    }*/

	public static void main(String[] args) {
		System.setProperty("catalina.base","P:\\Users\\Bejawada.santoshkuma\\apache-tomcat");
		System.out.println("Cataline base value consumer "+System.getProperty("catalina.base"));
    	System.setProperty("environment", "development");
        PropertiesUtil.initializeProperties();
        
		
		//final DataObject do1 = new DataObject("this is just", "This is great");
		MessagingAction ma1 = new KafkaConsumer();
		
        System.out.println("MCO in consumer");
        try {
         mc0 =  MessagingConsumer.startConsumer(topic1, ma1, "xyz");
         //mc1 =  MessagingConsumer.startConsumer(topic1, ma1, "abc");
         if(null!=mc0) {
        	 System.out.println("MCO in consumer not null");
        	 //LOG.info("MCO in consumer not null");
         }
         /*if(null!=mc1) {
        	 System.out.println("MC1 in consumer not null");
        	 //LOG.info("MCO in consumer not null");
         }*/
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	System.out.println("finally of consumer");
        	//mc0.close();
        }
        

	}

	public void onMessage(Object obj) {
		System.out.println("inside onMessage in consumer");
		
		if(obj instanceof DataObject) {
			if(obj != null) {
				String id = (String) ((DataObject) obj).getObjValue1();
            System.out.println("Message : " + obj);
            //LOG.info("message :" + obj);
            ConsumerProducer(obj);
            mc0.close();
            
            //consumer producer start
            
          	//consumer producer end
          	 
           
			}
			else {
				//LOG.info("no output");
			}
            
        }
		
	}
	
	public void ConsumerProducer (Object obj) {
		MessagingProducer mp =null;
		String id = (String) ((DataObject) obj).getObjValue1();
		String response = "Response "
				+(String) ((DataObject) obj).getObjValue2();
		
     	 try {
       DataObject do1 = new DataObject(id, response);
       
       PropertiesUtil.initializeProperties();
       
       KafkaHandler.createTopic(topic2);
       
  		System.out.println("before producing in consumer");
       mp = new MessagingProducer(topic2);
       System.out.println("before offer");
       mp.offer(do1);
       System.out.println("after offer");
       System.out.println("topic in consumer:" +topic2);
     	}catch (Exception e) {
            e.printStackTrace();
       }
        finally {
        
     	   //KafkaHandler.deleteTopic(topic2);
        mp.close();
        }
	}

}
