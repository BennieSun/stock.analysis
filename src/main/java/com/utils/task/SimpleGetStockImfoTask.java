package com.utils.task;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.controller.PageCharts;
import com.utils.task.base.TaskExcuteListener;
import com.utils.task.requeses.BaseGetRequest;

@Component
public class SimpleGetStockImfoTask extends BaseGetRequest {
	
	private static Logger log = Logger.getLogger(SimpleGetStockImfoTask.class);
	
	public static SimpleGetStockImfoTask simpleGetStockImfoTask;
	
	public SimpleGetStockImfoTask(){}

	private static String GET_STOCK_INFO_URL = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx/JS.aspx?type=ct&st=(BalFlowMain)&sr=-1&p=1&ps=40000&token=STRTOKEN&cmd=C._AB&sty=DCFFITA&rt=47883631";
	//private ArrayList<StockDisplayDetailedEntity> filteredList;

	@Override
	protected void parsResponse(String _response) throws Exception {
		log.info(_response);
		if(null==_response || "".equals(_response) || "-".equals(_response)){
			log.info("parsResponse return response is null!");
			return;
		}
		String src = _response.substring(2, _response.length() - 2);
		String[] array = src.split(",\"");
		listener.onTaskFinish(array);
	}

//	public ArrayList<StockDisplayDetailedEntity> getFilteredList() {
//		return filteredList;
//	}

	public SimpleGetStockImfoTask(String token, TaskExcuteListener _listener) {
		url = GET_STOCK_INFO_URL.replace("STRTOKEN", token);
		//listener = _listener;
		//filteredList = new ArrayList<StockDisplayDetailedEntity>();
	}
	
	public void setTokenToUrl(String token){
		this.url = GET_STOCK_INFO_URL.replace("STRTOKEN", token);
	}
	
	public void setListener(TaskExcuteListener listener){
		this.listener = listener;
	}
}
