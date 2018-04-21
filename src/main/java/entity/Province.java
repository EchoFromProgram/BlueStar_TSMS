package entity;
/**
 * 用户所属省份类，对应province表
 * @author happyChicken
 *
 */
public class Province {
	
	// 省份id
	private int provinceId;
	
	// 省份
	private String province;

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Province [provinceId=" + provinceId + ", province=" + province + "]";
	}
	
}
