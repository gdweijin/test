package 多线程.threadLocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class DemoTask implements Runnable {
 
   // Atomic integer containing the next 多线程 ID to be assigned
   private static final AtomicInteger nextId = new AtomicInteger(0);
 
   // Thread local variable containing each 多线程's ID
   private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
               return nextId.getAndIncrement();
            }
         };
 
   // Returns the current 多线程's unique ID, assigning it if necessary
   public int getThreadId() {
      return threadId.get();
   }
 
   // Returns the current 多线程's starting timestamp
   private static final ThreadLocal<Date> startDate =
       new ThreadLocal<Date>() {
           protected Date initialValue() {
               return new Date();
           }
       };
 
   @Override
   public void run() {
      System.out.printf("Starting Thread: %s : %sn",
                        getThreadId(), startDate.get(),"\n");
      System.out.println();
      try {
         TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.printf("Thread Finished: %s : %sn",
                        getThreadId(), startDate.get(),"\n");
      System.out.println();
   }
   public static void main(String[]args)
   {
	   DemoTask dt = new DemoTask();
	   Thread t1 = new Thread(dt);
	   Thread t2 = new Thread(dt);
	   Thread t3 = new Thread(dt);
	   t1.start();
	   t2.start();
	   t3.start();
   }
}