//Ïà¹ØµÄÓÃ»§²Ù×÷ ÈçµÇÂ½ ÍË³öµÈµÈ
//check user name
package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;




import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.UserDAO;
import model.UserBean;

public class UserService extends ActionSupport{
	private String username;
	private String password;
	
	private InputStream inputStream;
	
	private UserDAO userDAO;
	
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}    
	
	public void setUserDAO(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	public UserDAO getUserDAO(){
		return userDAO;
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream=inputStream;
	}
	public InputStream getInputStream(){
		return inputStream;
	} 
	
	public String ajaxLogin() throws Exception{
		try{
			System.out.println("UserService.java Test: UserService is strat to run");
			
			//Test code 27/11
			System.out.println("fname = "+username);
			System.out.println("password = "+password);
			
			UserBean user_userAjaxLogin = new UserBean();
			user_userAjaxLogin.setFname(username);
			user_userAjaxLogin.setFpassword(password);
			
			String string_loginInformation;
			string_loginInformation = userDAO.query(user_userAjaxLogin);
			System.out.println(string_loginInformation);
			
			if(string_loginInformation.equals("success")){
				inputStream=new ByteArrayInputStream("{\"success\":1,\"errMsg\":\"success\"}".getBytes("UTF-8"));
			}else if(string_loginInformation.equals("username_false")){
				inputStream=new ByteArrayInputStream("{\"success\":0,\"errMsg\":\"用户名错误\"}".getBytes("UTF-8"));
			}else{
				inputStream=new ByteArrayInputStream("{\"success\":2,\"errMsg\":\"密码错误\"}".getBytes("UTF-8"));
			}
			System.out.println("ooooooooooooooo");
			return "success";
		}
		catch(Exception e){
			System.out.println("UserService test: "+e);
			return "success";
		}
	}
	
	public String login(){
		System.out.println("this is a login funtion Test");
		Map<String, Object> mapSession;
		ActionContext.getContext().getSession().put("isRegister", "0");
		
		ActionContext.getContext().getSession().put("isRegister", "100");
		ActionContext.getContext().getSession().put("username", username);
		return "success";
	}
	
	public String wx_account() throws Exception{
		return "wx_account";
	}
}