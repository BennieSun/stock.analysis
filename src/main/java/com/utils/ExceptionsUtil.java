package com.utils;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ExceptionsUtil extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 326924084839803960L;

	private Throwable throwable;

	/**
	 * 
	 * @param
	 * @return
	 */
	public ExceptionsUtil() {
		super();
	}

	/**
	 * 
	 * @param msg
	 * @return
	 */
	public ExceptionsUtil(String msg) {
		super(msg);

	}

	/**
	 * 
	 * @param msg
	 * @param throwable
	 * @return
	 */
	public ExceptionsUtil(String msg, Throwable throwable) {
		super(msg,throwable);
	}

	/**
	 * 
	 * @param
	 */
	public Throwable getException() {
		throwable = super.getCause();
		return throwable;
	}

	/**
	 * 
	 * @param
	 */
	public void printStackTrace() {
		super.printStackTrace();
	}

	/**
	 * 
	 * @param printStream
	 */
	public void printStackTrace(PrintStream printStream) {
		super.printStackTrace(printStream);
	}

	/**
	 * 
	 * @param printWriter
	 */
	public void printStackTrace(PrintWriter printWriter) {
		super.printStackTrace(printWriter);

	}
}
