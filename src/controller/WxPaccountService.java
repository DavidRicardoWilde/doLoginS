package controller;

import model.UserBean;
import model.WxAccountBean;
import model.WxAccountDAO;
import model.WxAccountDAOInf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WxPaccountService  extends ActionSupport{
	private WxAccountDAOInf wxAccountDAOInf;
	private InputStream inputStream;
	//private WxAccountBean wxAccountBean;
	
	private int fid;
	private String paccount;
	private String pnick;
	private String pname;
	private String url;
	private String token;
	private String appid;
	private String appsecret;
	private String access_token;
	private String expires_in;
	private String flast;
	private int enabled;
	private String funct;
	
//	public void setWxAccountBean(WxAccountBean wxAccountBean){
//		this.wxAccountBean=wxAccountBean;
//	}
//	public WxAccountBean getWxaccountBean(){
//		return wxAccountBean;
//	}
		
	public void setFunct(String funct){
		this.funct=funct;
	}
	public String getFunct(){
		return funct;
	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setPaccount(String paccount){
		this.paccount=paccount;
	}
	public String getPaccount(){
		return paccount;
	}
	
	public void setPnick(String pnick){
		this.pnick=pnick;
	}
	public String getPnick(){
		return pnick;
	}
	
	public void setPname(String pname){
		this.pname=pname;
	}
	public String getPname(){
		return pname;
	}
	
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	
	public void setToken(String token){
		this.token=token;
	}
	public String getToken(){
		return token;
	}
	
	public void setAppid(String appid){
		this.appid=appid;
	}
	public String getAppid(){
		return appid;
	}
	
	public void setAppsecret(String appsecret){
		this.appsecret=appsecret;
	}
	public String getAppsecret(){
		return appsecret;
	}
	
	public void setAccess_token(String access_token){
		this.access_token=access_token;
	}
	public String getAccess_token(){
		return access_token;
	}
	
	public void setExpires_in(String expires_in){
		this.expires_in=expires_in;
	}
	public String getExpires_in(){
		return expires_in;
	}
	
	public void setFlast(String flast){
		this.flast=flast;
	}
	public String getFlast(){
		return flast;
	}
	
	public void setEnabled(int enabled){
		this.enabled=enabled;
	}
	public int getEnabled(){
		return enabled;
	}
	
	public void setWxAccountDAOInf(WxAccountDAOInf wxAccountDAOInf){
		this.wxAccountDAOInf=wxAccountDAOInf;
	}
	public WxAccountDAOInf getWxAccountDAOInf(){
		return wxAccountDAOInf;
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream=inputStream;
	}
	public InputStream getInputStream(){
		return inputStream;
	} 
	
	public String execute() throws Exception{
		System.out.println("This is WxPaccountService");
//		UserBean user_wxPaccount = null;
//		System.out.println(user_wxPaccount.getFname());
		
//		ActionContext.getContext().getSession().get("username"); 获取session如何转为string
//		String username=null;
//		String password=null;
//		username=(String)ActionContext.getContext().getSession().get("username");
//		System.out.println("username = "+username);
//		password=(String)ActionContext.getContext().getSession().get("password");
//		
//		UserBean user_wxPaccount = null;
//		user_wxPaccount.setFname(username);
		
		return "wxpaccount";
	}
	
	public String wxJson(){
		//System.out.println("wxpaccount servie wxjson");
		
		WxAccountBean wx2=new WxAccountBean();
		
		try {
			
			List list = wxAccountDAOInf.queryList();
			
			//String inputStreamString = "{\"total\":1,\"rows\":[{\"fid\":1,\"paccount\":\"gh_b3c855dd9391\",\"pnick\":\"yyx-MIG\",\"pname\":\"翼云兴软件\",\"url\":\"http://www.iammig.com/weixin\",\"token\":\"yiyunxing\",\"appid\":\"wx4ad1260c094a0e7b\",\"appsecret\":\"f15f6d8078a59fd5537e0e0451bf014e\",\"enabled\":\"1\"},{\"fid\":1,\"paccount\":\"gh_b3c855dd9391\",\"pnick\":\"yyx-MIG\",\"pname\":\"翼云兴软件\",\"url\":\"http://www.iammig.com/weixin\",\"token\":\"yiyunxing\",\"appid\":\"wx4ad1260c094a0e7b\",\"appsecret\":\"f15f6d8078a59fd5537e0e0451bf014e\",\"enabled\":\"1\"}]}";
			
			Iterator testItertor =null;
			String inputStreamString=null;//保存数据库内容
			
			String inputStreamData="{\"total\":1,\"rows\":[";
			testItertor=list.iterator();
			
			if(list.size()<=0 || list == null){
				System.out.println("list null");
			}
			while(testItertor.hasNext()){
				if(inputStreamString==null){
					wx2=(WxAccountBean)testItertor.next();
					inputStreamString = "{\"fid\":"+wx2.getFid()+",\"paccount\":\""+wx2.getPaccount()+"\",\"pnick\":\""+wx2.getPnick()+"\",\"pname\":\""+wx2.getPname()+"\",\"url\":\""+wx2.getUrl()+"\",\"token\":\""+wx2.getToken()+"\",\"appid\":\""+wx2.getAppid()+"\",\"appsecret\":\""+wx2.getAppsecret()+"\",\"enabled\":\""+wx2.getEnabled()+"\"}";
				}else{
					wx2=(WxAccountBean)testItertor.next();
					inputStreamString = inputStreamString+",{\"fid\":"+wx2.getFid()+",\"paccount\":\""+wx2.getPaccount()+"\",\"pnick\":\""+wx2.getPnick()+"\",\"pname\":\""+wx2.getPname()+"\",\"url\":\""+wx2.getUrl()+"\",\"token\":\""+wx2.getToken()+"\",\"appid\":\""+wx2.getAppid()+"\",\"appsecret\":\""+wx2.getAppsecret()+"\",\"enabled\":\""+wx2.getEnabled()+"\"}";
			
				}
			}
			
			inputStreamData=inputStreamData+inputStreamString+"]}";
			
			System.out.println("inputStreamData = "+inputStreamData);
			
			//System.out.println(inputStreamString);
		
			inputStream = new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("This is WxPaccountService :"+inputStream);
		
		return "success";
	}
	
	public String switchData() throws Exception{
		System.out.println("function is: "+funct);
		switch(funct){
		case "add":
			addData();
			break;
		case "mod":
			modData();
			break;																			
		case "del":
			delData();
			break;
		case "assistDevGrp":
			//getDataForDevGrp();
		default:
			break;
		}
		
		return "success";
	}
	
	public void addData() throws Exception{
//		System.out.println("this is addData function");
		//System.out.println("appid = "+appid);
	
		WxAccountBean wxAccountBean=new WxAccountBean();
		wxAccountBean.setAppid(appid); //必须的
		wxAccountBean.setPnick(pnick);//微信
		wxAccountBean.setPname(pname);
		wxAccountBean.setAppsecret(appsecret);
		wxAccountBean.setPaccount(paccount);
//		System.out.println("pnick = "+wxAccountBean.getPnick());
//		System.out.println("pname = "+wxAccountBean.getPname());
//		System.out.println("appsecret = "+wxAccountBean.getAppsecret());
//		System.out.println("paccount = "+wxAccountBean.getPaccount());
//		System.out.println("appid = "+wxAccountBean.getAppid());
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		wxAccountDAOInf.addObjt(wxAccountBean);
		//return "success";
	}
	
	public void modData() throws Exception{
		//System.out.println("this is modData function");
		//WxAccountBean accountBean;
//		System.out.println("appid = "+appid);
//		wxAccountBean.setAppid(appid);
//		System.out.println("appid = "+wxAccountBean.getAppid());
		//System.out.println("fid = "+fid);
		WxAccountBean wxAccountBean2=new WxAccountBean();
		wxAccountBean2.setFid(fid);
		wxAccountBean2.setAppid(appid); //必须的
		wxAccountBean2.setPnick(pnick);//微信
		wxAccountBean2.setPname(pname);
		wxAccountBean2.setAppsecret(appsecret);
		wxAccountBean2.setPaccount(paccount);
		wxAccountBean2.setEnabled(enabled);
		
//		System.out.println("pnick = "+wxAccountBean2.getPnick());
//		System.out.println("pname = "+wxAccountBean2.getPname());
//		System.out.println("appsecret = "+wxAccountBean2.getAppsecret());
//		System.out.println("paccount = "+wxAccountBean2.getPaccount());
//		System.out.println("appid = "+wxAccountBean2.getAppid());
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		wxAccountDAOInf.modObjt(wxAccountBean2);
	}
	
	public void delData() throws Exception{
		//System.out.println("this is delData function");
		WxAccountBean wxAccountBean2=new WxAccountBean();
		wxAccountBean2.setFid(fid);
		//System.out.println("fid = "+wxAccountBean2.getFid());
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		wxAccountDAOInf.delObjt(wxAccountBean2);
	}
	
	public String checkDataForDevGrp() throws Exception{
		System.out.println("获取tree list数据");
		try{
			WxAccountBean wx2=new WxAccountBean();
			
			List list = wxAccountDAOInf.queryList();
//			Iterator testItertor =null;
			String inputStreamString=null;//保存数据库内容
			
			String inputStreamData="[";
			Iterator testItertor =null;
			testItertor=list.iterator();
			
			if(list.size()<=0 || list == null){
				System.out.println("list null");
			}
			while(testItertor.hasNext()){
				if(inputStreamString==null){
					wx2=(WxAccountBean)testItertor.next();
					inputStreamString = "{\"fid\":"+wx2.getFid()+",\"paccount\":\""+wx2.getPaccount()+"\",\"pnick\":\""+wx2.getPnick()+"\",\"pname\":\""+wx2.getPname()+"\",\"url\":\""+wx2.getUrl()+"\",\"token\":\""+wx2.getToken()+"\",\"appid\":\""+wx2.getAppid()+"\",\"appsecret\":\""+wx2.getAppsecret()+"\",\"enabled\":\""+wx2.getEnabled()+"\"}";
				}else{
					wx2=(WxAccountBean)testItertor.next();
					inputStreamString = inputStreamString+",{\"fid\":"+wx2.getFid()+",\"paccount\":\""+wx2.getPaccount()+"\",\"pnick\":\""+wx2.getPnick()+"\",\"pname\":\""+wx2.getPname()+"\",\"url\":\""+wx2.getUrl()+"\",\"token\":\""+wx2.getToken()+"\",\"appid\":\""+wx2.getAppid()+"\",\"appsecret\":\""+wx2.getAppsecret()+"\",\"enabled\":\""+wx2.getEnabled()+"\"}";
			
				}
			}
			
			inputStreamData=inputStreamData+inputStreamString+"]";
			
			System.out.println("inputStreamData = "+inputStreamData);
			
			//System.out.println(inputStreamString);
		
			inputStream = new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
		}
		catch(Exception e){
			System.out.println("UserService test: "+e);
			return "success";
		}
		
		return "success";
	}
}
