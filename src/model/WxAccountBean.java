package model;

public class WxAccountBean {
	private int fid;
	private String paccount;
	private String pnick;
	private String pname;
	private String url;
	private String token;
	private String appid;
	private String appsecret;
	private String access_token;
	private String expires_in;
	private String flast;
	private int enabled;
	
	public WxAccountBean(){
		
	}
	
	public void setFid(int fid){
		this.fid=fid;
	}
	public int getFid(){
		return fid;
	}
	
	public void setPaccount(String paccount){
		this.paccount=paccount;
	}
	public String getPaccount(){
		return paccount;
	}
	
	public void setPnick(String pnick){
		this.pnick=pnick;
	}
	public String getPnick(){
		return pnick;
	}
	
	public void setPname(String pname){
		this.pname=pname;
	}
	public String getPname(){
		return pname;
	}
	
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	
	public void setToken(String token){
		this.token=token;
	}
	public String getToken(){
		return token;
	}
	
	public void setAppid(String appid){
		this.appid=appid;
	}
	public String getAppid(){
		return appid;
	}
	
	public void setAppsecret(String appsecret){
		this.appsecret=appsecret;
	}
	public String getAppsecret(){
		return appsecret;
	}
	
	public void setAccess_token(String access_token){
		this.access_token=access_token;
	}
	public String getAccess_token(){
		return access_token;
	}
	
	public void setExpires_in(String expires_in){
		this.expires_in=expires_in;
	}
	public String getExpires_in(){
		return expires_in;
	}
	
	public void setFlast(String flast){
		this.flast=flast;
	}
	public String getFlast(){
		return flast;
	}
	
	public void setEnabled(int enabled){
		this.enabled=enabled;
	}
	public int getEnabled(){
		return enabled;
	}
}
