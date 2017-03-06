package com.utils.task.base;

import java.io.IOException;

import javax.servlet.ServletException;

public interface TaskExcuteListener {
	public <T> void onTaskFinish(String[] dataString) throws ServletException, IOException, Exception;
}
