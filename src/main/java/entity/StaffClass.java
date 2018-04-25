package entity;
/**
 * 员工班级表，对应staff-class表
 * @author happyChicken
 *
 */
public class StaffClass {
	
	//id
	private Integer staffClassId;
	
	//员工id
	private Integer staffId;
	
	//班级id
	private Integer classId;

	public Integer getStaffClassId() {
		return staffClassId;
	}

	public void setStaffClassId(Integer staffClassId) {
		this.staffClassId = staffClassId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "StaffClass [staffClassId=" + staffClassId + ", staffId=" + staffId + ", classId=" + classId + "]";
	}
}
