package model;

public class BaseFeatureBean {
	private int pid;
	private int fid;
	private int id;
	private String fname;
	private String furl;
	private String ficon;
	private int pwinfo;
	private int isroot;
	private int ordid;
	private String fmemo;
	
	public BaseFeatureBean(){
		
	}
	
	public void setFmemo(String fmemo){
		this.fmemo=fmemo;
	}
	public String getFmemo(){
		return fmemo;
	}
	
	public void setOrdid(int ordid){
		this.ordid=ordid;
	}
	public int getOrdid(){
		return ordid;
	}
	
	public void setIsroot(int isroot){
		this.isroot=isroot;
	}
	public int getIsroot(){
		return isroot;
	}
	
	public void setPwinfo(int pwinfo){
		this.pwinfo=pwinfo;
	}
	public int getPwinfo(){
		return pwinfo;
	}
	
	public void setFicon(String ficon){
		this.ficon=ficon;
	}
	public String getFicon(){
		return ficon;
	}
	
	public void setFurl(String furl){
		this.furl=furl;
	}
	public String getFurl(){
		return furl;
	}
	
	public void setFname(String fname){
		this.fname=fname;
	}
	public String getFname(){
		return fname;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setPid(int pid){
		this.pid=pid;
	}
	public int getPid(){
		return pid;
	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
}
