package com.hotusm.crawl.task;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.crawl.util.ThreadMonitor;

/**
 * ����̳߳ص�״̬
 * @author Hotusm  <br/>
 * @date 2016��11��18��   <br/>
 * @description
 */
public class MonitorPoolTask implements Runnable{

	private ThreadPoolExecutor needMonitorPool;
	private String monitorName;
	
	public MonitorPoolTask(ThreadPoolExecutor needMonitorPo,String monitorName) {
		this.needMonitorPool=needMonitorPo;
		this.monitorName=monitorName;
	}
	public void run() {
		
		while(ThreadMonitor.isMonitor){
			
           System.out.println("�����:"+this.monitorName+"  "+
        		   String.format("[monitor] [%d/%d] Active: %d, Completed: %d, queueSize: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                           this.needMonitorPool.getPoolSize(),
                           this.needMonitorPool.getCorePoolSize(),
                           this.needMonitorPool.getActiveCount(),
                           this.needMonitorPool.getCompletedTaskCount(),
                           this.needMonitorPool.getQueue().size(),
                           this.needMonitorPool.getTaskCount(),
                           this.needMonitorPool.isShutdown(),
                           this.needMonitorPool.isTerminated())
        		   );
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				//e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

}
