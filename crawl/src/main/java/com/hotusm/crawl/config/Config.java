package com.hotusm.crawl.config;

public class Config {

	//�ؼ���
	public static String keyWords="������,���,�㷨";

	//������
	public static String domain="https://book.douban.com/";
	
	//����������ǰ׺  ����������κε�
	public static String prefix="https://book.douban.com/subject/";
	
	//��ѯ����·��
	public static String searchUrl="https://book.douban.com/tag/";
	
	//�ų���url��׺
	public static String excludeUrl="buylinks";
	
	//�������߳���   ���ͨ����������Ϊ�������Ե�
	public static int pserseThreadCount=20;
	//�������߳��� ���ͨ����������Ϊ�������Ե�
	public static int rankingThreadCount=20;
	//��Ҫ����������url��׺
	public static String needContinueParser="type=S";
	//��ȡ���ݵ�����
	public static int count=60;
	//�Ƿ�Ҫ������
	public static boolean isScale=true;
	
	public static String filePath="C://DoubanBookcrawl.xls";
	
	//�������� �����Ե��߳���
	static{

		if(isScale){
			
			int NCPU=Runtime.getRuntime().availableProcessors();
			//80%�Ĺ���
			
			
		}
	}
	
	
	
}
