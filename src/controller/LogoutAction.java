package controller;

import com.opensymphony.xwork2.ActionContext;

public class LogoutAction {
	public String execute() throws Exception{
		System.out.println("LogoutAction.java Log: get in the logout action");
		ActionContext.getContext().getSession().put("isRegister", "0");
		return "goback";
	}
}
