package model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.WxAccountBean;

public class WxAccountDAO extends BaseDAO<WxAccountBean> implements WxAccountDAOInf {
//	private InputStream inputStream;
//	private SessionFactory sessionFactory;
	
//	public void setSessionFactory(SessionFactory sessionFactory){
//		this.sessionFactory=sessionFactory;
//	}
//	public SessionFactory getSessionFacotry(){
//		return sessionFactory;
//	}
//	
//	public void setInputStream(InputStream inputStream){
//		this.inputStream=inputStream;
//	}
//	public InputStream getInputStream(){
//		return inputStream;
//	}
//	
	/*
	public String jsonData(){
		System.out.println("this is a json text funtion");

		String jsonString;

		WxAccountBean wx =new WxAccountBean();//Test
		WxAccountBean wx2=new WxAccountBean();

		Session session=sessionFactory.getCurrentSession();
		//Transaction tx = session.beginTransaction();
		//session.beginTransaction();
		
		String hql=null;

		//System.out.println("hql = "+hql);
			Query query=null;
			java.util.List testList = null;
			Iterator testItertor =null;

			//int i=1;
			String inputStreamString=null;//保存数据库内容
			String inputStreamData="{\"total\":1,\"rows\":[";
			//Test
			System.out.println("------------------------------------------");
			
			hql="from WxAccountBean";
			System.out.println("hql = "+hql);
			query=session.createQuery(hql);
			testList=query.list();
			
			
			testItertor=testList.iterator();
			
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
			
			//tx.commit();
			//session.close();
			System.out.println("Last Fid = "+wx2.getFid());

			try{
				inputStream=new ByteArrayInputStream(inputStreamData.getBytes("utf-8"));
				
			}catch(Exception e){
				System.out.println("something going wrong");
			}
			
			return inputStreamData;
			
//			return "success";
	}
	
*/
//	public void saveData(){
////		System.out.println("this is saveData function");
//		
//	}
//
//	public void addData(WxAccountBean wxAccountBean) throws Exception{
//		
//		//可以定义自己的Exception 需要在上层获取exception/异常
//		
//		//保存的hql???
//		//String hql=null; //不需要hql
//		try{
//			Session session =sessionFactory.getCurrentSession();
//			//Transaction tx;
//			//tx=session.beginTransaction();
//			session.save(wxAccountBean);
//			//tx.commit();
//		}catch(Exception e){
//			System.out.println("wxaccountdao test:"+e);
//		}
//		
////		System.out.println("this is addData function in DAO");
////		System.out.println("pnick = "+wxAccountBean.getPnick());
////		System.out.println("pname = "+wxAccountBean.getPname());
////		System.out.println("appsecret = "+wxAccountBean.getAppsecret());
////		System.out.println("paccount = "+wxAccountBean.getPaccount());
////		System.out.println("appid = "+wxAccountBean.getAppid());
//		
//	}
//	
//	public void modData(WxAccountBean wxAccountBean) throws Exception{
//		
//		
////		System.out.println("this is addData function in DAO");
//		System.out.println("fid = "+wxAccountBean.getFid());
////		System.out.println("pname = "+wxAccountBean.getPname());
////		System.out.println("appsecret = "+wxAccountBean.getAppsecret());
////		System.out.println("paccount = "+wxAccountBean.getPaccount());
////		System.out.println("appid = "+wxAccountBean.getAppid());
//		WxAccountBean wxAccountBean2=new WxAccountBean(); 
//		
//		try{			
//			Session session=sessionFactory.getCurrentSession();
//			//Transaction tx;
//			//tx=session.beginTransaction();
//			
////			String hql="from WxAccountBean where fid='"+wxAccountBean.getFid()+"'";
////			Query query = session.createQuery(hql);
////			System.out.println("query = "+query);
//////			session.saveOrUpdate(wxAccountBean);
////			java.util.List testList = null;
////			testList=query.list();
////			Iterator testItertor =testList.iterator();
////			System.out.println("testList = "+testList);
////			
////			wxAccountBean2=(WxAccountBean)testItertor.next();
////			System.out.println("appid = "+wxAccountBean2.getAppid());
//			
//			session.update(wxAccountBean);
//			//tx.commit();
//		}catch(Exception e){
//			System.out.println("wxaccountdao test:"+e);
//		}
//	}
//
//	public void delData(WxAccountBean wxAccountBean) throws Exception{
//		System.out.println("fid = "+wxAccountBean.getFid());
//		try{
//			Session session =sessionFactory.getCurrentSession();
//			//Transaction tx;
//			//tx=session.beginTransaction();
//			session.delete(wxAccountBean);
//			//tx.commit();
//		}catch(Exception e){
//			System.out.println("wxaccountdao test:"+e);
//		}
//	}
	

}
