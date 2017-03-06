package com.utils.task.requeses;

import java.util.Map;

import com.utils.task.base.TaskExcuteListener;


public abstract class BaseRequest {
	protected String response = "";
	protected String url = "";
	protected Map<String, String> params = null;
	protected TaskExcuteListener listener;

	public abstract void execute();

	protected abstract void makeParams();

	public TaskExcuteListener getListener() {
		return listener;
	}

	public void setListener(TaskExcuteListener listener) {
		this.listener = listener;
	}

	protected abstract void parsResponse(String _response) throws Exception;

	protected void setFields(String _url, Map<String, String> _params,
			TaskExcuteListener _listener) {
		url = _url;
		params = _params;
		listener = _listener;
	}

}
