package com.sds.icto.emaillist.action;

import com.sds.icto.web.Action;

public class ActionFactory {

	private static ActionFactory instance;
	static{
		instance = new ActionFactory();
	}
	private ActionFactory(){
		
	}
	
	public static ActionFactory getInstance(){
		if(instance == null){
			instance = new ActionFactory();
		}
		return instance;
	}
	
	public Action getAction(String action){
		Action act = null;
		if ("form".equals(action)) {
			act = new formAction();
		} else if ("insert".equals(action)) {
			act = new insertAction();
		} else {
			act = new IndexAction();
		}
		return act;
	}
}
