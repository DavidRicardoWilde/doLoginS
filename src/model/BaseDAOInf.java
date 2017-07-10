package model;

import java.io.InputStream;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface BaseDAOInf<T> {
	
		public void hibernateBySQL(String sql);
		
		public <T> List queryBySQL(String sql, T... Obj);
		
		//public List queryBySQL(String sql, T... tObjt);
	
		public List queryList();
		
		public T queryObjt(String condition);
		
		public void addObjt(T tObjt);
		
		public void modObjt(T tObjt);
		
		public void delObjt(T tObjt);
		
		public int sqlAssist(String sql);
}
