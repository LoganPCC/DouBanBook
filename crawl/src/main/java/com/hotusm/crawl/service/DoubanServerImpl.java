package com.hotusm.crawl.service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.crawl.config.Config;
import com.hotusm.crawl.entity.Book;
import com.hotusm.crawl.task.MonitorPoolTask;
import com.hotusm.crawl.task.ParserTask;
import com.hotusm.crawl.task.RankingTask;

public class DoubanServerImpl implements DoubanServer{
	
	   /**
     * ������ҳ�̳߳�
     */
    private ThreadPoolExecutor parseThreadExecutor;
    /**
     * �������̳߳�
     */
    private ThreadPoolExecutor rankingThreadExecutor;
    
    //��������  ����û������Ĺ�������
    private LinkedBlockingQueue<Book> parsedBook=new LinkedBlockingQueue<Book>(500);
    
    
    public DoubanServerImpl() {
    	initThreadPool();
    	startMonitor();
	}
    
	public void startCrawl(String keywords) {
		String[] split = keywords.split(",");
		for(String str:split){
			parseThreadExecutor.execute(new ParserTask(parsedBook, Config.searchUrl+str+"?"+Config.needContinueParser,parseThreadExecutor));
		}
		for(int i=0;i<Config.rankingThreadCount;i++){
			rankingThreadExecutor.execute(new RankingTask(parsedBook));
		}
	}
	
	private void initThreadPool(){
        parseThreadExecutor = new ThreadPoolExecutor(Config.pserseThreadCount, Config.pserseThreadCount,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        //������߳�
        rankingThreadExecutor = new ThreadPoolExecutor(Config.rankingThreadCount,
                Config.rankingThreadCount,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
	
	//�����߳�ȥ�����̳߳ص�״̬
	private void startMonitor(){
		
		new Thread(new MonitorPoolTask(parseThreadExecutor, "parseThreadExecutor")).start();;
		new Thread(new MonitorPoolTask(rankingThreadExecutor, "rankingThreadExecutor")).start();
		
	}
	
}
