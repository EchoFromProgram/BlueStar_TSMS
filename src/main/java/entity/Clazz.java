package entity;
/**
 * 班级类，对应class表
 * @author happyChicken
 *
 */
public class Clazz {
	
	// 班级id
	private Integer classId;
	
	// 班级名
	private String className;
	
	// 课程id 
	private Integer courseId;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", className=" + className + ", courseId=" + courseId + "]";
	}
	
}
