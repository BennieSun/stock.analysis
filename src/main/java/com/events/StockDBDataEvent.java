package com.events;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.controller.StockDBController;
import com.entitys.StockDetailedEntity;
import com.entitys.StockDisplayDetailedEntity;
import com.entitys.StockInfoEntity;
import com.service.StockDetailedService;
import com.service.StockInfoService;
import com.utils.DateFormatUtil;
import com.utils.task.SimpleGetStockImfoTask;
import com.utils.task.base.TaskExcuteListener;

@Component
public class StockDBDataEvent {
	private static Logger log = Logger.getLogger(StockDBController.class);
	
	@Autowired
	private StockInfoService stockInfoService;
	@Autowired
	private StockDetailedService stockDetailedService;
	@Autowired
	private SimpleGetStockImfoTask simpleGetStockImfoTask;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Scheduled(cron = "0 5,35 11,13,15,20 * * MON-FRI")
    public void saveAndUpdateStockDB() throws Exception {  
        Object obj = new Object(); 
        log.info("class StockDBDataEvent Method saveAndUpdateStockDB is Start.***"+DateFormatUtil.getNowTimeStr());
        synchronized (obj) {
			simpleGetStockImfoTask.setTokenToUrl("894050c76af8597a853f5b408b759f5d");
			simpleGetStockImfoTask.setListener(new TaskExcuteListener() {

				public <T> void onTaskFinish(String[] dataString)
						throws Exception {
					stringArray2EntityList(dataString, "saveDB");
				}
			});
			simpleGetStockImfoTask.execute();
		}
		log.info("class StockDBDataEvent Method saveAndUpdateStockDB is finish!***"+DateFormatUtil.getNowTimeStr());
    } 	
	
	/**
	 * 存储数据到DB
	 */
	public Object obj = new Object();
	
	public synchronized ArrayList<StockDisplayDetailedEntity> stringArray2EntityList(
			String[] source,String targe) throws Exception {
		ArrayList<StockDisplayDetailedEntity> entityList = new ArrayList<StockDisplayDetailedEntity>();
		dataToEntity(source, entityList, targe);
		return entityList;
	}

	public void dataToEntity(String[] source,
			ArrayList<StockDisplayDetailedEntity> entityList,String targe) throws Exception {
		
		
		//ExecutorService es = Executors.newScheduledThreadPool(1);
		
		for (int i = 0; i < source.length; i++) {
			
			log.info("******************count:"+ i);
			
			/**线程池处理**/
			threadPoolTaskExecutor.execute(new ForPoolExecutor(source, entityList, targe, i));
			log.info("threadPoolTaskExecutor.getActiveCount:"+ threadPoolTaskExecutor.getActiveCount());
			//threadPoolTaskExecutor.execute(ForPoolExecutor(source, entityList, targe, i));
		}
	}

	@SuppressWarnings("unused")
	private class ForPoolExecutor implements Runnable {
		private String[] source;
		private ArrayList<StockDisplayDetailedEntity> entityList;
		private String targe;
		private int i;
		
