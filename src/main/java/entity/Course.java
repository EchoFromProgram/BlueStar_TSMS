package entity;
/**
 * 课程类，对应course表
 * @author happyChicken
 *
 */
public class Course {
	
	// 课程id
	private int courseId;
	
	// 课程名
	private String course;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", course=" + course + "]";
	}
}
