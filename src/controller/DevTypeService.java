package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import model.BaseModulesBean;
import model.DevTypeBean;
import model.DevTypeDAOInf;

public class DevTypeService {
	private DevTypeDAOInf devTypeDAOInf;
	private InputStream inputStream;
	
	private int fid;
	private String devtypename;
	private String dev_arch;
	private String dev_archname;
	
	private String funct;
	
	public void setFunct(String funct){
		this.funct=funct;
	}
	public String getFunct(){
		return funct;
	}
		
	public void setDevTypeDAOInf(DevTypeDAOInf devTypeDAOInf){
		this.devTypeDAOInf=devTypeDAOInf;
	}
	public DevTypeDAOInf getDevTypeDAOInf(){
		return devTypeDAOInf;
	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setDevtypename(String devtypename){
		this.devtypename=devtypename;
	}
	public String getDevtypename(){
		return devtypename;
	}
	
	public void setDev_arch(String dev_arch){
		this.dev_arch=dev_arch;
	}
	public String getDev_arch(){
		return dev_arch;
	}
	
	public void setDev_archname(String dev_archname){
		this.dev_archname=dev_archname;
	}
	public String getDev_archname(){
		return dev_archname;
	}
	
	public String showpage(){
		return "devtype";
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream=inputStream;
	}
	public InputStream getInputStream(){
		return inputStream;
	} 
	
	public String switchfunct() throws Exception{
		System.out.println("this is switch function and the function your switch is "+funct);
	
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
		}
		return "success";
	}
	
	public void addData() throws Exception{
		System.out.println("this is add function");
		DevTypeBean devTypeBean = new DevTypeBean();
		
		//devTypeBean.setDevtype_id(fid);
		devTypeBean.setDev_arch(dev_arch);
		devTypeBean.setDev_archname(dev_archname);
		devTypeBean.setDevtypename(devtypename);
		
		System.out.println("dev_arch = "+devTypeBean.getDev_arch());
		System.out.println("archname = "+devTypeBean.getDev_archname());
		System.out.println("devtype_id = "+devTypeBean.getDevtype_id());
		System.out.println("devtype_name = "+devTypeBean.getDevtypename());
		
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		devTypeDAOInf.addObjt(devTypeBean);
		
	}
	
	public void modData() throws Exception{
		System.out.println("this is mod function");
		
		DevTypeBean devTypeBean = new DevTypeBean();
		
		devTypeBean.setDevtype_id(fid);
		devTypeBean.setDev_arch(dev_arch);
		devTypeBean.setDev_archname(dev_archname);
		devTypeBean.setDevtypename(devtypename);
		
		System.out.println("dev_arch = "+devTypeBean.getDev_arch());
		System.out.println("archname = "+devTypeBean.getDev_archname());
		System.out.println("devtype_id = "+devTypeBean.getDevtype_id());
		System.out.println("devtype_name = "+devTypeBean.getDevtypename());
		
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		devTypeDAOInf.modObjt(devTypeBean);
	}
	
	
	public void delData() throws Exception{
		System.out.println("this is delete function");
		
		DevTypeBean devTypeBean = new DevTypeBean();
		devTypeBean.setDevtype_id(fid);
		inputStream=new ByteArrayInputStream("{\"success\":1}".getBytes("UTF-8"));
		devTypeDAOInf.delObjt(devTypeBean);
	}
	
	public String getData() throws UnsupportedEncodingException{
		System.out.println("this is getData function in devtypeService");
		DevTypeBean baseDevTypeBean = new DevTypeBean();
		
		
		List list=devTypeDAOInf.queryList();
		
		String inputStreamString=null;
		String inputStreamData="{\"total\":1,\"rows\":[";
		
		Iterator testItertor =null;	
		testItertor=list.iterator();
		
		if(list.size()<=0 || list == null){
			System.out.println("list null");
		}
		
		while(testItertor.hasNext()){
			if(inputStreamString==null){
				//System.out.println("queryList44444444");
				baseDevTypeBean=(DevTypeBean)testItertor.next();
				inputStreamString="{\"fid\":"+baseDevTypeBean.getDevtype_id()+",\"devtypename\":\""+baseDevTypeBean.getDevtypename()+"\",\"dev_arch\":\""+baseDevTypeBean.getDev_arch()+"\",\"dev_archname\":\""+baseDevTypeBean.getDev_archname()+"\"}";
			}else{
				baseDevTypeBean=(DevTypeBean)testItertor.next();
				inputStreamString=inputStreamString + ",{\"fid\":"+baseDevTypeBean.getDevtype_id()+",\"devtypename\":\""+baseDevTypeBean.getDevtypename()+"\",\"dev_arch\":\""+baseDevTypeBean.getDev_arch()+"\",\"dev_archname\":\""+baseDevTypeBean.getDev_archname()+"\"}";
			}
		}
		inputStreamData=inputStreamData+inputStreamString+"]}";
		
		System.out.println("inputStreamData = "+inputStreamData);
		
		inputStream = new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
		
		return "success";
	}
}
