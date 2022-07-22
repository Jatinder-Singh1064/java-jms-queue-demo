package kakf;

import javax.jms.*;  
import javax.naming.InitialContext;  
  
public class MyReceiver {  
    public static void main(String[] args) {  
        try{  
            //1) Create and start connection  
            InitialContext ctx=new InitialContext();  
            //QueueConnectionFactory f=(QueueConnectionFactory)ctx.lookup("myQueueConnectionFactory"); 
            QueueConnectionFactory f=(QueueConnectionFactory)ctx.lookup("jatinder_jndi"); 
            QueueConnection con=f.createQueueConnection();  
            con.start();  
            //2) create Queue session  
            QueueSession ses=con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);  
            //3) get the Queue object  
           // Queue t=(Queue)ctx.lookup("myQueue"); 
            Queue t=(Queue)ctx.lookup("jatinder_dest_jndi"); 
            //4)create QueueReceiver  
            QueueReceiver receiver=ses.createReceiver(t);  
              
            //5) create listener object  
            MyListener listener=new MyListener();  
              
            //6) register the listener object with receiver  
            receiver.setMessageListener(listener);  
              
            System.out.println("Receiver1 is ready, waiting for messages...");  
            System.out.println("press Ctrl+c to shutdown...");  
            while(true){                  
                Thread.sleep(1000);  
            }  
        }catch(Exception e){System.out.println(e);}  
    }  
  
}  