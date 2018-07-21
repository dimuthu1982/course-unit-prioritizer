package com.demo.course.courseunitprioritizer.model;

public class Course {

	private int courseId;

	private String courseTitle;

	public Course(int courseId, String courseTitle) {
		super();
		this.courseId = courseId;
		this.courseTitle = courseTitle;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}


	@Override
	public int hashCode() {
		return courseId;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Course)) return false;

		return ((Course)obj).getCourseId() == this.courseId;
	}

	@Override
	public String toString() {
		return "Course [Course ID=" + courseId + ", Course Tile=" + courseTitle + "]";
	}


}
