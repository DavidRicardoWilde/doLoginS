package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import model.BaseDevGroupBean;
import model.BaseDevGroupDAOInf;
import model.BaseDevModulesBean;
import model.BaseDevModulesDAOInf;
import model.BaseModulesBean;
import model.BaseModulesDAOInf;


public class baseDevGroupService {
	private BaseDevGroupDAOInf baseDevGroupDAOInf;
	private BaseModulesDAOInf baseModulesDAOInf;
	private BaseDevModulesDAOInf baseDevModulesDAOInf;
	private InputStream inputStream;
	
	private int id;
	private int parentId;
	private int lft;
	private int rgt;
	private String grpname;
	private String grpmemo;
	private int efc;
	private int wxid;
	private int smsid;
	private String ml1;
	
	private String funct;
	
	public void setBaseDevModulesDAOInf(BaseDevModulesDAOInf baseDevModulesDAOInf){
		this.baseDevModulesDAOInf=baseDevModulesDAOInf;
	}
	public BaseDevModulesDAOInf getBaseDevModulesDAOInf(){
		return baseDevModulesDAOInf;
	}
	
	public void setBaseModulesDAOInf(BaseModulesDAOInf baseModulesDAOInf){
		this.baseModulesDAOInf=baseModulesDAOInf;
	}
	public BaseModulesDAOInf getBaseModulesDAOInf(){
		return baseModulesDAOInf;
	}
	
	public void setBaseDevGroupDAOInf(BaseDevGroupDAOInf baseDevGrouppDAOInf){
		this.baseDevGroupDAOInf=baseDevGrouppDAOInf;
	}
	public BaseDevGroupDAOInf getBaseDevGroupDAOInf(){
		return baseDevGroupDAOInf;
	}
	
