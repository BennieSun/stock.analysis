package com.utils.task.requeses;

import java.util.HashMap;
import java.util.Map;

import com.utils.DateFormatUtil;
import com.utils.HttpUtil;
import com.utils.task.base.TaskExcuteListener;

public abstract class BaseGetRequest extends BaseRequest {

	protected BaseGetRequest() {
	}

	public BaseGetRequest(String _url) {
		setFields(_url, null, null);
	}

	public BaseGetRequest(String _url, Map<String, String> _params,
			TaskExcuteListener _listener) {
		setFields(_url, _params, _listener);
	}

	public BaseGetRequest(String _url, Map<String, String> _params) {
		setFields(_url, _params, null);
	}

	@Override
	public void execute() {
		makeParams();
		System.out.println(DateFormatUtil.getNowTimeStr()+"::"+url);
		response = HttpUtil.executeGetRequest(url);
		try {
			parsResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void makeParams() {
		if (params != null) {
			if (!params.isEmpty()) {
				if (url.endsWith("/")) {
					url = url.substring(0, url.length() - 1);
				}
				url += "?";
				for (Map.Entry<String, String> entry : params.entrySet()) {
					url += entry.getKey() + "=" + entry.getValue() + "&";
				}
				url = url.substring(0, url.length() - 1);
			}
		}
	}

}
