 package model;	//Ô­À´µÄUser

public class UserBean {
	private int fid;
	private int mgr_type;
	private int devgrp_id;
	private boolean isroot;
	private String fname;
	private String fpassword;
	private String email;
	private String tel;
	
	public UserBean(){
		
	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setMgr_type(int mgr_type){
		this.mgr_type=mgr_type;
	}
	public int getMgr_type(){
		return mgr_type;
	}
	
	public void setDevgrp_id(int devgrp_id){
		this.devgrp_id=devgrp_id;
	}
	public int getDevgrp_id(){
		return devgrp_id;
	}
	
	public void setIsroot(boolean isroot){
		this.isroot=isroot;
	}
	public boolean getIsroot(){
		return isroot;
	}
	
	public void setFname(String fname){
		this.fname=fname;
	}
	public String getFname(){
		return fname;
	}
	
	public void setFpassword(String fpassword){
		this.fpassword=fpassword;
	}
	public String getFpassword(){
		return fpassword;
	}
	
	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}		
}
