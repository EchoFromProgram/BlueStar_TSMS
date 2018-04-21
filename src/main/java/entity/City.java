package entity;
/**
 * 用户所属城市类，对应city表
 * @author happyChicken
 *
 */
public class City {
	
	// 城市id
	private int cityId;
	
	// 省份id
	private int provinceId;
	
	// 城市名
	private String city;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", provinceId=" + provinceId + ", city=" + city + "]";
	}
}
