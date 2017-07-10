package model;

public class BaseModulesBean {
	private int fid;
	private String modulename;
	private String modulememo;
	
	public BaseModulesBean(){
		
	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setModulename(String modulename){
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
}
