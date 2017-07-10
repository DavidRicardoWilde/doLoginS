package model;

public class DevTypeBean {
	private int devtype_id;
	private String devtypename;
	private String dev_arch;
	private String dev_archname;
	
	public DevTypeBean(){
		//code
	}
	
	public void setDevtype_id(int devtype_id){
		this.devtype_id=devtype_id;
	}
	public int getDevtype_id(){
		return devtype_id;
	}
	
	public void setDevtypename(String devtypename){
		this.devtypename=devtypename;
	}
	public String getDevtypename(){
		return devtypename;
	}
	
	public void setDev_arch(String dev_arch){
		this.dev_arch=dev_arch;
	}
	public String getDev_arch(){
		return dev_arch;
	}
	
	public void setDev_archname(String dev_archname){
		this.dev_archname=dev_archname;
	}
	public String getDev_archname(){
		return dev_archname;
	}
}
