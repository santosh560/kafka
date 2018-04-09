package level3.kafka;

import java.util.UUID;

import level3.kafka.util.DataObject;
import level3.rest.engine.kafka.api.KafkaHandler;
import level3.rest.engine.kafka.api.MessagingAction;
import level3.rest.engine.kafka.api.MessagingConsumer;
import level3.rest.engine.kafka.api.MessagingProducer;
import level3.rest.engine.utils.PropertiesUtil;

public class KafkaProducer {
	//private static final Logger LOG = Logger.getLogger(KafkaProducer.class.getName(), KafkaProducer.class);
	static String topic1 = "realtime-orange1";
	static String topic2 = "realtime-orange2";
	static MessagingConsumer mc0 =null;
	
	public static void main(final String[] args) {
		System.setProperty("catalina.base","P:\\Users\\Bejawada.santoshkuma\\apache-tomcat");
    	System.out.println("Cataline base value producer "+System.getProperty("catalina.base"));
    	System.setProperty("environment", "development");
        PropertiesUtil.initializeProperties();
        
        KafkaHandler.createTopic(topic1);
        
        
   	 MessagingProducer mp =null;
   	 try {
   		 DataObject do1 = new DataObject(UUID.randomUUID().toString(), "This is first test");
   		 //DataObject do2 = new DataObject("2", "This is second test");
       
   		System.out.println("before producing in producer");
        mp = new MessagingProducer(topic1);

       mp.offer(do1);
       
       Thread.sleep(5000);
       //mp.offer(do2);
       
     //producer consumer start
       MessagingAction ma1 = new TestMessagingAction();
       
       System.out.println("MCO in producer");
       try {
        mc0 =  MessagingConsumer.startConsumer(topic2, ma1, "xyz");
        if(null!=mc0) {
       	 System.out.println("MCO in producer not null");
       	 //LOG.info("MCO in producer not null");
        }
       } catch (Exception e) {
       	e.printStackTrace();
       }
       finally {
       	//mc0.close();
       }
     //producer consumer end
       
       
      
       
       System.out.println("topic in producer:" +topic1);
       //LOG.info("topic in producer:" +topic);
  
           
       } catch (Exception e) {
        e.printStackTrace();
       }
       finally {
    	   System.out.println("finally of producer");
       /*mc0.close();*/
    	   KafkaHandler.deleteTopic(topic1);
    	   //KafkaHandler.deleteTopic(topic2);
       mp.close();
       //executorService.shutdown();
       
       
       
       
       
       }

	}
  

	private static class TestMessagingAction implements MessagingAction {
		
	

	public void onMessage(Object obj) {
		System.out.println("inside onMessage of producer");
		
		if(obj instanceof DataObject) {
			if(obj != null) {
            System.out.println("Response Message : " + obj);
            //LOG.info("Response message :" + obj);
            mc0.close();
			}
			else {
				//LOG.info("no output");
			}
            
        }
	}
	}


}
