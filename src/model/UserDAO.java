//与数据库之间的操作
package model;


import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.UserBean;

public class UserDAO{
//	private String username;
//	private String password;
	
//	private InsertSql querySql;
	
	private SessionFactory sessionFactory;
	
//	public void UserDAO(){
//		
//	}
	
//	public void setUsername(String username){
//		this.username = username;
//	}
//	public String  getUsername(){
//		return username;
//	}
//	
//	public void setPassword(String password){
//		this.password=password;
//	}
//	public String getPassword(){
//		return password;
//	}
	
//	public void setQuerySql(InsertSql querySql){
//		this.querySql=querySql;
//	}
//	public InsertSql getQuerySql(){
//		return querySql;
//	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	} 
	
	public String query(UserBean userBean) throws Exception{
//      System.out.println("The program get into query function");
		try{
			
//			System.out.println("userBean username= "+userBean.getUsername());
//			System.out.println("userBean password= "+userBean.getPassword());
			
			UserBean userBean2=new UserBean(); //user2是数据库获取数据的对象
			
			Session session = sessionFactory.getCurrentSession();
			//Transaction tx;
			//tx = session.beginTransaction();
			//session.beginTransaction();

			String hql=" from UserBean where fname='"+userBean.getFname()+"'";//sql查询语句
			System.out.println("hql = "+hql);
			
			Query query = session.createQuery(hql);
			System.out.println("query = "+query);
			
			//List 保存数据库查询的数据
			java.util.List testList = null;
			testList=query.list();
			Iterator testItertor =testList.iterator();
			System.out.println("-=-=-=-=-=--=-=-=-=-=-=-=");
			
			while(testItertor.hasNext()){
				if(userBean.getFname()==null){
					userBean2=(UserBean)testItertor.next();
				}else{
					if(userBean.getFname().equals(userBean2.getFname())){ //
						break;
					}else{
						userBean2=(UserBean)testItertor.next();
					}
				}
			}
			//tx.commit();
			//session.close();
			//System.out.println(userBean2.getFid()+userBean2.getFpassword()+userBean2.getEmail());
			System.out.println("Over?");
			if(userBean.getFname()==null){
				return "username_false";
			}else{
				if(userBean.getFname().equals(userBean2.getFname())){
					if(userBean.getFname().equals(userBean2.getFname())){
						return "success";
					}else{
						return "password_false";
					}
				}else{
					return "username_false";
				}
			}
			
		}
		catch(Exception e){
			System.out.println("UserDAO test:"+e);
			return "false";
		}
	}
} 