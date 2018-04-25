package entity;
/**
 * 员工类，对应staff表
 * @author happyChicken
 *
 */
public class Staff {
	
	//id
	private Integer tId;
	
	//身份证
	private String identityNum;
	
	//工作履历
	private String resume;
	
	//QQ
	private String qq;
	
	//电话
	private String telephone;
	
	//邮箱
	private String email;

	public Integer gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Staff [tId=" + tId + ", identityNum=" + identityNum + ", resume=" + resume + ", qq=" + qq
				+ ", telephone=" + telephone + ", email=" + email + "]";
	}
}
