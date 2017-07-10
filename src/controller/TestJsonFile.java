package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.WxAccountBean;

public class TestJsonFile {
	private InputStream inputStream;
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public SessionFactory getSessionFacotry(){
		return sessionFactory;
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream=inputStream;
	}
	public InputStream getInputStream(){
		return inputStream;
	}
	
	public String jsonData() throws Exception{
		System.out.println("this is json text funtion");
//		inputStream = new ByteArrayInputStream("{\"total\":5,\"rows\":\"Hello\"}".getBytes("UTF-8"));//必须有row 切row必须有参数
//		inputStream = new ByteArrayInputStream("{\"total\":1,\"rows\":\"qq\"}".getBytes("UTF-8"));//total没有改变浏览器显示的行数 影响行数的是rows rows有多少就会有多少
//		inputStream = new ByteArrayInputStream("{\"total\":1,\"rows\":\"[{}]\"}".getBytes("UTF-8"));//1
//		inputStream = new ByteArrayInputStream("{\"total\":1,\"rows\":\"{\"fid\":1}\"}".getBytes("UTF-8"));//2
		
//		inputStream = new ByteArrayInputStream("{\"total\":1,\"rows\":[{\"fid\":1,\"paccount\":\"gh_b3c855dd9391\",\"pnick\":\"yyx-MIG\",\"pname\":\"翼云兴软件\",\"url\":\"http://www.iammig.com/weixin\",\"token\":\"yiyunxing\",\"appid\":\"wx4ad1260c094a0e7b\",\"appsecret\":\"f15f6d8078a59fd5537e0e0451bf014e\",\"enabled\":\"1\"},{\"fid\":1,\"paccount\":\"gh_b3c855dd9391\",\"pnick\":\"yyx-MIG\",\"pname\":\"翼云兴软件\",\"url\":\"http://www.iammig.com/weixin\",\"token\":\"yiyunxing\",\"appid\":\"wx4ad1260c094a0e7b\",\"appsecret\":\"f15f6d8078a59fd5537e0e0451bf014e\",\"enabled\":\"1\"}]}".getBytes("UTF-8"));

		//		inputStream=new ByteArrayInputStream("{\"total\":5,\"rows\":\"[\"{\"fid\":1,\"paccount\":\"gh_b3c855dd9391\",\"pnick\":\"yyx-MIG\",\"pname\":\"翼云兴软件\",\"url\":\"http://www.iammig.com/weixin\",\"token\":\"yiyunxing\",\"appid\":\"wx4ad1260c094a0e7b\",\"appsecret\":\"f15f6d8078a59fd5537e0e0451bf014e\",\"enable\":\"1\"}\"]\"}".getBytes("utf-8")); //3
//		System.out.println(inputStream);
		
		String jsonString;
		WxAccountBean wx2=new WxAccountBean();
		
		WxAccountBean wx =new WxAccountBean();//Test
		wx.setFid(2);
		
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		String hql=null;
		hql=" from wx_account where fid='"+wx.getFid()+"'";
		
		Query query = session.createQuery(hql);
		System.out.println("query = "+query);
		
		java.util.List testList = null;
		testList=query.list();
		Iterator testItertor =testList.iterator();
		int i=0;
		while(testItertor.hasNext()){
			if(wx.getFid()!=i){
				wx2=(WxAccountBean)testItertor.next();
				i++;
				System.out.println("if:"+wx2.getFid());
			}else
				break;
		}
		session.close();
		System.out.println(wx2.getFid());

		return "success";
	}
}
