package level3.kafka;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class KafkaExecutor implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
    	System.out.println("inside Consumer Execute");
    	KafkaConsumer.main(null);
		KafkaProducer.main(null);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Webapp shutdown.
    }

	
		
		/*ExecutorService executorService = Executors.newFixedThreadPool(10);

		try
		{
		  
		      System.out.println("Reading Information ");

		      
		      
		      

		      executorService.execute(
		        new Runnable()
		        {
		          public void run()
		          {        
		            KafkaConsumer.main(args);
		          }
		        }
		      );
		      //executorService.wait(100);
		   
		      executorService.execute(
				        new Runnable()
				        {
				          public void run()
				          {        
				            KafkaProducer.main(args);
				          }
				        }
				      );
		  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
		  executorService.shutdown();
		}*/

	

}
