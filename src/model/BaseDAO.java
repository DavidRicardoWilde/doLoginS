
package model;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseDAO<T> implements BaseDAOInf<T> {
	private Class entityClass;
	
	protected SessionFactory sessionFactory;

		public void setSessionFactory(SessionFactory sessionFactory){
			this.sessionFactory=sessionFactory;
		}
		public SessionFactory getSessionFacotry(){
			return sessionFactory;
		}
		
		protected BaseDAO(){
			Type genType = getClass().getGenericSuperclass();
			Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
			//ParameterizedType params = ((ParameterizedType)genType).getActualTypeArguments();
			entityClass =(Class)params[0];
		}
		
		
		public <T> List queryBySQL(String sql, T... tObj){
			System.out.println("sql = "+sql);
			
			for(int i=0;i<tObj.length;i++){
				System.out.println("tObjt = "+tObj[i].getClass());
				i++;
			}
			
			try{
				Session session=sessionFactory.getCurrentSession();
				//T t=tObjt;
				//System.out.println("class is"+entityClass.getName());
				System.out.println("tObjt.toString() =" + tObj.getClass());
				System.out.println("entityClass.getClass() = "+entityClass.getClass());
				
				SQLQuery sqlQuery=session.createSQLQuery(sql);
				//SQLQuery sqlQuery=session.createSQLQuery(sqlStr).addEntity(entityClass.getClass());
				//Query sqlQuery=session.createSQLQuery(sqlStr).addEntity(entityClass.getClass());
				System.out.println("1111111111111111111111");
				List list =null;
				//list = sqlQuery.addEntity(entityClass.getName()).list();
				
				
				if(tObj.length==1){
					list = sqlQuery.addEntity(tObj[0].getClass()).list();
					System.out.println("list 1 = "+list);
				}else if(tObj.length==2){
					list = sqlQuery.addEntity("c",tObj[0].getClass()).addEntity("m",tObj[1].getClass()).list();
					System.out.println("list 2 = "+list);
				}
				
				
				//List list = sqlQuery.list();//要想变成hibernate的对象 必须使用addEntity 但是这样就必须包含所有的column的对象。 如果不使用就只能变成不同的list（数组？？）
				
				System.out.println("22222222222222222222222");
				System.out.println("this function's list = "+list);
				return list;
			}catch(Exception e){
				List list = null;
				System.out.println(e);
				return list;
			}
		}
		
//		public List queryBySQL(String sql,T... tObjt){
//			System.out.println("sql = "+sql);
//			try{
//				Session session=sessionFactory.getCurrentSession();
//				//T t=tObjt;
//				//System.out.println("class is"+entityClass.getName());
//				System.out.println("tObjt.toString() =" + tObjt.getClass());
//				System.out.println("entityClass.getClass() = "+entityClass.getClass());
//				
//				SQLQuery sqlQuery=session.createSQLQuery(sql);
//				//SQLQuery sqlQuery=session.createSQLQuery(sqlStr).addEntity(entityClass.getClass());
//				//Query sqlQuery=session.createSQLQuery(sqlStr).addEntity(entityClass.getClass());
//				System.out.println("1111111111111111111111");
//				List list =null;
//				//list = sqlQuery.addEntity(entityClass.getName()).list();
//				list = sqlQuery.addEntity(tObjt.getClass()).list();
//				//List list = sqlQuery.list();//要想变成hibernate的对象 必须使用addEntity 但是这样就必须包含所有的column的对象。 如果不使用就只能变成不同的list（数组？？）
//				
//				System.out.println("22222222222222222222222");
//				System.out.println("this function's list = "+list);
//				return list;
//			}catch(Exception e){
//				List list = null;
//				System.out.println(e);
//				return list;
//			}
//		}
		
		public void hibernateBySQL(String sql){
					try{
						System.out.println("this is hebernateBySql function");
						Session session=sessionFactory.getCurrentSession();
						SQLQuery sqlQuery=session.createSQLQuery(sql);
						
					}catch (Exception e){
						System.out.println(e);
					}
				}
		
		public List queryList(){
			//System.out.println("queryList1111111");
			try{
				System.out.println("queryList2222222222");
				Session session=sessionFactory.getCurrentSession();

				String hqlStr="from "+entityClass.getName();
				System.out.println("hqlStr = "+hqlStr);
				
				Query query=session.createQuery(hqlStr);
				System.out.println("queryList33333333333");
				
				List list = query.list();
				System.out.println("queryList444444444444");
				System.out.println(list);
				return list;
			}catch(Exception e){
				List list =null;
				System.out.println(e);
				return list;
			}
		}
		

		public T queryObjt(String condition) {
			//T tObjt;
			Session session = sessionFactory.getCurrentSession();
			String hqlStr=" from "+entityClass.getName()+" where '"+condition+"'";
			Query query = session.createQuery(hqlStr);
			java.util.List testList = null;
			testList=(List<T>)query.list();
			T t = null;
			if(testList.size()>0){
				t = (T) testList.get(0);
			}
			return t;
		}
		
//		public void addForDevgrp(){
//			Session session=sessionFactory.getCurrentSession();
//			
//		}
		
		public void addObjt(T tObjt) {
			try{
				System.out.println("add function");
				Session session=sessionFactory.getCurrentSession();
				System.out.println("ready to save");
				session.save(tObjt);
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		public void modObjt(T tObjt) {
			System.out.println("mod function");
			Session session = sessionFactory.getCurrentSession();
			session.update(tObjt);
		}
		
		public void delObjt(T tObjt) {
			try{
				System.out.println("test");
				System.out.println("tObjt = "+tObjt);
				Session session = sessionFactory.getCurrentSession();
				session.delete(tObjt);
				System.out.println("Over!");
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		public int sqlAssist(String sql){
			int result;
			String sqlStr = sql;
			System.out.println("sql = "+sqlStr);

			Session session = sessionFactory.getCurrentSession();
			SQLQuery sqlQuery=session.createSQLQuery(sql);
			result = sqlQuery.executeUpdate();
			System.out.println("result = "+result);
			return result;
		}
}