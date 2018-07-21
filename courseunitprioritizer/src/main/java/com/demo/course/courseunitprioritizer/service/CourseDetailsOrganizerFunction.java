package com.demo.course.courseunitprioritizer.service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.demo.course.courseunitprioritizer.model.Course;

public class CourseDetailsOrganizerFunction {

    public Map<Integer, Course> execute(Stream<String> courseDataStream) {

        return courseDataStream
                .skip(1)
                .map(this::createCourse)
                .collect(Collectors.toMap(Course:: getCourseId, Function.identity()));

    }

    private Course createCourse(String courseData) {
        int courseNumber = Integer.parseInt(courseData.split(",")[0]);
        String courseName = courseData.split(",")[1];

        return new Course(courseNumber, courseName);
    }

}
