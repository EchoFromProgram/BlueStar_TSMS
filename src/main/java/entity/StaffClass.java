package entity;
/**
 * 员工班级表，对应staff-class表
 * @author happyChicken
 *
 */
public class StaffClass {
	
	//id
	private int staffClassId;
	
	//员工id
	private int staffId;
	
	//班级id
	private int classId;

	public int getStaffClassId() {
		return staffClassId;
	}

	public void setStaffClassId(int staffClassId) {
		this.staffClassId = staffClassId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "StaffClass [staffClassId=" + staffClassId + ", staffId=" + staffId + ", classId=" + classId + "]";
	}
}
