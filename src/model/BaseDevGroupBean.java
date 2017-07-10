package model;

public class BaseDevGroupBean {
	private int fid;
	private int _parentid;
	private int lft;
	private int rgt;
	private String grpname;
	private String grpmemo;
	private int efc;
	private int wxid;
	private int smsid;
//	private String ml;
	
	public BaseDevGroupBean(){
		
	}
	
//	public void setMl(String ml){
//		this.ml=ml;
//	}
//	public String getMl(){
//		return ml;
//	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void set_parentid(int _parentid){
		this._parentid=_parentid;
	}
	public int get_parentid(){
		return _parentid;
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
}
