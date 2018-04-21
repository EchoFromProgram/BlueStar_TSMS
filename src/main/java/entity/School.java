package entity;
/**
 * 学校类，对应school表
 * @author happyChicken
 *
 */
public class School {
	
	//学校id
	private int schoolId;
	
	//城市id
	private int cityId;
	
	//学校名
	private String school;

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", cityId=" + cityId + ", school=" + school + "]";
	}
}
