package model;

public class BaseDevModulesBean {
	
	private int fid;
	private int dm_type;//bool??
	private int dev_id;
	private String modulist; //text类型 在数据库中
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setDm_type(int dm_type){
		this.dm_type=dm_type;
	}
	public int getDm_type(){
		return dm_type;
	}
	
	public void setDev_id(int dev_id){
		this.dev_id=dev_id;
	}
	public int getDev_id(){
		return dev_id;
	}
	
	public void setModulist(String modulist){
		this.modulist=modulist;
	}
	public String getModulist(){
		return modulist;
	}
}