		public ForPoolExecutor(){
			
		}
		public ForPoolExecutor(String[] source,
				ArrayList<StockDisplayDetailedEntity> entityList, String targe,
				int i){
			this.source = source;
			this.entityList = entityList;
			this.targe = targe;
			this.i = i;
			
			this.run();
		}
		
		
		public void run() {
			final int count = i; 
			
			String temp = source[i];
			temp = temp.replace("\"", "");
			String[] tempArray = temp.split(",");
			final StockDisplayDetailedEntity tempEntity = new StockDisplayDetailedEntity();
			tempEntity.setNum(i+1);
			tempEntity.setIndex(tempArray[0]);
			tempEntity.setCode(tempArray[1]);
			tempEntity.setName(tempArray[2]);
			//tempEntity.setLatestPrice(tempArray[3]);
			
			if(tempArray[3].length()>1){
				if(tempArray[3].equals("-")){
					tempEntity.setLatestPrice("0.0");
				}
				tempEntity.setLatestPrice(tempArray[3]);
			}else{
				tempEntity.setLatestPrice("0.0");
			}
			
			if(tempArray[4].length()>1){
				if(tempArray[4].equals("-")){
					tempEntity.setUpAndDown("0.0");
				}
				tempEntity.setUpAndDown(tempArray[4]);
			}else{
				tempEntity.setUpAndDown("0.0");
			}
			
			if(tempArray[5].length()>1){
				tempEntity.setMainForceBillIncome(tempArray[5]);
			}else{
				tempEntity.setMainForceBillIncome("0.0");
			}
			
			if(tempArray[6].length()>1){
				tempEntity.setMainForceBillIncomePercentage(tempArray[6]);
			}else{
				tempEntity.setMainForceBillIncomePercentage("0.0");
			}
			
			if(tempArray[7].length()>1){
				tempEntity.setHugeBillIncome(tempArray[7]);
			}else{
				tempEntity.setHugeBillIncome("0.0");
			}
			
			if(tempArray[8].length()>1){
				tempEntity.setHugeBillIncomePercentage(tempArray[8]);
			}else{
				tempEntity.setHugeBillIncomePercentage("0.0");
			}
			
			if(tempArray[9].length()>1){
				tempEntity.setBigBillIncome(tempArray[9]);
			}else{
				tempEntity.setBigBillIncome("0.0");
			}
			
			if(tempArray[10].length()>1){
				tempEntity.setBigBillIncomePercentage(tempArray[10]);
			}else{
				tempEntity.setBigBillIncomePercentage("0.0");
			}
			
			if(tempArray[11].length()>1){
				tempEntity.setMediumBillIncome(tempArray[11]);
			}else{
				tempEntity.setMediumBillIncome("0.0");
			}
			
			if(tempArray[12].length()>1){
				tempEntity.setMediumBillIncomePercentage(tempArray[12]);
			}else{
				tempEntity.setMediumBillIncomePercentage("0.0");
			}
			
			if(tempArray[13].length()>1){
				tempEntity.setSmallBillIncome(tempArray[13]);
			}else{
				tempEntity.setSmallBillIncome("0.0");
			}
			
			if(tempArray[14].length()>1){
				tempEntity.setSmallBillIncomePercentage(tempArray[14]);
			}else{
				tempEntity.setSmallBillIncomePercentage("0.0");
			}
			
			if(null!=targe&&targe.equals("saveDB")){
				//new ThreadPoolExecutor(100, maximumPoolSize, keepAliveTime, Hour, workQueue);
				//es.execute(new Runnable() {
					//public void run() {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						//log.info(count +"@@@@@@"+ tempEntity);
						try {
							saveStock(tempEntity);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//}
				//});
			}
			entityList.add(tempEntity);
		}

			
	}
	
	
	private void saveStock(StockDisplayDetailedEntity sddEntity) throws Exception{
		
		StockInfoEntity stockInfoEntity = new StockInfoEntity();
		StockDetailedEntity stockDetailedEntity = new StockDetailedEntity();
		
		stockInfoEntity.setStockCode(sddEntity.getCode());
		stockInfoEntity.setStockName(sddEntity.getName());
		
		int infoCount = 1;
		/**获取当前info id**/
		String stockInfoCountByIdCount = stockInfoService.getInfoCountById(sddEntity.getCode());
		
		if(stockInfoCountByIdCount.equals("0")){/**id 不存在，进行保存info**/
			infoCount= stockInfoService.saveStockInfo(stockInfoEntity);
		}else if(infoCount==1){/**已保存info,查询存入的info ID**/
			stockDetailedEntity.setStockId(stockInfoService.getStockInfoId(sddEntity.getCode()));
		}else{
			log.info("stockInfoCountByIdCount 状态不符合");
			return;
		}
		
		String stockDetailedCountByIDAndCurrentTime = stockDetailedService.findDetailedCountByIDAndCurrentTime(stockDetailedEntity.getStockId());
		stockDetailedEntity.setLatestPrice(sddEntity.getLatestPrice());
		stockDetailedEntity.setUpDown(sddEntity.getUpAndDown());
		stockDetailedEntity.setMainForceBillIncome(sddEntity.getMainForceBillIncome());
		stockDetailedEntity.setMainForceBillIncomePercentage(sddEntity.getMainForceBillIncomePercentage());
		stockDetailedEntity.setHugeBillIncome(sddEntity.getHugeBillIncome());
		stockDetailedEntity.setHugeBillIncomePercentage(sddEntity.getHugeBillIncomePercentage());
		stockDetailedEntity.setBigBillIncome(sddEntity.getBigBillIncome());
		stockDetailedEntity.setBigBillIncomePercentage(sddEntity.getBigBillIncomePercentage());
		stockDetailedEntity.setMediumBillIncome(sddEntity.getMediumBillIncomePercentage());
		stockDetailedEntity.setMediumBillIncomePercentage(sddEntity.getMediumBillIncomePercentage());
		stockDetailedEntity.setSmallBillIncome(sddEntity.getSmallBillIncomePercentage());
		stockDetailedEntity.setSmallBillIncomePercentage(sddEntity.getSmallBillIncomePercentage());
		stockDetailedEntity.setImportTime(DateFormatUtil.stringToDate(DateFormatUtil.getNowTimeStr(), DateFormatUtil.DATE_DEFAULT_FORMAT));
		int detailedCount = 0;
		if(stockDetailedCountByIDAndCurrentTime.equals("0")){/**不存在进行插入Detailed**/
			detailedCount= stockDetailedService.saveStockDetailed(stockDetailedEntity);
		}else if(stockDetailedCountByIDAndCurrentTime.equals("1")){/**进行更新**/
			detailedCount = stockDetailedService.updateStockDetailed(stockDetailedEntity,DateFormatUtil.getNowTimeStr());
		}else{
			log.info("stockDetailedList is null."+DateFormatUtil.getNowTimeStr() + ":::::"+stockDetailedEntity.getStockId());
			throw new Exception("存在重复数据！");
		}
	}
	
	
	public static void main(String[] args) {
		String xx = "-1";
		if(xx.equals("-")){
			System.out.println("--------");
		}
		System.out.println(xx.length());
		
	}
}