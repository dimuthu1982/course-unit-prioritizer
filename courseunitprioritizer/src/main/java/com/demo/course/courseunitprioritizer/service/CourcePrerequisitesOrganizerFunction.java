package com.demo.course.courseunitprioritizer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.demo.course.courseunitprioritizer.model.Course;

public class CourcePrerequisitesOrganizerFunction {

	Map<Integer, Course> courseDetailsHolder;

	public CourcePrerequisitesOrganizerFunction(Map<Integer, Course> courseDetailsHolder) {
		this.courseDetailsHolder = courseDetailsHolder;
	}

	public Map<Course, List<Course>> execute(Stream<String> courseDependenctStream){
		Map<Course, List<Course>> courseDependentHolder = new HashMap<>();

		courseDependenctStream
		.skip(1)
		.forEach(cource -> courseDependencyCollector(cource, courseDependentHolder));

		return courseDependentHolder;
	}

	private void courseDependencyCollector(String courceWithDependencys, Map<Course, List<Course>> courseDependentHolder) {
		Course mainCourse = getCourse(courceWithDependencys.split(",")[0]);
		Course dependenctCourse = getCourse(courceWithDependencys.split(",")[1]);

		courseDependentHolder
		.computeIfAbsent(mainCourse, key -> new ArrayList<Course>())
		.add(dependenctCourse);
	}

	private Course getCourse(String courseNumber) {
		return courseDetailsHolder.get(Integer.parseInt(courseNumber));
	}
}
