package com.demo.course.courseunitprioritizer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.demo.course.courseunitprioritizer.model.Course;

public class CourseDependencyOrganizingService {

    private List<Course> courseConductingSequence = new ArrayList<>();

    public List<Course> execute(Map<Course, List<Course>> courseDependentMap) {

        for (Map.Entry<Course, List<Course>> courseEntry : courseDependentMap.entrySet()) {

            findDependentLocation(courseEntry.getKey(), courseEntry.getValue(), courseDependentMap);
        }

        return courseConductingSequence;
    }

    private void findDependentLocation(Course mainCourse, List<Course> dependingCourses, Map<Course, List<Course>> courseDependentList) {
        for (Course course : dependingCourses) {
            fillDependence(course, courseDependentList);
        }

        if (!courseConductingSequence.contains(mainCourse)) {
            courseConductingSequence.add(mainCourse);
        }
    }


    private void fillDependence(Course course, Map<Course, List<Course>> courseDependentList) {
        if (courseConductingSequence.contains(course)) {
            return;
        } else if (!courseDependentList.containsKey(course)) {
            courseConductingSequence.add(course);
            return;
        }

        List<Course> dependingCourses = courseDependentList.get(course);
        findDependentLocation(course, dependingCourses, courseDependentList);
    }
}