	public void setFunct(String funct){
		this.funct=funct;
	}
	public String getFucnt(){
		return funct;
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream=inputStream;
	}
	public InputStream getInputStream(){
		return inputStream;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setParentId(int parentId){
		this.parentId=parentId;
	}
	public int getParentId(){
		return parentId;
	}
	
	public void setMl1(String ml1){
		this.ml1=ml1;
	}
	public String getMl1(){
		return ml1;
	}
	
	
	public void setLft(int lft){
		this.lft=lft;
	}
	public int getLft(){
		return lft;
	}
	
	public void setRgt(int rgt){
		this.rgt=rgt;
	}
	public int getRgt(){
		return rgt;
	}
	
	public void setGrpname(String grpname){
		this.grpname=grpname;
	}
	public String getGrpname(){
		return grpname;
	}
	
	public void setGrpmemo(String grpmemo){
		this.grpmemo=grpmemo;
	}
	public String getGrpmemo(){
		return grpmemo;
	}
	
	public void setEfc(int efc){
		this.efc=efc;
	}
	public int getEfc(){
		return efc;
	}
	
	public void setWxid(int wxid){
		this.wxid=wxid;
	}
	public int getWxid(){
		return wxid;
	}
	
	public void setSmsid(int smsid){
		this.smsid=smsid;
	}
	public int getSmsid(){
		return smsid;
	}	
//-------------------------------------------------------------------------	
	public String page(){
		System.out.println("this is baseDevGroupService");
		
		return "baseDevGroup";
	}

	public String switchModules() throws Exception{
		System.out.println("function is "+funct);
		System.out.println("pid = "+parentId);
		switch(funct){
		case "add_child":
			//System.out.println("2222funct = "+funct);
			addChild();
			break;
		case "add_nested":
			add_nested();
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
		default:
			break;
		}
		
		return "success";
	}
	
	public void add_nested() throws Exception{
		BaseDevGroupBean baseDevGrpBean = new BaseDevGroupBean();
		BaseDevGroupBean baseDevGrpBean2 = new BaseDevGroupBean();
		BaseDevModulesBean baseDevModulesBean = new BaseDevModulesBean();
		System.out.println("pid = "+parentId);
		
		baseDevGrpBean.set_parentid(parentId);
		baseDevGrpBean.setGrpname(grpname);
		//baseDevGrpBean.setMl(ml);
		baseDevGrpBean.setEfc(efc);
		baseDevGrpBean.setWxid(wxid);
		baseDevGrpBean.setSmsid(smsid);
		baseDevGrpBean.setGrpmemo(grpmemo);
		
		baseDevGroupDAOInf.addObjt(baseDevGrpBean);
		
		System.out.println("grpname = "+baseDevGrpBean.getGrpname());
		//操作dev_modules表
		String sql=null;
		sql = "select * from base_devgroup where _parentId = "+baseDevGrpBean.get_parentid();
		List list = baseDevGroupDAOInf.queryBySQL(sql, baseDevGrpBean);
		Iterator testItertor =null;
		testItertor=list.iterator();
		int dev_id=0;
		while(testItertor.hasNext()){
			baseDevGrpBean2=(BaseDevGroupBean)testItertor.next();
			System.out.println("bean2 grpname = "+baseDevGrpBean2.getGrpname());
			if(baseDevGrpBean2.getGrpname()==baseDevGrpBean2.getGrpname()){
				dev_id=baseDevGrpBean2.getFid();
				continue;
			}
		}
		
		String modulistStr =ml1.replaceAll(" ", "");
		
		baseDevModulesBean.setModulist(modulistStr);
		baseDevModulesBean.setDev_id(dev_id);
		baseDevModulesBean.setDm_type(1);
		
		System.out.println("dev_id = "+baseDevModulesBean.getDev_id());
		System.out.println("ml1 = "+baseDevModulesBean.getModulist());
		
		baseDevModulesDAOInf.addObjt(baseDevModulesBean);
		
	}
	
	public void addChild() throws Exception{
		//增加子节点
		BaseDevGroupBean baseDevGrpBean = new BaseDevGroupBean();
		BaseDevGroupBean baseDevGrpBean2 = new BaseDevGroupBean();
		BaseDevModulesBean baseDevModulesBean = new BaseDevModulesBean();
		
		//baseDevGrpBean.setFid(id);
		baseDevGrpBean.set_parentid(id);
		baseDevGrpBean.setGrpname(grpname);
		//baseDevGrpBean.setMl(ml);
		baseDevGrpBean.setEfc(efc);
		baseDevGrpBean.setWxid(wxid);
		baseDevGrpBean.setSmsid(smsid);
		baseDevGrpBean.setGrpmemo(grpmemo);
		
		System.out.println("pid = "+baseDevGrpBean.get_parentid());
		//System.out.println("wxid = "+baseDevGrpBean.getWxid());
		baseDevGroupDAOInf.addObjt(baseDevGrpBean);
		
		System.out.println("============================");
		//获取fid？？？
		String sql=null;
		sql = "select * from base_devgroup where _parentId = "+baseDevGrpBean.get_parentid();
		List list = baseDevGroupDAOInf.queryBySQL(sql, baseDevGrpBean);
		Iterator testItertor =null;
		testItertor=list.iterator();
		int dev_id=0;
		while(testItertor.hasNext()){
			baseDevGrpBean2=(BaseDevGroupBean)testItertor.next();
			if(baseDevGrpBean2.get_parentid()==baseDevGrpBean.get_parentid()){
				dev_id=baseDevGrpBean2.getFid();
				continue;
			}
		}
		
		String modulistStr =ml1.replaceAll(" ", "");
		System.out.println("modulist = "+modulistStr);
		baseDevModulesBean.setModulist(modulistStr);
		baseDevModulesBean.setDev_id(dev_id);
		baseDevModulesBean.setDm_type(1);
		
		System.out.println("dev_id = "+baseDevModulesBean.getDev_id());
		System.out.println("ml1 = "+baseDevModulesBean.getModulist());
		baseDevModulesDAOInf.addObjt(baseDevModulesBean);
		
		//baseDevModulesBean.setDev_id(/**/);
		//baseDevGrpBean.setMl(ml1); 错 debgrp没有ml
		
		
//		System.out.println("ml1 = "+baseDevModulesBean.getModulist());
//		System.out.println("fid = "+baseDevGrpBean.get_parentid());
//		System.out.println("pid = "+baseDevGrpBean.get_parentid());
//		System.out.println("grpname = "+baseDevGrpBean.getGrpname());
//		System.out.println("efc = "+baseDevGrpBean.getEfc());
//		System.out.println("wxid = "+baseDevGrpBean.getWxid());
//		System.out.println("smsid = "+baseDevGrpBean.getSmsid());
//		System.out.println("grpmemo = "+baseDevGrpBean.getGrpmemo());
		//System.out.println("ml = "+baseDevGrpBean.getMl());
		
//		String sql="";
//		baseDevGroupDAOInf.hibernateBySQL(sql);
		
		//baseDevModulesDAOInf.addObjt(baseDevModulesBean);
		
		
	}
	
	public void modData() throws Exception{
		System.out.println("this is mod function");
		//修改数据
		BaseDevGroupBean baseDevGrpBean = new BaseDevGroupBean();
		BaseDevModulesBean baseDevModulesBean2 = new BaseDevModulesBean();
		BaseDevModulesBean baseDevModulesBean = new BaseDevModulesBean();
		
		System.out.println("id = "+id);
		System.out.println("pid = "+parentId);
		//baseDevGroupDAOInf.modObjt(baseDevGrpBean);
		
		baseDevGrpBean.setFid(id);
		baseDevGrpBean.set_parentid(parentId);
		baseDevGrpBean.setGrpname(grpname);
		baseDevGrpBean.setEfc(efc);
		baseDevGrpBean.setWxid(wxid);
		baseDevGrpBean.setSmsid(smsid);
		baseDevGrpBean.setGrpmemo(grpmemo);
		
	
		System.out.println("Bean pid = "+baseDevGrpBean.get_parentid());
		baseDevGroupDAOInf.modObjt(baseDevGrpBean);
		
		String sql =null;
		sql = "select * from base_dev_modules where dev_id = "+id;
		List list = baseDevGroupDAOInf.queryBySQL(sql, baseDevModulesBean);
		Iterator testItertor =null;
		testItertor=list.iterator();
		int fid_devmodules=0;
		while(testItertor.hasNext()){
			baseDevModulesBean2=(BaseDevModulesBean)testItertor.next();
			if(baseDevModulesBean2.getDev_id()==id){
				fid_devmodules=baseDevModulesBean2.getFid();
				continue;
			}
		}
		
		
		String modulistStr =ml1.replaceAll(" ", "");
		System.out.println("modulist = "+modulistStr);
		baseDevModulesBean.setModulist(modulistStr);
		baseDevModulesBean.setDev_id(id);
		baseDevModulesBean.setDm_type(1);
		baseDevModulesBean.setFid(fid_devmodules);
		
		System.out.println("dev_id = "+baseDevModulesBean.getDev_id());
		System.out.println("ml1 = "+baseDevModulesBean.getModulist());
		System.out.println("fid in dev_modules = "+baseDevModulesBean.getFid());
		System.out.println("modulist = "+baseDevModulesBean.getModulist());
		baseDevModulesDAOInf.modObjt(baseDevModulesBean);
	}
	
	public void delData() throws Exception{
		//删除节点
		BaseDevGroupBean baseDevGrpBean = new BaseDevGroupBean();
		BaseDevModulesBean baseDevModulesBean = new BaseDevModulesBean();//删除base_dev_modules表里的内容
		//System.out.println("id = "+id);
		
		baseDevGrpBean.setFid(id); //删除devgroup表数据
		//baseDevGrpBean.set_parentid(parentId);//测试 是否必须fid才能删除 hibernate删除必须操作主键
		
		baseDevModulesBean.setDev_id(id);//设置需要删除dev_modules表中的数据
		//int test=60;
		//baseDevModulesBean.setFid(test);
		
		String sql=null;
		sql = "delete from base_dev_modules where dev_id="+baseDevModulesBean.getDev_id();
		
			//System.out.println("id = "+baseDevGrpBean.getFid());
			//System.out.println("dev_id = "+baseDevModulesBean.getDev_id());
		
		//删除base_dev_modules数据
		//String sql="delete from base_dev_modules where dev_id=?";
		//System.out.println("hql = "+sql);
		//baseDevModulesDAOInf.hqlAssist(sql,baseDevModulesBean.getDev_id());
		//baseDevModulesDAOInf.delObjt(baseDevModulesBean);
		
		//baseDevModulesDAOInf.sqlAssist(sql, baseDevModulesBean.getDev_id());
		int result;
		boolean bool;
		result = baseDevModulesDAOInf.sqlAssist(sql);
		if(result==1){
			bool=true;
		}else{
			bool=false;
			System.out.println("some thing wrong on delete base_dev_modules data");
		}
		baseDevGroupDAOInf.delObjt(baseDevGrpBean);
		
	}
	
	//显示页面数据
	public String showPage() throws Exception{
		
		System.out.println("This is showpage function");
		BaseDevGroupBean devgrpBean2 = new BaseDevGroupBean();
		BaseModulesBean modulesBean2 = new BaseModulesBean();
		BaseDevModulesBean devmodulesBean2 =new BaseDevModulesBean();
		
		String sqlAssist=null;
		String sql = null;
		//sql = "select * from base_devgroup";
		
//		sql = "SELECT c.fid, c.grpname, c.grpmemo, c.efc,c.wxid,c.smsid, c._parentid, c.lft, c.rgt, IFNULL(m.modulist,'')AS ml FROM base_devgroup c "
//				+"LEFT JOIN base_devgroup p ON(c.lft BETWEEN p.lft AND p.rgt) "
//				+"LEFT JOIN base_dev_modules m ON (m.dm_type=1 AND c.fid=m.dev_id) WHERE c.lft BETWEEN "
//				+"0 AND 90 GROUP BY c.grpname ORDER BY c.lft;";
		
		sql = "SELECT c.fid, m.fid, IFNULL(m.dm_type, 1)AS dm_type, IFNULL(m.dev_id, 0)AS dev_id, m.modulist, c.grpname, c.grpmemo, c.efc,c.wxid,c.smsid, c._parentid, c.lft, c.rgt, IFNULL(m.modulist,'')AS ml FROM base_devgroup c "
				+"LEFT JOIN base_devgroup p ON(c.lft BETWEEN p.lft AND p.rgt) "
				+"LEFT JOIN base_dev_modules m ON (m.dm_type=1 AND c.fid=m.dev_id) WHERE c.lft BETWEEN "
				+"0 AND 90 GROUP BY c.grpname ORDER BY c.lft;";
		
				//sql = "select * from BaseDevGroupBean"; sql不是query Bean？？？？
		//List list = baseDevGroupDAOInf.queryList();
		sqlAssist="SELECT fid, modulename, modulememo FROM base_modules;";
		
		//baseDevGroupDAOInf.test(sql, "123",1,2,3);
		
		List list = baseDevGroupDAOInf.queryBySQL(sql, devgrpBean2, devmodulesBean2); //需要在BaseDAOInf里添加queryBySQL函数， 需要在spring配置文件添加相关函数的属性。
		List listAssist = baseModulesDAOInf.queryBySQL(sqlAssist, modulesBean2); //需要在spring里的baseDevGroupService bean里添加相关属性
		
		//sql="SELECT fid,modulememo mn FROM base_modules;";
		//List listAssist = baseModulesDAOInf.queryBySQL(sqlAssist);
		
		String inputStreamString=null;//保存数据库内容
		String inputStreamData="{\"total\":1,\"rows\":[";
		
		//System.out.println("queryList2222222222");
		
		Iterator testItertor =null;
		testItertor=list.iterator();
		
		Iterator testItertor2 =null;
		testItertor2=listAssist.iterator();
		
		//System.out.println("devgrpService testItertor ="+testItertor);
		//System.out.println("queryList2222222222");
		
		
		String[] mlChar = new String[3]; //字符串数组不能使用 String[] xxx = null;
		int i=0;
		
		
		if(listAssist.size()<=0 || listAssist == null){
			System.out.println("list null");
		}
		
		System.out.println("list = "+list);
		System.out.println("list size ="+list.size());
		
		while(testItertor2.hasNext()){
			modulesBean2=(BaseModulesBean)testItertor2.next();
			//System.out.println("ml = "+ modulesBean2.getModulememo());
			mlChar[i]=modulesBean2.getModulememo();
			i++;
		}
		
		if(list.size()<=0 || list == null){
			System.out.println("list null");
		}
		
		//System.out.println("list = "+list);
		
		System.out.println("33333333333333333333");
		
		Object[] objects=null;

		System.out.println("44444444444444444444");
		
		for(Iterator iterator = list.iterator();iterator.hasNext();){
			//System.out.println("~~~~~~~~~~~~~~~~");
			objects = (Object[]) iterator.next();
			devgrpBean2=(BaseDevGroupBean)objects[0];
			devmodulesBean2=(BaseDevModulesBean)objects[1];
			
			if(inputStreamString==null){
				//System.out.println("queryList44444444");
				
				//devgrpBean2=(BaseDevGroupBean)testItertor.next();
				
				//inputStreamString="{\"fid\":"+baseModulesBean2.getFid()+",\"modulename\":\""+baseModulesBean2.getModulename()+"\",\"modulememo\":\""+baseModulesBean2.getModulememo()+"\"}";
				//inputStreamString = "{\"id\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"lft\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"rgt\":"+devgrpBean2.getRgt()+",\"_parentId\":"+devgrpBean2.get_parentid()+",\"ml\":\""+devgrpBean2.getMl()+"\"}";
				//inputStreamString = inputStreamString+"{\"fid\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"id\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"pid\":"+devgrpBean2.getRgt()+",\"_parentid\":"+devgrpBean2.get_parentid()+"}";
			
				if(devmodulesBean2.getModulist() == null){
					//System.out.println("---------------");
					inputStreamString = "{\"id\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"lft\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"rgt\":"+devgrpBean2.getRgt()+",\"_parentId\":"+devgrpBean2.get_parentid()+",\"ml\":\""+devmodulesBean2.getModulist()+"\"}";
				}else{
					//System.out.println("================");
					inputStreamString = "{\"id\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"lft\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"rgt\":"+devgrpBean2.getRgt()+",\"_parentId\":"+devgrpBean2.get_parentid()+",\"ml\":\""+dealMn(devmodulesBean2.getModulist(),mlChar)+"\"}";
					//dealMn(devgrpBean2.getMl(),mlChar);
				}
			}else{
				//devgrpBean2=(BaseDevGroupBean)testItertor.next();
				
				//modulesBean2=(BaseModulesBean)testItertor2.next();
				//System.out.println("++++-----======///////");
				//inputStreamString=inputStreamString + ",{\"fid\":"+baseModulesBean2.getFid()+",\"modulename\":\""+baseModulesBean2.getModulename()+"\",\"modulememo\":\""+baseModulesBean2.getModulememo()+"\"}";
				//inputStreamString = inputStreamString+",{\"id\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"lft\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"rgt\":"+devgrpBean2.getRgt()+",\"_parentId\":"+devgrpBean2.get_parentid()+",\"ml\":\""+devgrpBean2.getMl()+"\"}";
				//inputStreamString = inputStreamString+",{\"fid\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"id\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"pid\":"+devgrpBean2.getRgt()+",\"_parentid\":"+devgrpBean2.get_parentid()+"}";
			
				if(devmodulesBean2.getModulist() == null){
					inputStreamString = inputStreamString+",{\"id\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"lft\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"rgt\":"+devgrpBean2.getRgt()+",\"_parentId\":"+devgrpBean2.get_parentid()+",\"ml\":\""+devmodulesBean2.getModulist()+"\"}";
				}else{
					//System.out.println("---------------");
					//System.out.println("================");
					inputStreamString = inputStreamString+",{\"id\":"+devgrpBean2.getFid()+",\"grpname\":\""+devgrpBean2.getGrpname()+"\",\"lft\":"+devgrpBean2.getLft()+",\"efc\":"+devgrpBean2.getEfc()+",\"wxid\":\""+devgrpBean2.getWxid()+"\",\"smsid\":\""+devgrpBean2.getSmsid()+"\",\"grpmemo\":\""+devgrpBean2.getGrpmemo()+"\",\"rgt\":"+devgrpBean2.getRgt()+",\"_parentId\":"+devgrpBean2.get_parentid()+",\"ml\":\""+dealMn(devmodulesBean2.getModulist(),mlChar)+"\"}";
					//dealMn(devgrpBean2.getMl(),mlChar);
				}
			
			
			}
			//System.out.println("=======================");
		}
		
		System.out.println("4444444444444444444444");
		
		inputStreamData=inputStreamData+inputStreamString+"]}";
		
		//inputStreamData="{\"total\":1,\"rows\":[{\"id\":1,\"grpname\":\"娣卞湷璁惧\",\"grpmemo\":\"\",\"_parentId\":0,\"ml1\":\"125\",\"ml\":\"绯荤粺,鍏畨鍚堣瀹¤鍔熻兘,涓婄綉璁よ瘉閫氫俊鎺ュ彛\"}]}";
		System.out.println("devgrp inputStreamData = "+inputStreamData);
		
//		for(int j=0; j<3; j++){
//			System.out.println(j+" mlChar = "+mlChar[j]+ " ");
//		}

		
		
		//System.out.println("list size =" +list.size());
//		for(int i=0,	){
//			
//		} //待写
		
		inputStream = new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
		
		return "success";
	}
	
	public String dealMn(String mnStr,String[] mlChar){
		String ml="";
		//System.out.println("mnStr = "+mnStr);
		
		String[] strArr=mnStr.split(",");
		//char[] charArr=strArr.toString().toCharArray();
		for(int i=0;i<strArr.length;i++){
			if(strArr[i].equals("1")){
				ml=mlChar[i]+",";
			}else if(strArr[i].equals("2")){
				ml=ml+mlChar[i]+",";
			}else if(strArr[i].equals("5")){
				ml=ml+mlChar[i];
			}
		}
		//System.out.println("ml = "+ml);
		//test(1,2,3,"123");
		
		return ml;
	}

//	public int obtainFid(){
//		int obtain_fid;
//		String sql=null;
//		sql = "select * from base_devgroup where _parentId = ";
//		
//		return obtain_fid;
//	}
}
