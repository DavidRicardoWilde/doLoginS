package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import model.BaseModulesBean;
import model.BaseModulesDAOInf;

import com.opensymphony.xwork2.ActionSupport;

public class BaseModulesService extends ActionSupport{
	private BaseModulesDAOInf baseModulesDAOInf;
	private InputStream inputStream;
	
	//private BaseModulesBean baseModulesBean;
	
	private int fid;
	private String modulename;
	private String modulememo;
	private String funct;
	
	public void setFid(int fid){
		System.out.println("Test fid");
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setFunct(String funct){
		System.out.println("Test funct");
		this.funct=funct;
	}
	public String getFunct(){
		return funct;
	}
	
	public void setBaseModulesDAOInf(BaseModulesDAOInf baseModulesDAOInf){
		this.baseModulesDAOInf = baseModulesDAOInf;
	}
	public BaseModulesDAOInf setBaseModulesDAOInf(){
		return baseModulesDAOInf;
	}
	
	public void setModulename(String modulename){
		System.out.println("Test modulename");
		this.modulename=modulename;
	}
	public String getModulename(){
		return modulename;
	}
	
	public void setModulememo(String modulememo){
		this.modulememo=modulememo;
	}
	public String getModulememo(){
		return modulememo;
	}
//	
//	public void setBaseModulesBean(BaseModulesBean baseModulesBean){
//		this.baseModulesBean=baseModulesBean;
//	}
//	public BaseModulesBean getBaseModulesBean(){
//		return baseModulesBean;
//	}
//	
	
	
	public String execute() throws Exception{
		System.out.println("This is BaseModulesService Test");
		//showPage();
		return "baseModules";
	}
	
	public String switchModules() throws Exception{
		System.out.println("function is "+funct);
		switch(funct){
		case "add":
			System.out.println("funct = "+funct);
			addData();
			break;
		case "mod":
			modData();
			break;
		case "del":
			delData();
			break;
		case "showPage":
			showPage();
			break;
		case "assist":
		assitTest();
		break;
		default:
			break;
		}
		
		return "success";
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream=inputStream;
	}
	public InputStream getInputStream(){
		return inputStream;
	}
	
	public void addData() throws Exception{
		System.out.println("This is addData function");
		BaseModulesBean baseModulesBean2 = new BaseModulesBean();
		//baseModulesBean2.setFid(fid);
		baseModulesBean2.setModulename(modulename);
		baseModulesBean2.setModulememo(modulememo);
		
		//System.out.println("fid = "+baseModulesBean2.getFid());
		System.out.println("modulename = "+baseModulesBean2.getModulename());
		System.out.println("modulememo = "+baseModulesBean2.getModulememo());
		
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		baseModulesDAOInf.addObjt(baseModulesBean2);
	}
	
	public void modData() throws Exception{
		BaseModulesBean baseModulesBean2 = new BaseModulesBean();
		baseModulesBean2.setFid(fid);
		baseModulesBean2.setModulename(modulename);
		baseModulesBean2.setModulememo(modulememo);
		
		System.out.println("fid = "+baseModulesBean2.getFid());
		System.out.println("modulename = "+baseModulesBean2.getModulename());
		System.out.println("modulememo = "+baseModulesBean2.getModulememo());
		
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		baseModulesDAOInf.modObjt(baseModulesBean2);
	}
	
	public void delData() throws Exception{
		System.out.println("fid ="+fid);
		BaseModulesBean baseModulesBean2 = new BaseModulesBean();
		baseModulesBean2.setFid(fid);
		
		System.out.println("fid = "+baseModulesBean2.getFid());
		
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		baseModulesDAOInf.delObjt(baseModulesBean2);
	}
	
	public String showPage() throws UnsupportedEncodingException{
		System.out.println("This is showpage function");
		BaseModulesBean baseModulesBean2 = new BaseModulesBean();
		
		List list = baseModulesDAOInf.queryList();
		
		String inputStreamString=null;//保存数据库内容
		String inputStreamData="{\"total\":1,\"rows\":[";
		
		//System.out.println("queryList1111111");
		Iterator testItertor =null;	
		testItertor=list.iterator();
		//System.out.println("queryList222222");
		
		if(list.size()<=0 || list == null){
			System.out.println("list null");
		}
		//System.out.println("queryList333333");
		
		while(testItertor.hasNext()){
			if(inputStreamString==null){
				//System.out.println("queryList44444444");
				baseModulesBean2=(BaseModulesBean)testItertor.next();
				inputStreamString="{\"fid\":"+baseModulesBean2.getFid()+",\"modulename\":\""+baseModulesBean2.getModulename()+"\",\"modulememo\":\""+baseModulesBean2.getModulememo()+"\"}";
			}else{
				baseModulesBean2=(BaseModulesBean)testItertor.next();
				inputStreamString=inputStreamString + ",{\"fid\":"+baseModulesBean2.getFid()+",\"modulename\":\""+baseModulesBean2.getModulename()+"\",\"modulememo\":\""+baseModulesBean2.getModulememo()+"\"}";
			}
		}
		inputStreamData=inputStreamData+inputStreamString+"]}";
		
		System.out.println("inputStreamData = "+inputStreamData);
		
		inputStream = new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
		
		return "success";
	}
	
	public String assitTest() throws Exception{
		System.out.println("This is assitTest function");
		BaseModulesBean baseModulesBean2 = new BaseModulesBean();
		
		List list = baseModulesDAOInf.queryList();
		
		String inputStreamString=null;//保存数据库内容
		String inputStreamData="[";
		
		//System.out.println("queryList1111111");
		Iterator testItertor =null;	
		testItertor=list.iterator();
		//System.out.println("queryList222222");
		
		if(list.size()<=0 || list == null){
			System.out.println("list null");
		}
		//System.out.println("queryList333333");
		
		while(testItertor.hasNext()){
			if(inputStreamString==null){
				//System.out.println("queryList44444444");
				baseModulesBean2=(BaseModulesBean)testItertor.next();
				inputStreamString="{\"id\":"+baseModulesBean2.getFid()+",\"modulename\":\""+baseModulesBean2.getModulename()+"\",\"text\":\""+baseModulesBean2.getModulememo()+"\"}";
			}else{
				baseModulesBean2=(BaseModulesBean)testItertor.next();
				inputStreamString=inputStreamString + ",{\"id\":"+baseModulesBean2.getFid()+",\"modulename\":\""+baseModulesBean2.getModulename()+"\",\"text\":\""+baseModulesBean2.getModulememo()+"\"}";
			}
		}
		inputStreamData=inputStreamData+inputStreamString+"]";
		
		System.out.println("inputStreamData = "+inputStreamData);
		
		inputStream = new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
		
		return "success";
	}
		
}



/*
 * 
 * 
 * 
 * inputStream="{\"modulename\":\""+baseModulesBean.getModulesName+"\",\"modulememo\":\"+baseModulesBean.getModulesMemo+'"\"}";
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
