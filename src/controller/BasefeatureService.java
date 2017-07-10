package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import model.BaseFeatureBean;
import model.BaseFeatureDAOInf;


public class BasefeatureService {
	private BaseFeatureDAOInf baseFeatureDAOInf;
	private InputStream inputStream;
	
	private int pid;
	private int fid;
	private int id;
	private String fname;
	private String furl;
	private String ficon;
	private int pwinfo;
	private int isroot;
	private int ordid;
	private String fmemo;
	
	private String funct;
	
	public void setFmemo(String fmemo){
		this.fmemo=fmemo;
	}
	public String getFmemo(){
		return fmemo;
	}
	
	public void setOrdid(int ordid){
		this.ordid=ordid;
	}
	public int getOrdid(){
		return ordid;
	}
	
	public void setIsroot(int isroot){
		this.isroot=isroot;
	}
	public int getIsroot(){
		return isroot;
	}
	
	public void setPwinfo(int pwinfo){
		this.pwinfo=pwinfo;
	}
	public int getPwinfo(){
		return pwinfo;
	}
	
	public void setFicon(String ficon){
		this.ficon=ficon;
	}
	public String getFicon(){
		return ficon;
	}
	
	public void setFurl(String furl){
		this.furl=furl;
	}
	public String getFurl(){
		return furl;
	}
	
	public void setFname(String fname){
		this.fname=fname;
	}
	public String getFname(){
		return fname;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setPid(int pid){
		this.pid=pid;
	}
	public int getPid(){
		return pid;
	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setFunct(String funct){
		this.funct=funct;
	}
	public String getFunct(){
		return funct;
	}
	
	public void setBaseFeatureDAOInf(BaseFeatureDAOInf baseFeatureDAOInf){
		this.baseFeatureDAOInf=baseFeatureDAOInf;
	}
	public BaseFeatureDAOInf getBaseFeatureDAOInf(){
		return baseFeatureDAOInf;
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream=inputStream;
	}
	public InputStream getInputStream(){
		return inputStream;
	}
	
	public String showpage(){
		return "FeaturePage";
	}
	
	public String switchfunct() throws Exception{
		System.out.println("this is switch function and the function your switch is "+funct);
	
		switch(funct){
		case "add":
			addData();
			break;
		case "mod":
			//modData();
			break;																			
		case "del":
			//delData();
			break;
		}
		return "success";
	}
	
	public void checkData(){
		System.out.println("fid = "+fid);
		System.out.println("pid = "+pid);
		System.out.println("ficon = "+ficon);
		System.out.println("fmemo = "+fmemo);
		System.out.println("fname = "+fname);
		System.out.println("furl = "+furl);
		System.out.println("id = "+id);
		System.out.println("isroot = "+isroot);
		System.out.println("ordid = "+ordid);
		System.out.println("pwinfo = "+pwinfo);
	}
	
	public void addData() throws Exception{
		System.out.println("this is add function");
		BaseFeatureBean baseFeatureBean = new BaseFeatureBean();
		
		baseFeatureBean.setFid(fid);
		baseFeatureBean.setPid(pid);
		baseFeatureBean.setFicon(ficon);
		baseFeatureBean.setFmemo(fmemo);
		baseFeatureBean.setFname(fname);
		baseFeatureBean.setFurl(furl);
		baseFeatureBean.setId(id);
		baseFeatureBean.setIsroot(isroot);
		baseFeatureBean.setOrdid(ordid);
		baseFeatureBean.setPwinfo(pwinfo);
		
		checkData();
		
	}
	
	public String getData() {
		System.out.println("this is BaseFeatureService");
		
		try{
			BaseFeatureBean baseFeatureBean =new BaseFeatureBean();
			List list = baseFeatureDAOInf.queryList();
			System.out.println("test");
			
			String inputStreamString=null;
			
			String inputStreamData="[";
			Iterator testItertor =null;
			testItertor=list.iterator();
			
			if(list.size()<=0 || list == null){
				System.out.println("list null");
			}
			while(testItertor.hasNext()){
				if(inputStreamString==null){
					baseFeatureBean=(BaseFeatureBean)testItertor.next();
					inputStreamString="{\"fid\":"+baseFeatureBean.getFid()+",\"pid\":"+baseFeatureBean.getPid()+",\"id\":"+baseFeatureBean.getId()+",\"fname\":\""+baseFeatureBean.getFname()+"\",\"furl\":\""+baseFeatureBean.getFurl()+"\",\"ficon\":\""+baseFeatureBean.getFicon()+"\",\"pwinfo\":\""+baseFeatureBean.getPwinfo()+"\",\"isroot\":\""+baseFeatureBean.getIsroot()+"\",\"ordid\":\""+baseFeatureBean.getOrdid()+"\",\"fmemo\":\""+baseFeatureBean.getFmemo()+"\"}";
				}else{
					baseFeatureBean=(BaseFeatureBean)testItertor.next();
					inputStreamString=inputStreamString+",{\"fid\":"+baseFeatureBean.getFid()+",\"pid\":"+baseFeatureBean.getPid()+",\"id\":"+baseFeatureBean.getId()+",\"fname\":\""+baseFeatureBean.getFname()+"\",\"furl\":\""+baseFeatureBean.getFurl()+"\",\"ficon\":\""+baseFeatureBean.getFicon()+"\",\"pwinfo\":\""+baseFeatureBean.getPwinfo()+"\",\"isroot\":\""+baseFeatureBean.getIsroot()+"\",\"ordid\":\""+baseFeatureBean.getOrdid()+"\",\"fmemo\":\""+baseFeatureBean.getFmemo()+"\"}";
				}
			}
			
			inputStreamData=inputStreamData+inputStreamString+"]";
			System.out.println("inputStreamData = "+inputStreamData);
			
			inputStream = new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}
}
