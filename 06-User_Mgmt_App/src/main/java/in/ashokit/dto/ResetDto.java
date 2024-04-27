package in.ashokit.dto;


public class ResetDto {
	
	
	private Integer id;
	private String email;
	private String pwd;
	private String updatedPwd;
	private String confirmPwd;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUpdatedPwd() {
		return updatedPwd;
	}
	public void setUpdatedPwd(String updatedPwd) {
		this.updatedPwd = updatedPwd;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	
	

}
