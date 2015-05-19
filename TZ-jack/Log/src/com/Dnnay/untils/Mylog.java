package com.Dnnay.untils;

import org.apache.commons.logging.Log;



public  class Mylog implements Log{

	static boolean bug;
	@Override
	public void debug(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(Object arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void fatal(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Object arg0) {
		// TODO Auto-generated method stub
		
		if(bug){
			//收集日志
		}
		
	}

	@Override
	public void info(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trace(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

}
