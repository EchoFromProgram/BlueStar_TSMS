package entity;
/**
 * 班级类，对应class表
 * @author happyChicken
 *
 */
public class Clazz {
	
	// 班级id
	private int classId;
	
	// 班级名
	private String className;
	
	// 课程id 
	private int courseId;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", className=" + className + ", courseId=" + courseId + "]";
	}
	
}
