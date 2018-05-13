package entity;
/**
 * 客户类，对应customer表
 * @author happyChicken
 *
 */
public class Customer  {
	
	// id
	private Integer infoId;
	
	// 身份证
	private String identityNum;
	
	// 学校
	private String school;
	
	// 专业年级
	private String gradeMajor;
	
	// qq
	private String qq;
	
	// 电话号码
	private String telephone;
	
	// 邮箱
	private String email;

	//城市
	private String city;
	
	//省份
	private String province;

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getGradeMajor() {
		return gradeMajor;
	}

	public void setGradeMajor(String gradeMajor) {
		this.gradeMajor = gradeMajor;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Customer [infoId=" + infoId + ", identityNum=" + identityNum + ", school=" + school + ", gradeMajor="
				+ gradeMajor + ", qq=" + qq + ", telephone=" + telephone + ", email=" + email + ", city=" + city
				+ ", province=" + province + "]";
	}

	

	


	
}
